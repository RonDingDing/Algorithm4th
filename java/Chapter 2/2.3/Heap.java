import edu.princeton.cs.algs4.StdOut;

public class Heap {
    public static void sort(Comparable[] pq) {
        int N = pq.length;
        for (int k = N / 2; k >= 1; k--) sink(pq, k, N);
        while (N > 1) {
            exch(pq, 1, N);
            sink(pq, 1, --N);
        }
    }

    private static void swim(Comparable[] pq, int k) {
        while (k > 1 && less(pq, k / 2, k)) {
            exch(pq, k, k / 2);
            k /= 2;
        }
    }

    private static void sink(Comparable[] pq, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq, j, j + 1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }


    private static boolean less(Comparable[] pq, int i, int j) {
        // 此时堆的起始值为0 需要减一
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    private static void exch(Comparable[] pq, int i, int j) {
        Comparable temp = pq[j - 1];
        pq[j - 1] = pq[i - 1];
        pq[i - 1] = temp;
    }

    public static void main(String[] args) {
        //String[] a = In.readStrings();
        String[] a = {"d", "a", "c", "b", "r", "p", "o", "h"};
        sort(a);
        for (String s : a) {
            StdOut.println(s);
        }
    }
}
