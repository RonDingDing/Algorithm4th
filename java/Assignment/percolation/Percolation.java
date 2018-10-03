/* *****************************************************************************
 *  Name: Ron Ding
 *  Date: 2018-10-03
 *  Description:
 **************************************************************************** */

public class Percolation {

    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufF;

    private int base;
    private final boolean[][] grid;

    private int first;
    private int last;


    public Percolation(int n) { // create N-by-N grid, with all sites blocked
        if (n <= 0) {
            throw new IllegalArgumentException("N < 1!");
        }
        base = n;
        grid = new boolean[n][n];

        uf = new WeightedQuickUnionUF(n * n + 2);
        ufF = new WeightedQuickUnionUF(n * n + 1);

        first = 0;
        last = n * n + 1;
    }

    public void open(int row, int col) {
        // open site (row row, column col) if it is not open already
        if (!isOpen(row, col)) {
            int box = getBox(row, col);

            if (row == 1) {
                uf.union(first, box);
                ufF.union(first, box);
            }
            if (row == base) {
                uf.union(last, box);
            }

            unionIfOpen(box, row + 1, col);
            unionIfOpen(box, row - 1, col);
            unionIfOpen(box, row, col - 1);
            unionIfOpen(box, row, col + 1);

            grid[row - 1][col - 1] = true;
        }
    }

    public boolean isOpen(int row, int col) {   // is site (row row, column col) open?
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {   // is site (row row, column col) connected to top?
        if (isOpen(row, col)) {
            int box = getBox(row, col);
            return ufF.connected(first, box);
        }
        return false;
    }

    public boolean percolates() {   // does the system percolate?
        return uf.connected(first, last);
    }

    private void unionIfOpen(int box, int row, int col) {
        try {
            if (isOpen(row, col)) {
                int neighborBox = getBox(row, col);
                uf.union(neighborBox, box);
                ufF.union(neighborBox, box);
            }
        }
        catch (IndexOutOfBoundsException e) {
            // don't connect field with field outside grid
        }
    }

    private int getBox(int row, int col) {
        return (row - 1) * base + col;
    }
}
