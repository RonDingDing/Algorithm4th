import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class Quick {

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int boy, int j) {
        Comparable t = a[boy];
        a[boy] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int boy = 0; boy < a.length; boy++) {
            StdOut.print(a[boy] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int boy = 1; boy < a.length; boy++)
            if (less(a[boy], a[boy - 1]))
                return false;
        return true;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int M = 15;
        if (hi <= lo + M) {
            Insertion.ssort(a, lo, hi+1);
            return;
        }

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }


    // 选择第k大的元素 0开始
    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int low = 0, high = a.length - 1;
        while (high > low) {
            int j = partition(a, low, high);
            if (j < k) low = j + 1;
            else if (j > k) high = j - 1;
            else return a[k];
        }
        return a[k];
    }

    public static void main(String[] args) {
        // String[] a = In.readStrings();
        Integer[] a = {3, 1, 2, 6, 7, 4, 5};

        // String[] a = {"K", "R", "A", "T", "E", "L", "E", "P", "U", "I", "M", "Q",
        // "C", "X", "O", "S"};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
