import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
    private final Digraph digraph;
    private final LinearProbingHashST<String, int[]> record;


    private static class Node {
        public final int vertex;
        public final int from;
        public final Node parent;

        public Node(int vertex, int from, Node parent) {
            this.vertex = vertex;
            this.from = from;
            this.parent = parent;
        }

//        public String toString() {
//            Integer v = this.parent == null ? null : this.parent.vertex;
//            return "{v: " + this.vertex + ", f:" + this.from + ", p:" + v + "}";
//
//        }
    }

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        digraph = new Digraph(G.V());
        for (int i = 0; i < G.V(); i++) {
            for (int j : G.adj(i)) {
                digraph.addEdge(i, j);
            }
        }
        record = new LinearProbingHashST<String, int[]>();
    }


    private String makeKey(int v, int w) {
        if (v > w) {
            int tmps = v;
            v = w;
            w = tmps;
        }
        return v + "," + w;
    }

    private int[] results(Node temp, Node[] marked, Bag<Node> hubPoint) {

        int distance = 0;
        Node original = marked[temp.vertex];
        hubPoint.add(temp);

        for (Node i = original; i.parent != null; i = i.parent) {
            distance++;
        }
        for (Node j = temp; j.parent != null; j = j.parent) {
            distance++;
        }

        int[] value = new int[]{temp.vertex, distance};
        return value;
    }

    private int[] commonAncestor(int v, int w) {

        int verticesNum = digraph.V();
        if (v > w) {
            int change = v;
            v = w;
            w = change;
        }

        if (v < 0 || v >= verticesNum || w < 0 || w >= verticesNum) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (verticesNum - 1));
        }
        if (v == w) {
            return new int[]{v, 0};
        }

        Bag<Node> hubPoint = new Bag<Node>();
        Queue<Node> queue = new Queue<>();
        queue.enqueue(new Node(v, v, null));
        queue.enqueue(new Node(w, w, null));

        int shortest = -1;
        int ancestor = -1;

        Node[] marked = makeMarked(verticesNum);

        while (!queue.isEmpty()) {
            Node temp = queue.dequeue();
            if (temp.from == v) {
                if (marked[temp.vertex] == null) {
                    marked[temp.vertex] = temp;
                    for (int adj : digraph.adj(temp.vertex)) {
                        queue.enqueue(new Node(adj, v, temp));
                    }
                } else if (marked[temp.vertex].from == w) {
                    int[] oneResult = results(temp, marked, hubPoint);
                    int[] result = putResult(v, w, ancestor, shortest, oneResult);
                    ancestor = result[0];
                    shortest = result[1];
                } else if (marked[temp.vertex].from == v && temp.vertex == v) {
                    int distance = hubDistance(temp, hubPoint, true, v, w);
                    if (distance > 0) {
                        int[] result = putResult(v, w, ancestor, shortest, new int[]{temp.vertex, distance});
                        ancestor = result[0];
                        shortest = result[1];
                    }
                }

            } else if (temp.from == w) {
                if (marked[temp.vertex] == null) {
                    marked[temp.vertex] = temp;
                    for (int adj : digraph.adj(temp.vertex)) {
                        queue.enqueue(new Node(adj, w, temp));
                    }
                } else if (marked[temp.vertex].from == v) {
                    int[] oneResult = results(temp, marked, hubPoint);
                    int[] result = putResult(v, w, ancestor, shortest, oneResult);
                    ancestor = result[0];
                    shortest = result[1];
                } else if (marked[temp.vertex].from == w && temp.vertex == w) {
                    int distance = hubDistance(temp, hubPoint, false, v, w);
                    if (distance > 0) {
                        int[] result = putResult(v, w, ancestor, shortest, new int[]{temp.vertex, distance});
                        ancestor = result[0];
                        shortest = result[1];
                    }
                }
            }
        }
        return new int[]{ancestor, shortest};
    }

    private int[] putResult(int v, int w, int ancestor, int shortest, int[] oneResult) {
        if ((ancestor == -1 && shortest == -1) || (shortest > oneResult[1])) {
            ancestor = oneResult[0];
            shortest = oneResult[1];
            String vw = makeKey(v, w);
            record.put(vw, oneResult);
        }
        return new int[]{ancestor, shortest};
    }

    private int hubDistance(Node temp, Bag<Node> hubPoint, boolean flag, int v, int w) {
        Node hub = null;
        Node ano = temp;
        int distance = 0;
        while (true) {
            for (Node special : hubPoint) {
                if (ano.vertex == special.vertex) {
                    hub = special;
                    break;
                }
            }
            if (ano.parent != null && hub == null) {
                ano = ano.parent;
                distance++;
            } else {
                break;
            }
        }
        if (hub != null) {
            for (Node j = hub; (j.parent != null) && (j.vertex != (flag ? v : w)); j = j.parent) {
                distance++;
            }
        } else {
            distance = -1;
        }
        return distance;
    }

    private void checkIterable(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null) {
            throw new java.lang.IllegalArgumentException();
        }
        for (Integer i : v) {
            if (i == null) {
                throw new java.lang.IllegalArgumentException();
            }
            if (i < 0 || i >= digraph.V()) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (digraph.V() - 1));
            }
        }
        for (Integer j : w) {
            if (j == null) {
                throw new java.lang.IllegalArgumentException();
            }
            if (j < 0 || j >= digraph.V()) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (digraph.V() - 1));
            }
        }
    }

    // 创造纪录是否已经搜索的数组
    private Node[] makeMarked(int verticesNum) {
        Node[] marked = new Node[verticesNum];
        for (int i = 0; i < verticesNum; i++) {
            marked[i] = null;
        }
        return marked;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        String vw = makeKey(v, w);
        int[] result = record.get(vw);
        if (result != null) {
            return record.get(vw)[1];
        } else {
            return commonAncestor(v, w)[1];
        }
    }


    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        String vw = makeKey(v, w);
        int[] result = record.get(vw);
        if (result != null) {
            return record.get(vw)[0];
        } else {
            return commonAncestor(v, w)[0];
        }
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        checkIterable(v, w);

        int shortestCommonAncestor = -1;
        int shortestAncestorLength = -1;

        for (int i : v) {
            for (int j : w) {
                int a = ancestor(i, j);
                int b = length(i, j);
                if (shortestAncestorLength < 0 && shortestCommonAncestor < 0) {
                    shortestCommonAncestor = a;
                    shortestAncestorLength = b;
                } else if (b < shortestAncestorLength) {
                    shortestCommonAncestor = a;
                    shortestAncestorLength = b;
                }
            }
        }
        return shortestAncestorLength;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        checkIterable(v, w);
        int shortestCommonAncestor = -1;
        int shortestAncestorLength = -1;

        for (int i : v) {
            for (int j : w) {
                int a = ancestor(i, j);
                int b = length(i, j);
                if (shortestAncestorLength < 0 && shortestCommonAncestor < 0) {
                    shortestCommonAncestor = a;
                    shortestAncestorLength = b;
                } else if (b < shortestAncestorLength) {
                    shortestCommonAncestor = a;
                    shortestAncestorLength = b;
                }
            }
        }
        return shortestCommonAncestor;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}
