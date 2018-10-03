import edu.princeton.cs.algs4.StdRandom;

public class Discrete {
    public static int algorithm(double[] a) { // Entries in a[] must sum to 1.
        double r = StdRandom.random();
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum = sum + a[i];
            if (sum >= r)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        double[] a = {0.1, 0.2};
        System.out.println(algorithm(a));
    }
}
