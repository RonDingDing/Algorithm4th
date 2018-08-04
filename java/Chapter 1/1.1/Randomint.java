public class Randomint {
    public static int algorithm(int N) {
        return (int) (StdRandom.random() * N);
    }

    public static void main(String[] args) {
        System.out.println(algorithm(5));
    }
}