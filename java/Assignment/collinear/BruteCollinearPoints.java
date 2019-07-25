import edu.princeton.cs.algs4.Merge;

import java.util.ArrayList;

public class BruteCollinearPoints {

    private Point[] pts;
    private ArrayList<LineSegment> lines;

    public BruteCollinearPoints(Point[] points) {
        // deep copy to avoid mutating the constructor argument
        checkNullArgument(points);
        this.pts = new Point[points.length];
        for (int k = 0; k < points.length; k++) {
            pts[k] = points[k];
        }

        checkDuplicatedElement(pts); // mergesort
        lines = new ArrayList<LineSegment>(); // to avoid NullPointerException

        for (int k1 = 0; k1 < pts.length; k1++) {
            for (int k2 = k1 + 1; k2 < pts.length; k2++) {
                for (int k3 = k2 + 1; k3 < pts.length; k3++) {

                    if (pts[k1].slopeTo(pts[k2]) == pts[k1].slopeTo(pts[k3])) {
                        for (int k4 = k3 + 1; k4 < pts.length; k4++) {
                            if (pts[k1].slopeTo(pts[k2]) == pts[k1].slopeTo(pts[k4])) {
                                // k1, k2, k3, k4 are already sorted
                                lines.add(new LineSegment(pts[k1], pts[k4]));
                            }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return lines.size();
    }

    // line segment will contain at most 4 collinear points
    public LineSegment[] segments() {

        return lines.toArray(new LineSegment[numberOfSegments()]);
    }

    private void checkDuplicatedElement(Point[] points) {

        // sort input array by natural order
        Merge.sort(points);

        // duplicated element
        for (int i = 1; i < points.length; i++) {
            if (points[i].slopeTo(points[i - 1]) == Double.NEGATIVE_INFINITY) {
                throw new IllegalArgumentException("Input contains repeated element!\n");
            }
        }

    }

    private void checkNullArgument(Point[] points) {

        // null array
        if (points == null) {
            throw new IllegalArgumentException("Input cannot be null!\n");
        }

        // null element
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("Input contains null element!\n");
            }
        }
    }

}
 
