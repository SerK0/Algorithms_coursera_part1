import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] results;
    private double m;
    private double s;
    private double conf_lo;
    private double conf_hi;

    public PercolationStats(int n, int trials) {
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }
            results[i] = (double) p.numberOfOpenSites() / (n * n);
        }

    }

    public double mean() {
        m = StdStats.mean(results);
        return m;
    }

    public double stddev() {
        s = StdStats.stddev(results);
        return s;
    }

    public double confidenceLo() {
        conf_lo = m - 1.96 * s / Math.sqrt(results.length);
        return conf_lo;
    }

    public double confidenceHi() {
        conf_hi = m + 1.96 * s / Math.sqrt(results.length);
        return conf_hi;
    }

    private void show() {
        StdOut.println("mean=" + mean());
        StdOut.println("stddev=" + stddev());
        StdOut.println("95% confidence interval=[" + confidenceLo() + " , " + confidenceHi() + "]");
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        PercolationStats ps = new PercolationStats(n, trials);
        ps.show();
    }
}
