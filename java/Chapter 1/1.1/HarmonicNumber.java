import edu.princeton.cs.algs4.StdOut;

//倒数的和
public class HarmonicNumber {
    public static double algorithm(int N) {
        double sum = 0.0;
        for (int i = 1; i <= N; i++)
            sum += 1.0 / i;
        return sum;
    }

    public static void main(String[] args) {
        StdOut.println(algorithm(3));
    }
}
