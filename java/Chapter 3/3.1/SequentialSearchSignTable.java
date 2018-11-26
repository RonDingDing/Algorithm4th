import edu.princeton.cs.algs4.StdOut;
import java.util.Queue;

public class SequentialSearchSignTable<Key, Value> {
    private Node first;
    private int num = 0;
    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }



    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) return x.val;
        }
        return null;

    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        num += 1;

    }


    public static void main(String[] args) {
        SequentialSearchSignTable<String, Integer> dic = new SequentialSearchSignTable<String, Integer>();
        dic.put("2", 2);
        dic.put("1", 1);
        StdOut.println(dic.get("1"));


    }
}