public class RandomintFromTo {
    public static int algorithm(int lo, int hi) {
        return lo + StdRandom.uniform(hi - lo);
    }

    public static void main(String[] args) {
        System.out.println(algorithm(2, 5));
    }
}