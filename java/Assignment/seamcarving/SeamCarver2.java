import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class SeamCarver2 {
    private Picture picture;
    private int width;
    private int height;
    private Double[][] energies;
    private Bag<Integer> adj;

    public SeamCarver2(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }
        this.picture = picture;
        this.width = picture.width();
        this.height = picture.height();
        this.energies = new Double[height][width];
    }

    private Iterable<Integer> reverse() {
        Queue<Integer> postorder = new Queue<Integer>();
        boolean[] marked = new boolean[this.width * this.height];
        for (int i = 0; i < this.width * this.height; i++) {
            if (!marked[i]) {
                dfs(i, marked, postorder);
            }
        }
        Stack<Integer> reversePost = new Stack<Integer>();
        for (Integer j : postorder) {
            reversePost.push(j);
        }
        return reversePost;
    }


    private void dfs(int v, boolean[] marked, Queue<Integer> postorder) {
        marked[v] = true;
        for (int w : getAdj(v)) {
            if (!marked[w]) {
                dfs(w, marked, postorder);
            }
        }
        postorder.enqueue(v);
    }

    public int width() {
        return this.width;
    }

    public int height() {
        return this.height;
    }

    public double energy(int vertex) {
        int y = vertex / this.width;
        int x = vertex % this.width;
        if (x < 0 || x > this.width - 1 || y < 0 || y > this.height - 1) {
            throw new IllegalArgumentException();
        }
        return energy(x, y);

    }

    public double energy(int x, int y) {
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
        Integer[] edgeTo = new Integer[this.height * this.width];
        double[] distTo = new double[this.height * this.width];
        for (int i = 0; i < this.height * this.width; i++) {
            edgeTo[i] = null;
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        for (int i = 0; i < this.width; i++) {
            distTo[i] = 0;
        }

        for (Integer from : reverse()) {
            for (int to : getAdj(from)) {
                if (distTo[to] > distTo[from] + energy(to)) {
                    distTo[to] = distTo[from] + energy(to);
                    edgeTo[to] = from;
                }
            }
        }

        int index = this.width * (this.height - 2);
        double shortest = Double.POSITIVE_INFINITY;
        for (int i = this.width * (this.height - 2); i < this.width * (this.height - 1); i++) {
            if (distTo[i] < shortest) {
                shortest = distTo[i];
                index = i;
            }
        }

        Stack<Integer> resultStack = new Stack<Integer>();
        resultStack.push(index % width);
        while (distTo[index] != 0) {
            resultStack.push(index % width);
            index = edgeTo[index];

        }
        resultStack.push(index % width);

        int[] result = new int[height];
        int i = 0;
        for (int j : resultStack) {
            result[i] = j;
            i++;
        }
        return result;
    }


    public int[] findHorizontalSeam() {
        return null;
    }

    public void removeHorizontalSeam(int[] horizontalSeam) {


    }

    public void removeVerticalSeam(int[] horizontalSeam) {
        Picture newPicture = new Picture(width - 1, height);
        Double[][] newEnergies = new Double[height][width - 1];

        int row = 0;
        for (int del : horizontalSeam) {
            for (int j = 0; j < del; j++) {
                int thisRgb = this.picture().getRGB(j, row);
                newPicture.setRGB(j, row, thisRgb);
                double thisEnergy = this.energy(j, row);
                newEnergies[row][j] = thisEnergy;
            }

            for (int j = del + 1; j < width - 1; j++) {
                int nextRgb = this.picture().getRGB(j + 1, row);
                newPicture.setRGB(j, row, nextRgb);
                double nextEnergy = this.energy(j + 1, row);
                newEnergies[row][j] = nextEnergy;
            }
            row += 1;
        }
        this.picture = newPicture;
        this.energies = newEnergies;
        this.width = picture.width();
        this.height = picture.height();


    }

    public Picture picture() {
        return this.picture;
    }


    public int getVertex(int x, int y) {
        return y * width + x;
    }

    public Iterable<Integer> getAdj(int v) {

        int y = v / this.width;
        int x = v % this.width;
        if (x < 0 || x > this.width - 1 || y < 0 || y > this.height - 1) {
            throw new IllegalArgumentException();
        }
        return getAdj(x, y);
    }


    public Iterable<Integer> getAdj(int x, int y) {
        Bag<Integer> result = new Bag<Integer>();
        int vertex = getVertex(x, y);
        if (vertex > this.width * this.height - 1) {
            return result;
        }

        if (y == height - 1) {
        } else {
            if (x == 0) {
                result.add(getVertex(x + 1, y + 1));
                result.add(getVertex(x, y + 1));

            } else if (x == width - 1) {

                result.add(getVertex(x, y + 1));
                result.add(getVertex(x - 1, y + 1));
            } else if (x < width - 1 && y < height - 1) {
                result.add(getVertex(x + 1, y + 1));
                result.add(getVertex(x, y + 1));
                result.add(getVertex(x - 1, y + 1));

            }
        }

        return result;
    }


    public static void main(String[] args) {
        Picture picture = new Picture("D:\\Coding\\Algorithm4th\\Algorithm4th\\java\\Assignment\\seamcarving\\10x12.png");
        SeamCarver2 sc = new SeamCarver2(picture);
        for (int j : sc.findVerticalSeam()) {
            StdOut.print(j);
            StdOut.print(" ");
        }

//        for (int row = 0; row < sc.height(); row++) {
//            for (int col = 0; col < sc.width(); col++)
//                StdOut.printf("%9.0f ", sc.energy(col, row));
//            StdOut.println();
//        }
//        sc.removeVerticalSeam(sc.findVerticalSeam());
//        StdOut.println();
//        for (int row = 0; row < sc.height(); row++) {
//            for (int col = 0; col < sc.width(); col++)
//                StdOut.printf("%9.0f ", sc.energy(col, row));
//            StdOut.println();
//        }

        ;
    }
}
