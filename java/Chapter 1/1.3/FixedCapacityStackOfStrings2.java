import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfStrings2 {
    private String[] a; // stack entries
    private int N; // size

    public FixedCapacityStackOfStrings2(int cap) {
        a = new String[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        a[N++] = item;
    }

    public String pop() {
        return a[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings2 s;
        s = new FixedCapacityStackOfStrings2(100);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty())
                StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
// % more tobe.txt
// to be or not to - be - - that - - - is
// % java FixedCapacityStackOfStrings < tobe.txt
// to be not that or be (2 left on stack)
