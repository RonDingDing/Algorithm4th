import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomintFromTo {
    public static int algorithm(int lo, int hi) {
        return lo + StdRandom.uniform(hi - lo);
    }

    public static void main(String[] args) {
        StdOut.println(algorithm(2, 5));
    }
}
