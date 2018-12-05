

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {

    private boolean solvable;
    private int solveMove;
    private Stack<Board> resultStack;

    private class SearchNode implements Comparable<SearchNode> {
        private final int manhattan;
        private final Board board;
        private final SearchNode parent;
        private final int moveStep;
        private final boolean isTwin;

        public SearchNode(Board initial, int moves, SearchNode parent, boolean isTwin) {
            this.board = initial;
            this.manhattan = board.manhattan() + moves;
            this.parent = parent;
            this.moveStep = moves;
            this.isTwin = isTwin;
        }

        public int compareTo(SearchNode that) {
            if (this.manhattan > that.manhattan) {
                return 1;
            } else if (this.manhattan < that.manhattan) {
                return -1;
            } else {
                return 0;
            }
        }
    }


    public Solver(Board initial) {
        if (initial == null) {
            throw new java.lang.NullPointerException();
        }

        MinPQ<SearchNode> nodeQueue = new MinPQ<>();
        SearchNode firstNode = new SearchNode(initial, 0, null, false);
        SearchNode twinNode = new SearchNode(initial.twin(), 0, null, true);
        nodeQueue.insert(twinNode);
        nodeQueue.insert(firstNode);
        solve(nodeQueue);
    }


    private void solve(MinPQ<SearchNode> nodeQueue) {
        while (!nodeQueue.isEmpty()) {
            SearchNode currentNode = nodeQueue.delMin();
            Board bord = currentNode.board;
            if (bord.isGoal()) {
                if (!currentNode.isTwin) {
                    resultStack = new Stack<Board>();

                    while (currentNode.parent != null) {
                        resultStack.push(currentNode.board);
                        currentNode = currentNode.parent;
                    }
                    resultStack.push(currentNode.board);
                    solvable = true;
                    solveMove = resultStack.size() - 1;
                    break;
                } else {
                    solvable = false;
                    solveMove = -1;
                    break;
                }
            } else {
                for (Board eachBoard : currentNode.board.neighbors()) {
                    SearchNode neiborNode = new SearchNode(eachBoard, currentNode.moveStep + 1, currentNode, currentNode.isTwin);
                    if (currentNode.parent == null) {
                        nodeQueue.insert(neiborNode);
                    } else if (!currentNode.parent.board.equals(neiborNode.board)) {
                        nodeQueue.insert(neiborNode);
                    }
                }
            }
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public Iterable<Board> solution() {
        return resultStack;
    }

    public int moves() {
        return solveMove;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int nn = in.readInt();
        int[][] blocks = new int[nn][nn];
        for (int i = 0; i < nn; i++)
            for (int j = 0; j < nn; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}