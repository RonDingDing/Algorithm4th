public class SortCompare {
    public static double time(String algs, Double[] a) {
        Stopwatch timer = new Stopwatch();
        if (algs.equals("Insertion"))
            Insertion.sort(a);
        if (algs.equals("Selection"))
            Selection.sort(a);        
        if (algs.equals("Shell"))
            Shell.sort(a);
        // if (algs.equals("Merge"))
        //     Merge.sort(a);
        // if (algs.equals("Quick"))
        //     Quick.sort(a);
        // if (algs.equals("Heap"))
        //     Heap.sort(a);
        double time_elapsed = timer.elapsedTime();
        return time_elapsed;
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < N; t++) {
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String [] args){
        String algs1 = args[0];
        String algs2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(algs1, N, T);
        double t2 = timeRandomInput(algs2, N, T);
        StdOut.printf("For %d random Doubles\n    %s is", N, algs1);
        StdOut.printf(" %.1f times faster than %s\n", t2/t1, algs2);
    }
}