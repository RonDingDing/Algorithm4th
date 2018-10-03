import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Randomint {
    public static int algorithm(int N) {
        return (int) (StdRandom.random() * N);
    }

    public static void main(String[] args) {
        StdOut.println(algorithm(5));
    }
}
