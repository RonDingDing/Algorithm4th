import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;


public class Board {
    private int n;
    private final String stringblock;
    private final String stringgoal;
    private int zero = 48; // ascii码中的偏移量

    private String swap(String string, int i, int j) {
        char[] charss = string.toCharArray();
        char tmp = charss[j];
        charss[j] = charss[i];
        charss[i] = tmp;

        return String.valueOf(charss);
    }

    private String toStr(int[] array) {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            strb.append((char) (array[i] + zero));
        }
        return strb.toString();
    }

    private String toStr(int[][] blocks) {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                strb.append((char) (blocks[i][j] + zero));
            }
        }
        return strb.toString();
    }

    private int[] checkLength(int[][] blocks) {
        for (int i = 0; i < n; i++) {
            if (blocks[i].length != n) throw new IllegalArgumentException("Can't be such board!");
        }
        int[] array = new int[n * n];
        int start = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[start++] = blocks[i][j];
            }
        }
        Arrays.sort(array);
        return array;
    }

    private void checkNum(int[] array) {
        int current = 0;
        for (int m = 0; m < n * n; m++) {
            if (current != array[m]) {
                throw new IllegalArgumentException("Can't be such board!");
            }
            current += 1;
        }
    }

    private int[] toGoal(int[] array) {
        for (int p = 1; p < n * n; p++) {
            array[p - 1] = array[p];
        }
        array[array.length - 1] = 0;
        return array;
    }

    private Board(String str) {
        stringblock = str;
        n = (int) Math.sqrt(str.length());
        int[] goalArray = new int[n * n];
        for (int p = 0; p < n * n; p++) {
            goalArray[p] = p + 1;
        }
        goalArray[goalArray.length - 1] = 0;
        stringgoal = toStr(goalArray);

    }

    public Board(int[][] blocks) {
        n = blocks.length;
        // 检查长度
        int[] array = checkLength(blocks);

        // 检查数据是否1到x
        checkNum(array);
        int[] goalArray = toGoal(array);

        stringblock = toStr(blocks);
        stringgoal = toStr(goalArray);

    }

    public int dimension() {
        return n;
    }


    public int hamming() {
        int count = 0;
        for (int i = 0; i < stringblock.length(); i++) {
            if (stringblock.charAt(i) != stringgoal.charAt(i)) {
                count += 1;
            }
        }
        return count;
    }

    public int manhattan() {
        int count = 0;
        for (int i = 0; i < stringblock.length(); i++) {
            if (stringblock.charAt(i) != ((char) zero) && stringblock.charAt(i) != stringgoal.charAt(i)) {
                int col = i / n;
                int row = i % n;

                int o = stringgoal.indexOf(stringblock.charAt(i));
                int colReal = o / n;
                int rowReal = o % n;
                count += Math.abs(row - rowReal) + Math.abs(col - colReal);
            }
        }
        return count;

    }

    public boolean isGoal() {
        return stringblock.equals(stringgoal);
    }

    public String toString() {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < n * n; i++) {
            int number = (int) stringblock.charAt(i) - zero;
            strb.append(String.format("%2d", number));
            if ((i + 1) % n == 0) {
                strb.append("\n");
            } else {
                strb.append(" ");
            }

        }
        return strb.toString();
    }

    public boolean equals(Object y) {
        if (y == null) return false;
        if (!(y instanceof Board)) {
            return false;
        } else if (((Board) y).dimension() == this.dimension() && ((Board) y).toString().equals(this.toString())) {
            return true;
        } else {
            return false;
        }
    }


    public Board twin() {
        for (int i = 0; i < n * n; i++) {
            int start = n * i;
            int stop = n * i + n - 1;
            String substring = stringblock.substring(start, stop + 1);
            if (substring.indexOf((char) zero) == -1) {
                String swapString = swap(stringblock, start, start + 1);
                return new Board(swapString);
            }
        }
        return null;
    }

    public Iterable<Board> neighbors() {
        Queue q = new Queue<Board>();

        int o = stringblock.indexOf((char) zero);


        // 上一行
        int up = o - n;
        if (up > -1) {
            String upBlock = swap(stringblock, o, up);
            q.enqueue(new Board(upBlock));
        }

        // 下一行
        int down = o + n;
        if (down < n * n) {
            String downBlock = swap(stringblock, o, down);
            q.enqueue(new Board(downBlock));
        }

        // 左边
        int left = o - 1;
        if (left > -1 && left / n == o / n) {
            String leftBlock = swap(stringblock, o, left);
            q.enqueue(new Board(leftBlock));
        }

        int right = o + 1;
        if (right < n * n && right / n == o / n) {
            String rightBlock = swap(stringblock, o, right);
            q.enqueue(new Board(rightBlock));
        }


        return q;
    }


    public static void main(String[] args) {
        Board b = new Board(new int[][]{{0, 3}, {1, 2}});
        StdOut.println("toString(): ");
        StdOut.println(b.toString());
        StdOut.println();

        StdOut.println("hamming(): ");
        StdOut.println(b.hamming());
        StdOut.println();

        StdOut.println("mahattan(): ");
        StdOut.println(b.manhattan());
        StdOut.println();


        StdOut.println("isGoal(): ");
        StdOut.println(b.isGoal());
        StdOut.println();


        StdOut.println("twin(): ");
        StdOut.println(b.twin());
        StdOut.println();


        StdOut.println("equals(): ");
        StdOut.println(b.equals(new Board(new int[][]{{1, 2, 3, 9}, {4, 0, 7, 10}, {6, 8, 5, 11}, {12, 13, 14, 15}})));
        StdOut.println();


        StdOut.println("neighbors(): ");
        for (Board n : b.neighbors()) {
            StdOut.println(n.toString());
            StdOut.println("\n");
        }


    }
}
