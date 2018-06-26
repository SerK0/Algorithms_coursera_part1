import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private byte[][] a;
    private int size;
    private int opensites;
    private WeightedQuickUnionUF union;

    public Percolation(int n) {
        union = new WeightedQuickUnionUF(n * n + 2);
        opensites = 0;
        size = n;
        a = new byte[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1) {
                    union.union(xyto1d(1, 0), xyto1d(i, j));
                }
                if (i == n) {
                    union.union(xyto1d(i, n + 1), xyto1d(i, j));
                }
                a[i][j] = 0;
            }
        }

    }

    public void open(int row, int col) {
        if (row <= 0 || row > size) throw new IndexOutOfBoundsException("row index i out of bounds");
        if (col <= 0 || col > size) throw new IndexOutOfBoundsException("col index i out of bounds");
        if (a[row][col] != 1) {
            a[row][col] = 1;
            opensites++;
        }

        if ((col > 1) && (a[row][col - 1] > 0)) {
            union.union(xyto1d(row, col), xyto1d(row, col - 1));
        }
        if ((col < size) && (a[row][col + 1] > 0)) {
            union.union(xyto1d(row, col), xyto1d(row, col + 1));
        }
        if ((row > 1) && (a[row - 1][col] > 0)) {
            union.union(xyto1d(row, col), xyto1d(row - 1, col));
        }
        if ((row < size) && (a[row + 1][col] > 0)) {
            union.union(xyto1d(row, col), xyto1d(row + 1, col));
        }

    }


    public boolean isOpen(int row, int col) {
        return a[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        return (union.connected(xyto1d(1, 0), xyto1d(row, col))) && a[row][col] > 0;
    }

    public int numberOfOpenSites() {
        return opensites;
    }

    public boolean percolates() {
        if (size == 1) {
            if (isOpen(1, 1)) {
                return true;
            } else {
                return false;
            }
        }

        return union.connected(xyto1d(1, 0), xyto1d(size, size + 1));
    }

    private int xyto1d(int row, int col) {
        return (row - 1) * size + col;
    }


    public static void main(String[] args) {
    }
}
