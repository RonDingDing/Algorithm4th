import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomintFromToDouble {
    public static double algorithm(double a, double b) {
        return a + StdRandom.random() * (b - a);
    }

    public static void main(String[] args) {
        StdOut.println(algorithm(2.2, 5.9));
    }
}
