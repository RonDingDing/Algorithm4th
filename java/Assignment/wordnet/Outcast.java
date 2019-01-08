import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet wordNet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        if (nouns.length < 2) {
            throw new IllegalArgumentException();
        }
        int outcastDistance = -1;
        String outcast = "";
        for (String noun : nouns) {
            if (noun == null) throw new IllegalArgumentException();
            if (!wordNet.isNoun(noun)) throw new IllegalArgumentException();

            int nounDistSum = 0;
            for (String otherNoun : nouns) {
                nounDistSum += wordNet.distance(noun, otherNoun);
            }
            if (nounDistSum > outcastDistance) {
                outcast = noun;
                outcastDistance = nounDistSum;
            }
        }
        return outcast;

    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
