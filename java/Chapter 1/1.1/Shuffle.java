import edu.princeton.cs.algs4.StdRandom;

public class Shuffle {

    public static void algorithm(double[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) { // Exchange a[i] with random element in a[i..N-1]
            int r = i + StdRandom.uniform(N - i);
            double temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    public static void main(String[] args) {

    }
}
