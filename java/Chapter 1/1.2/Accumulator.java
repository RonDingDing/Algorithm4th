public class Accumulator {
    private double total;
    private int N;

    public void addDataValue(double val) {
        N++;
        total += val;
    }

    public double mean() {
        return total / N;
    }

    public String toString() {
        return "Mean (" + N + " values): " + String.format("%7.5f", mean());
    }

    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Accumulator a = new Accumulator();
        for (int t = 0; t < T; t++)
            a.addDataValue(StdRandom.random());
        StdOut.println(a);
    }
    // % java Accumulator 1000
    // Mean (1000 values): 0.51829
    // % java Accumulator 1000000
    // Mean (1000000 values): 0.49948
    // % java Accumulator 1000000
    // Mean (1000000 values): 0.50014
}