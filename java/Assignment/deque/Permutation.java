import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {

        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);

        String string;

        while (!StdIn.isEmpty()) {

            string = StdIn.readString();
            queue.enqueue(string);
        }

        int count = 0;
        for (String ss : queue) {
            if (count < k) {
                StdOut.println(ss);
                count++;
            } else break;
        }

    }

}