import edu.princeton.cs.algs4.StdOut;

public class Hypotenuse {

    public static double algorithm(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    public static void main(String[] args) {
        StdOut.println(algorithm(3, 4));
    }
}
