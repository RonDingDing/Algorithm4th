
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private final int[][] blocks;
    private final int number;
    private final int manhattans;
    private final int zeroX;
    private final int zeroY;


    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new java.lang.NullPointerException();
        }

        number = blocks.length;
        int zeroX1 = 0, zeroY1 = 0;
        this.blocks = new int[number][number];
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                this.blocks[i][j] = blocks[i][j];
                if (blocks[i][j] == 0) {
                    zeroX1 = i;
                    zeroY1 = j;
                }
            }
        }
        zeroX = zeroX1;
        zeroY = zeroY1;
        manhattans = manhattan2();

    }

    private int hamming2() {
        int count = 0;
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                int blockn = blocks[i][j];
                int should;
                if (i == number - 1 && j == number - 1) {
                    should = 0;
                } else {
                    should = i * number + j + 1;
                }
                if (blockn != 0 && blockn != should) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public int hamming() {
        return hamming2();
    }

    public int manhattan() {
        return manhattans;
    }

    private int manhattan2() {
        int count = 0;
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                int blockn = blocks[i][j];
                int should;
                if (i == number - 1 && j == number - 1) {
                    should = 0;
                } else {
                    should = i * number + j + 1;
                }
                if (blockn != 0 && blockn != should) {
                    int blocknOI = (blockn - 1) / number;
                    int blocknOJ = (blockn - 1) % number;
                    count += Math.abs(blocknOI - i) + Math.abs(blocknOJ - j);
                }
            }
        }

        return count;

    }

    public int dimension() {
        return number;
    }

    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(number);
        stringbuilder.append("\n");
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                String content = String.format("%2d", blocks[i][j]);
                stringbuilder.append(content);
                if ((j + 1) % number == 0) {
                    stringbuilder.append("\n");
                } else {
                    stringbuilder.append(" ");
                }
            }
        }
        return stringbuilder.toString();
    }


    public Iterable<Board> neighbors() {
        Queue<Board> queue = new Queue<>();
        if (zeroX - 1 > -1) {
            int[][] newBoard = swap(zeroX - 1, zeroY, zeroX, zeroY);
            queue.enqueue(new Board(newBoard));
        }

        if (zeroX + 1 < number) {
            int[][] newBoard = swap(zeroX + 1, zeroY, zeroX, zeroY);
            queue.enqueue(new Board(newBoard));

        }
        if (zeroY - 1 > -1) {
            int[][] newBoard = swap(zeroX, zeroY - 1, zeroX, zeroY);
            queue.enqueue(new Board(newBoard));

        }
        if (zeroY + 1 < number) {
            int[][] newBoard = swap(zeroX, zeroY + 1, zeroX, zeroY);
            queue.enqueue(new Board(newBoard));
        }
        return queue;

    }

    private int[][] swap(int m, int n, int k, int p) {
        int[][] anotherBlocks = new int[number][number];
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                anotherBlocks[i][j] = blocks[i][j];
            }
        }

        int temp = blocks[m][n];
        anotherBlocks[m][n] = anotherBlocks[k][p];
        anotherBlocks[k][p] = temp;
        return anotherBlocks;
    }

    public boolean isGoal() {
        return manhattan() == 0;
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (this.getClass() != y.getClass()) {
            return false;
        }
        Board that = (Board) y;
        if (this.number != that.number) {
            return false;
        }
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                if (this.blocks[i][j] != that.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public Board twin() {
        if (blocks[0][0] != 0 && blocks[0][1] != 0) {
            int[][] twinBoard = swap(0, 0, 0, 1);
            return new Board(twinBoard);
        } else {
            int[][] twinBoard = swap(1, 0, 1, 1);
            return new Board(twinBoard);
        }

    }

    public static void main(String[] args) {
        // create initial board from file
        Board board = new Board(
                new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}
        );
        StdOut.print(board.twin());
        StdOut.print(board.twin().twin());
        StdOut.print(board.twin().twin().twin());

    }

}
