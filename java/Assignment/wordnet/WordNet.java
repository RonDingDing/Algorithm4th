import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.SET;

public class WordNet {
    private final LinearProbingHashST<String, SET<Integer>> nounToId;
    private final LinearProbingHashST<Integer, String> idToSyn;
    private final SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new java.lang.IllegalArgumentException();
        }
        int numOfEntry = 0;
        // 处理synsets，使得其变成noun为键，id集合为值的字典nounToId; 然后变成id为键, syn为值的字典idToSyn
        nounToId = new LinearProbingHashST<String, SET<Integer>>();
        idToSyn = new LinearProbingHashST<Integer, String>();
        In insyn = new In(synsets);
        while (!insyn.isEmpty()) {
            String line = insyn.readLine();
            String[] content = line.split(",", 3);
            int id = Integer.parseInt(content[0]);
            String syn = content[1];
            idToSyn.put(id, syn);
            String[] words = content[1].split(" ");
            for (String noun : words) {
                if (nounToId.contains(noun)) {
                    nounToId.get(noun).add(id);
                } else {
                    SET<Integer> newset = new SET<Integer>();
                    newset.add(id);
                    nounToId.put(noun, newset);
                }
            }
            numOfEntry += 1;
        }
        Digraph digraph = new Digraph(numOfEntry);
        // 处理hypernyms，使得其变成有向图
        In inhyper = new In(hypernyms);
        while (!inhyper.isEmpty()) {
            String line = inhyper.readLine();
            String[] content = line.split(",");
            int id = Integer.parseInt(content[0]);
            for (int i = 1; i < content.length; i++) {
                int num = Integer.parseInt(content[i]);
                digraph.addEdge(id, num);
            }
        }

        DirectedCycle t = new DirectedCycle(digraph);
        if (t.hasCycle()) {
            throw new java.lang.IllegalArgumentException();
        }

        sap = new SAP(digraph);
    }


    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounToId.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) {
            throw new java.lang.IllegalArgumentException();
        }

        String newWord = word.replace(' ', '_');
        return nounToId.contains(newWord);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if ((!isNoun(nounA)) || (!isNoun(nounB))) {
            throw new java.lang.IllegalArgumentException();
        }
        String newA = nounA.replace(' ', '_');
        String newB = nounB.replace(' ', '_');
        SET<Integer> idA = nounToId.get(newA);
        SET<Integer> idB = nounToId.get(newB);
        return sap.length(idA, idB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if ((!isNoun(nounA)) || (!isNoun(nounB))) {
            throw new java.lang.IllegalArgumentException();
        }
        String newA = nounA.replace(' ', '_');
        String newB = nounB.replace(' ', '_');
        SET<Integer> idA = nounToId.get(newA);
        SET<Integer> idB = nounToId.get(newB);
        int ancestor = sap.ancestor(idA, idB);
        return idToSyn.get(ancestor);
    }


    public static void main(String[] args) {


    }
}
