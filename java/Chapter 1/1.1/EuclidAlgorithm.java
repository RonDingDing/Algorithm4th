import edu.princeton.cs.algs4.StdOut;

public class EuclidAlgorithm {
    public static int algorithm(int p, int q) {
        if (q == 0)
            return p;
        int r = p % q;
        return algorithm(q, r);
    }

    public static void main(String[] args) {
        StdOut.println(algorithm(99, 3));
    }
}
