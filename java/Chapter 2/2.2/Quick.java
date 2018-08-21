import java.util.Random;

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
        //if (hi <= lo) return;
        int M = (int)(StdRandom.uniform() * 15);
        if (hi <= lo + M) {
            Insertion.ssort(a, lo, hi);
            return;
        }

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
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
        String[] a = { "Q", "U", "I", "C", "K", "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
        // String[] a = {"K", "R", "A", "T", "E", "L", "E", "P", "U", "I", "M", "Q",
        // "C", "X", "O", "S"};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}