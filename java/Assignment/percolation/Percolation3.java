/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

public class Percolation3 {

    private int[] opened;
    private int base;

    public Percolation3(int n) {
        opened = new int[n * n + 1];
        base = n;
        for (int i = 0; i < base; i++) {
            opened[i] = 0;
        }
    }

    public void open(int row, int col) {
        if (row <= 0 || row > base) {
            throw new IllegalArgumentException("Row out of bounds!");
        }
        if (col <= 0 || col > base) {
            throw new IllegalArgumentException("Column out of bounds!");
        }

        opened[(row - 1) * base + col] = 1;

    }

    public boolean isOpen(int row, int col) {
        int box = locate(row, col);
        return opened[box] == 1;
    }

    public boolean isFull(int row, int col) {
        int[] near = new int[base * base + 1];
        int i = 2;
        near[1] = locate(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        for (int op = 1; op < base * base + 1; op++) {
            int newbox = near[op];
            if (newbox == 0) continue;
            if (0 < newbox && newbox <= base) {
                return true;
            }
            int newRow = findRow(newbox);
            int newCol = findCol(newbox);


            if (newRow - 1 > 0 && isOpen(newRow - 1, newCol)) {
                int boxer = locate(newRow - 1, newCol);
                if (notIn(boxer, near)) {
                    near[i] = boxer;
                    i += 1;
                }
            }

            if (newCol - 1 > 0 && isOpen(newRow, newCol - 1)) {
                int boxer = locate(newRow, newCol - 1);
                if (notIn(boxer, near)) {

                    near[i] = boxer;
                    i += 1;
                }
            }

            if (newRow + 1 < base && isOpen(newRow + 1, newCol)) {
                int boxer = locate(newRow + 1, newCol);
                if (notIn(boxer, near)) {
                    near[i] = boxer;
                    i += 1;
                }
            }

            if (newCol + 1 < base && isOpen(newRow, newCol + 1)) {
                int boxer = locate(newRow, newCol + 1);
                if (notIn(boxer, near)) {
                    near[i] = locate(newRow, newCol + 1);
                    i += 1;
                }
            }
        }


        return false;
    }

    public int numberOfOpenSites() {
        return opened.length;
    }

    public boolean percolates() {
        for (int i = 1; i < base + 1; i++) {
            if (isFull(base, i))
                return true;
        }
        return false;
    }

    public boolean notIn(int element, int[] all) {
        for (int i = 0; i < all.length; i++) {
            if (all[i] == element)
                return false;
        }
        return true;

    }

    public int locate(int row, int col) {
        return (row - 1) * base + col;

    }

    public int findRow(int newbox) {
        int newRow;
        if (newbox % base != 0)
            newRow = newbox / base + 1;
        else
            newRow = newbox / base;
        return newRow;
    }

    public int findCol(int newbox) {
        int newCol = newbox % base;
        if (newCol == 0)
            newCol = base;
        return newCol;
    }

    public static void main(String[] args) {
        Percolation a = new Percolation(3);
        a.open(2, 3);
        a.open(1, 1);
        a.open(2, 2);
        a.open(3, 3);
        a.open(1, 2);


        StdOut.println(a.percolates());

    }
}
