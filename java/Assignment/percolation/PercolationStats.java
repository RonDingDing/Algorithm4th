/* *****************************************************************************
 *  Name: Ron Ding
 *  Date: 2018-10-03
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] allTimes;
    private double s, x;
    private int t;

    public PercolationStats(int n, int trials) {
        allTimes = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation nf = new Percolation(n);
            double num = 0;
            while (!nf.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!nf.isOpen(row, col)) {
                    nf.open(row, col);
                    num += 1;
                }
            }
            allTimes[i] = num / (double) (n * n);
        }

        s = stddev();
        t = allTimes.length;
        x = mean();
    }

    public double mean() {
        return StdStats.mean(allTimes);

    }

    public double stddev() {
        return StdStats.stddev(allTimes);
    }

    public double confidenceLo() {
        return x - 1.96 * s / Math.sqrt(t);
    }

    public double confidenceHi() {

        return x + 1.96 * s / Math.sqrt(t);
    }

    public static void main(String[] args) {
        PercolationStats p = new PercolationStats(5, 100);
        StdOut.println(p.mean());
        StdOut.println(p.confidenceHi());
        StdOut.println(p.confidenceLo());
        StdOut.println(p.stddev());
        StdOut.println(p.mean());
    }
}
