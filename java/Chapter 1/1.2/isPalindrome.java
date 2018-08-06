public class isPalindrome {
    public static boolean algorithm(String s) {
        int N = s.length();
        for (int i = 0; i < N / 2; i++)
            if (s.charAt(i) != s.charAt(N - 1 - i))
                return false;
        return true;
    }

    public static void main(String[] args) {
        StdOut.println(algorithm("fldasf"));
    }
}