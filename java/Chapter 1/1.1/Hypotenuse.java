public class Hypotenuse {

    public static double algorithm(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    public static void main(String[] args) {
        System.out.println(algorithm(3, 4));
    }
}