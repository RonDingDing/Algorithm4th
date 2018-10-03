import edu.princeton.cs.algs4.StdOut;

public class BinaryRank {
    public static int rank(int key, int[] a) {
        //查看数组中某个数的排名，若数不在数组中，返回-1
        return rank(key, a, 0, a.length - 1);
    }

    public static int rank(int key, int[] a, int lo, int hi) {
        if (lo > hi)
            return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid])
            return rank(key, a, lo, mid - 1);
        else if (key > a[mid])
            return rank(key, a, mid + 1, hi);
        else
            return mid;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 6};
        StdOut.println(rank(4, a));
    }
}
