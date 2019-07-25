import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private int width, height;
    private int[][] matrix;
    private double[][] energies;

    public SeamCarver(Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException();
        }
        this.width = picture.width();
        this.height = picture.height();
        this.matrix = new int[this.width][this.height];
        this.energies = new double[this.width][this.height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.matrix[i][j] = picture.getRGB(i, j);
            }
        }
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.energies[i][j] = computeEnergy(i, j);
            }
        }
    }


    private double computeEnergy(int x, int y) {
        if (x == 0 || x == this.width - 1 || y == 0 || y == this.height - 1) {
            return 1000.0;
        }
        int colorLeft = this.matrix[x - 1][y];
        int colorRight = this.matrix[x + 1][y];
        int colorUp = this.matrix[x][y - 1];
        int colorDown = this.matrix[x][y + 1];

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
        return energy;
    }

    public int height() {
        return this.height;
    }

    public int width() {
        return this.width;
    }

    public double energy(int x, int y) {
        if (x < 0 || x > this.width - 1 || y < 0 || y > this.height - 1)
            throw new IllegalArgumentException();
        return this.energies[x][y];
    }


    public Picture picture() {
        Picture pic = new Picture(this.width, this.height);
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                pic.setRGB(i, j, this.matrix[i][j]);
            }
        }
        return pic;
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam == null || seam.length != this.height || this.width <= 1) {
            throw new IllegalArgumentException();
        }
        boolean started = false;
        int last = 0;
        for (int j : seam) {
            if (j < 0 || j > this.width - 1) {
                throw new IllegalArgumentException();
            }
            if (!started) {
                started = true;

            } else if (Math.abs(j - last) > 1) {
                throw new IllegalArgumentException();

            }
            last = j;
        }


        int row = 0;
        for (int del : seam) {
            for (int col = del + 1; col < this.width; col++) {
                int thisRgb = this.matrix[col][row];
                this.matrix[col - 1][row] = thisRgb;
            }

            row += 1;
        }


        this.width--;

        int yy = 0;
        for (int del2 : seam) {
            int min;
            if (del2 - 1 < 0) {
                min = 0;
            } else {
                min = del2 - 1;
            }


            for (int xx = min; xx < this.width; xx++) {
                this.energies[xx][yy] = computeEnergy(xx, yy);
            }
            yy += 1;
        }


    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || seam.length != this.width || this.height <= 1) {
            throw new IllegalArgumentException();
        }
        boolean started = false;
        int last = 0;
        for (int j : seam) {
            if (j < 0 || j > this.height - 1) {
                throw new IllegalArgumentException();
            }
            if (!started) {
                started = true;

            } else if (Math.abs(j - last) > 1) {
                throw new IllegalArgumentException();

            }
            last = j;
        }


        int col = 0;
        for (int del : seam) {
            for (int row = del + 1; row < height(); row++) {
                int thisRgb = this.matrix[col][row];
                this.matrix[col][row - 1] = thisRgb;
            }
            col += 1;

        }

        this.height--;

        int xx = 0;
        for (int del2 : seam) {
            int min;
            if (del2 - 1 < 0) {
                min = 0;
            } else {
                min = del2 - 1;
            }

            for (int yy = min; yy < this.height; yy++) {
                this.energies[xx][yy] = computeEnergy(xx, yy);
            }
            xx += 1;
        }
//
//        for (int i = 0; i < this.width; i++) {
//            for (int j = 0; j < this.height; j++) {
//                this.energies[i][j] = computeEnergy(i, j);
//            }
//        }
    }

    public int[] findHorizontalSeam() {


        int[] seam = new int[this.width];
        int[][] edgeTo = new int[this.width][this.height];
        double[][] distTo = new double[this.width][this.height];

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (i == 0) {
                    distTo[i][j] = this.energies[i][j];
                } else {
                    distTo[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        for (int i = 0; i < width - 1 && i >= 0; i++) {
            for (int j = 0; j < this.height; j++) {
                relaxHorizontalSeam(edgeTo, distTo, i, j);
            }
        }
        double shortest = Double.POSITIVE_INFINITY;
        int index = 0;
        for (int yy = 0; yy < this.height - 1; yy++) {
            if (this.width - 1 >= 0 && distTo[this.width - 1][yy] < shortest) {
                shortest = distTo[this.width - 1][yy];
                index = yy;
            }
        }


        seam[this.width - 1] = index;
        for (int j = this.width - 2; j >= 0; j--) {
            seam[j] = edgeTo[j + 1][seam[j + 1]];
        }


        return seam;
    }

    public int[] findVerticalSeam() {
        int[] seam = new int[this.height];
        int[][] edgeTo = new int[this.width][this.height];
        double[][] distTo = new double[this.width][this.height];

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (j == 0) {
                    distTo[i][j] = this.energies[i][j];
                } else {
                    distTo[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

        for (int j = 0; j < this.height - 1 && j >= 0; j++) {
            for (int i = 0; i < width; i++) {
                relaxVerticalSeam(edgeTo, distTo, i, j);

            }
        }

        double shortest = Double.POSITIVE_INFINITY;
        int index = 0;
        for (int xx = 0; xx < this.width; xx++) {
            if (this.height - 1 >= 0 && distTo[xx][this.height - 1] < shortest) {
                shortest = distTo[xx][this.height - 1];
                index = xx;
            }
        }
        seam[this.height - 1] = index;
        for (int j = height - 2; j >= 0; j--) {
            seam[j] = edgeTo[seam[j + 1]][j + 1];
        }


        return seam;
    }

    private void relaxHorizontalSeam(int[][] edgeTo, double[][] distTo, int x, int y) {
        if (y > 0 && distTo[x + 1][y - 1] > distTo[x][y] + this.energies[x + 1][y - 1]) {
            distTo[x + 1][y - 1] = distTo[x][y] + this.energies[x + 1][y - 1];
            edgeTo[x + 1][y - 1] = y;
        }

        if (distTo[x + 1][y] > distTo[x][y] + this.energies[x + 1][y]) {
            distTo[x + 1][y] = distTo[x][y] + this.energies[x + 1][y];
            edgeTo[x + 1][y] = y;
        }
        if (y < this.height - 1 && distTo[x + 1][y + 1] > distTo[x][y] + this.energies[x + 1][y + 1]) {
            distTo[x + 1][y + 1] = distTo[x][y] + this.energies[x + 1][y + 1];
            edgeTo[x + 1][y + 1] = y;
        }
    }

    private void relaxVerticalSeam(int[][] edgeTo, double[][] distTo, int x, int y) {
        if (x > 0 && distTo[x - 1][y + 1] > distTo[x][y] + this.energies[x - 1][y + 1]) {
            distTo[x - 1][y + 1] = distTo[x][y] + this.energies[x - 1][y + 1];
            edgeTo[x - 1][y + 1] = x;
        }

        if (distTo[x][y + 1] > distTo[x][y] + this.energies[x][y + 1]) {
            distTo[x][y + 1] = distTo[x][y] + this.energies[x][y + 1];
            edgeTo[x][y + 1] = x;
        }
        if (x < this.width - 1 && distTo[x + 1][y + 1] > distTo[x][y] + this.energies[x + 1][y + 1]) {
            distTo[x + 1][y + 1] = distTo[x][y] + this.energies[x + 1][y + 1];
            edgeTo[x + 1][y + 1] = x;
        }
    }

    public static void main(String[] args) {
//        Picture picture = new Picture("D:\\Coding\\Algorithm4th\\Algorithm4th\\java\\Assignment\\seamcarving\\7x10.png");
//        SeamCarver sc = new SeamCarver(picture);
//        int[] seam = null;
////        for (int l : seam) {
////            StdOut.print(l);
////            StdOut.print(" ");
////        }
//        sc.removeHorizontalSeam(seam);


//        int[] seam2 = sc.findHorizontalSeam();
//        sc.removeHorizontalSeam(seam2);
//
//        Picture picture2 = sc.picture();
//        for (int j = 0; j < sc.height(); j++) {
//            for (int i = 0; i < sc.width(); i++) {
//
//                StdOut.printf("%9s", picture2.getRGB(i, j));
//            }
//            StdOut.println();
//        }
//        StdOut.println();
//
//
//        StdOut.print("Vertical: ");

//        for (int m : seam) {
//            StdOut.print(m);
//            StdOut.print(" ");
//        }
//        StdOut.println();


//        Picture picture3 = sc.picture();
//        for (int j = 0; j < sc.height(); j++) {
//            for (int i = 0; i < sc.width(); i++) {
//
//                StdOut.printf("%9s", picture3.getRGB(i, j));
//            }
//            StdOut.println();
//        }
//        StdOut.println();

//        StdOut.println();
//        Picture picture2 = sc.picture();
//        for (int j = 0; j < sc.height(); j++) {
//            for (int i = 0; i < sc.width(); i++) {
//
//                StdOut.printf("%9s", picture2.getRGB(i, j));
//            }
//            StdOut.println();
//        }
//        StdOut.println();
//
//        for (int j = 0; j < sc.height(); j++) {
//            for (int i = 0; i < sc.width(); i++) {
//
//                StdOut.printf("%9.0f", sc.energy(i, j));
//            }
//            StdOut.println();
//        }
//
//        sc.removeHorizontalSeam(seam);
//        StdOut.println();
//        Picture picture3 = sc.picture();
//        for (int j = 0; j < sc.height(); j++) {
//            for (int i = 0; i < sc.width(); i++) {
//
//                StdOut.printf("%9s", picture3.getRGB(i, j));
//            }
//            StdOut.println();
//        }
//
//        StdOut.println();
//        for (int j = 0; j < sc.height(); j++) {
//            for (int i = 0; i < sc.width(); i++) {
//
//                StdOut.printf("%9.0f", sc.energy(i, j));
//            }
//            StdOut.println();
//        }
//

    }

}


