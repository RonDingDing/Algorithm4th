import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class StaticSETofInts2 {
    private int[] a;

    public StaticSETofInts2(int[] keys) {
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            a[i] = keys[i]; // defensive copy
        Arrays.sort(a);
    }

    public boolean contains(int key) {
        return rank(key) != -1;
    }

    private int rank(int key) { // Binary search.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) { // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] w = In.readInts(args[0]);
        StaticSETofInts2 set = new StaticSETofInts2(w);
        while (!StdIn.isEmpty()) { // Read key, print if not in whitelist.
            int key = StdIn.readInt();
            if (set.rank(key) == -1)
                StdOut.println(key);
        }
    }
}