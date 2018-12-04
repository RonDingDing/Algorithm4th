
/**
 * Compilation:  javac FrequencyCounter.java
 * Execution:    java FrequencyCounter L < input.txt
 * Dependencies: ST.java StdIn.java StdOut.java
 * Data files:   https://algs4.cs.princeton.edu/31elementary/tnyTale.txt
 * https://algs4.cs.princeton.edu/31elementary/tale.txt
 * https://algs4.cs.princeton.edu/31elementary/leipzig100K.txt
 * https://algs4.cs.princeton.edu/31elementary/leipzig300K.txt
 * https://algs4.cs.princeton.edu/31elementary/leipzig1M.txt
 * <p>
 * Read in a list of words from standard input and print out
 * the most frequently occurring word that has length greater than
 * a given threshold.
 * <p>
 * % java FrequencyCounter 1 < tinyTale.txt
 * it 10
 * <p>
 * % java FrequencyCounter 8 < tale.txt
 * business 122
 * <p>
 * % java FrequencyCounter 10 < leipzig1M.txt
 * government 24763
 * <p>
 * <p>
 * <p>
 * The {@code FrequencyCounter} class provides a client for
 * reading in a sequence of words and printing a word (exceeding
 * a given length) that occurs most frequently. It is useful as
 * a test client for various symbol table implementations.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/31elementary">Section 3.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * <p>
 * The {@code FrequencyCounter} class provides a client for
 * reading in a sequence of words and printing a word (exceeding
 * a given length) that occurs most frequently. It is useful as
 * a test client for various symbol table implementations.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/31elementary">Section 3.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */

/**
 *  The {@code FrequencyCounter} class provides a client for 
 *  reading in a sequence of words and printing a word (exceeding
 *  a given length) that occurs most frequently. It is useful as
 *  a test client for various symbol table implementations.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/31elementary">Section 3.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdIn;

public class FrequencyCounter {

    // Do not instantiate.
    private FrequencyCounter() {
    }

    /**
     * Reads in a command-line integer and sequence of words from
     * standard input and prints out a word (whose length exceeds
     * the threshold) that occurs most frequently to standard output.
     * It also prints out the number of words whose length exceeds
     * the threshold and the number of distinct such words.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        ST<String, Integer> st = new ST<String, Integer>();

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
}