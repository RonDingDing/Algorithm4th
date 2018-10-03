import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3way {

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
        if (hi <= lo)
            return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0)
                exch(a, lt++, i++);
            else if (cmp > 0)
                exch(a, i, gt--);
            else
                i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int boy = lo, cat = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++boy], v))
                if (boy == hi)
                    break;
            while (less(v, a[--cat]))
                if (cat == lo)
                    break;
            if (boy >= cat)
                break;
            exch(a, boy, cat);
        }
        exch(a, lo, cat);
        return cat;
    }

    public static void main(String[] args) {
        // String[] a = In.readStrings();
        String[] a = {"R", "B", "W", "W", "R", "W", "B", "R", "R", "W", "B", "R"};
        // String[] a = {"K", "R", "A", "T", "E", "L", "E", "P", "U", "I", "M", "Q",
        // "C", "X", "O", "S"};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
