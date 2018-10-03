import edu.princeton.cs.algs4.StdOut;

public class IsPrime {
    public static boolean algorithm(int N) {
        if (N < 2)
            return false;
        for (int i = 2; i * i <= N; i += 2)
            if (N % i == 0)
                return false;
        return true;
    }

    public static void main(String[] args) {
        StdOut.println(algorithm(3));
    }
}
