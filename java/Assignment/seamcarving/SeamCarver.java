import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class SeamCarver {
    private Picture picture;
    private int width;
    private int height;
    private Double[][] energies;
    private Bag<Integer>[] adj;
    public Queue<Integer> postorder;
    private boolean[] marked;

    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }
        this.picture = picture;
        this.width = picture.width();
        this.height = picture.height();
        this.energies = new Double[height][width];
        this.adj = (Bag<Integer>[]) new Bag[this.width * this.height];
        this.postorder = new Queue<Integer>();
        this.marked = new boolean[this.width * this.height];
        this.postorder = topologicalOrder();

    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }


    public double energy(int x, int y) {
//        int colorthis = this.picture.getRGB(x, y);
//        int colorthisred = (colorthis >> 16) & 0xFF;
//        int colorthisGreen = (colorthis >> 8) & 0xFF;
//        int colorthisblue = (colorthis >> 0) & 0xFF;
//        StdOut.print("(");
//        StdOut.print(colorthisred);
//        StdOut.print(", ");
//        StdOut.print(colorthisGreen);
//        StdOut.print(", ");
//        StdOut.print(colorthisblue);
//        StdOut.print(")");


        if (x < 0 || x > this.width - 1 || y < 0 || y > this.height - 1) {
            throw new IllegalArgumentException();
        }

        if (this.energies[y][x] != null) {
            return this.energies[y][x];
        }

        if (x == 0 || x == this.width - 1 || y == 0 || y == this.height - 1) {
            this.energies[y][x] = 1000.0;
            return 1000.0;
        }


        int colorLeft = this.picture.getRGB(x - 1, y);
        int colorRight = this.picture.getRGB(x + 1, y);
        int colorUp = this.picture.getRGB(x, y - 1);
        int colorDown = this.picture.getRGB(x, y + 1);

        int colorLeftRed = (colorLeft >> 16) & 0xFF;
        int colorLeftGreen = (colorLeft >> 8) & 0xFF;
        int colorLeftBlue = (colorLeft >> 0) & 0xFF;

        int colorUpRed = (colorUp >> 16) & 0xFF;
        int colorUpGreen = (colorUp >> 8) & 0xFF;
        int colorUpBlue = (colorUp >> 0) & 0xFF;

        int colorRightRed = (colorRight >> 16) & 0xFF;
        int colorRightGreen = (colorRight >> 8) & 0xFF;
        int colorRightBlue = (colorRight >> 0) & 0xFF;

        int colorDownRed = (colorDown >> 16) & 0xFF;
        int colorDownGreen = (colorDown >> 8) & 0xFF;
        int colorDownBlue = (colorDown >> 0) & 0xFF;

        double deltax = Math.pow(colorRightRed - colorLeftRed, 2) + Math.pow(colorRightGreen - colorLeftGreen, 2) + Math.pow(colorRightBlue - colorLeftBlue, 2);
        double deltay = Math.pow(colorUpRed - colorDownRed, 2) + Math.pow(colorUpGreen - colorDownGreen, 2) + Math.pow(colorUpBlue - colorDownBlue, 2);
        double energy = Math.sqrt(deltax + deltay);
        this.energies[y][x] = energy;

        return energy;

    }

    public int[] findVerticalSeam() {
        return null;
    }

    public int[] findHorizontalSeam() {
        return null;
    }

    public void removeHorizontalSeam(int[] horizontalSeam) {

    }

    public void removeVerticalSeam(int[] horizontalSeam) {

    }

    public Picture picture() {
        return this.picture;
    }


    public int getVertex(int x, int y) {
        return y * width + x;
    }

    public Iterable<Integer> getAdj(int v) {
        int y = v / this.width;
        int x = v % this.height;
        return getAdj(x, y);
    }


    public Iterable<Integer> getAdj(int x, int y) {
        Bag<Integer> result = new Bag<Integer>();
        int vertex = getVertex(x, y);
        if (vertex > this.width * this.height - 1) {
            return result;
        }
        if (this.adj[vertex] != null) return this.adj[vertex];


        if (y == height - 1) {
        } else {
            if (x == 0) {
                result.add(getVertex(x, y + 1));
                result.add(getVertex(x + 1, y + 1));
            } else if (x == width - 1) {
                result.add(getVertex(x - 1, y + 1));
                result.add(getVertex(x, y + 1));
            } else if (x < width - 1 && y < height - 1) {
                result.add(getVertex(x - 1, y + 1));
                result.add(getVertex(x, y + 1));
                result.add(getVertex(x + 1, y + 1));
            }
        }
        this.adj[vertex] = result;
        return result;
    }

    private Queue<Integer> topologicalOrder() {
        int num = height * width;
        for (int v = 0; v < num; v++) {
            if (!marked[v]) dfs(v);
        }
        return postorder;

    }

    private void dfs(int v) {
        marked[v] = true;

        for (int w : getAdj(v)) {
            if (!marked[w]) {
                dfs(w);
            }
        }
        postorder.enqueue(v);
    }

    public static void main(String[] args) {
        Picture picture = new Picture("D:\\Coding\\Algorithm4th\\Algorithm4th\\java\\Assignment\\seamcarving\\3x4.png");
        SeamCarver sc = new SeamCarver(picture);

        StdOut.print("Width: ");
        StdOut.print(sc.width());
        StdOut.print("   Height: ");
        StdOut.print(sc.height());
        StdOut.println();
        StdOut.println();

        for (int y = 0; y < sc.height(); y++) {
            for (int x = 0; x < sc.width(); x++) {
                StdOut.printf("%.0f", sc.energy(x, y));
                StdOut.print(", ");

            }
            StdOut.println();
        }

        for (int j : sc.postorder) {
            StdOut.print(j);
            StdOut.print(", ");
        }


    }
}
