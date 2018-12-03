import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.HashMap;

public class Solver {


    private class SearchNode {
        private final SearchNode parent;
        private final int moved;
        private final int manhattan;
        private final int hamming;
        private final Board board;
        private final Comparator<SearchNode> manhattanC = new ManhattanOrder();
        private final Comparator<SearchNode> hammingC = new HammingOrder();


        public SearchNode(Board b, SearchNode p, int m) {
            board = b;
            parent = p;
            moved = m;
            manhattan = b.manhattan() + moved;
            hamming = b.hamming() + moved;
        }

        private class HammingOrder implements Comparator<SearchNode> {
            public int compare(SearchNode s1, SearchNode s2) {
                if (s1.manhattan > s2.manhattan) {
                    return 1;
                } else if (s1.manhattan < s2.manhattan) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

        private class ManhattanOrder implements Comparator<SearchNode> {
            public int compare(SearchNode s1, SearchNode s2) {
                if (s1.hamming > s2.hamming) {
                    return 1;
                } else if (s1.hamming < s2.hamming) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

    }

    private MinPQ<SearchNode> nodeQueue;
    private boolean solvables;

    private Stack<Board> resultStack;

    public Solver(Board initial) {
        SearchNode zeroNode = new SearchNode(initial, null, 0);
        nodeQueue = new MinPQ<SearchNode>(zeroNode.hammingC);
        nodeQueue.insert(zeroNode);
        resultStack = new Stack<Board>();
        solve();
    }

    public void solve() {
        int currentMove = 0;
        HashMap map = new HashMap<String, Boolean>();
        while (!nodeQueue.isEmpty()) {
            SearchNode node = nodeQueue.delMin();
           

            map.put(node.board.toString(), true);
            if (node.board.isGoal()) {
                solvables = true;
                while (node.parent != null) {
                    resultStack.push(node.board);
                    node = node.parent;
                }
                break;
            } else if (node.board.twin().isGoal()) {
                solvables = false;
                break;
            } else {
                currentMove += 1;
                for (Board b : node.board.neighbors()) {
                    if (!map.containsKey(b.toString())) {
                        SearchNode newNode = new SearchNode(b, node, currentMove);
                        nodeQueue.insert(newNode);
                    }
                }
            }
        }

    }

    public boolean isSolvable() {
        return solvables;
    }

    public int moves() {
        if (isSolvable()) {
            return resultStack.size();
        } else {
            return -1;
        }
    }

    public Iterable<Board> solution() {
        return resultStack;
    }

    public static void main(String[] args) {
        Board b = new Board(new int[][]{
                {8, 2, 3,},
                {7, 0, 5,},
                {6, 4, 1,},
        });
        Solver solver = new Solver(b);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}



