

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private Point[] pts;
    private ArrayList<LineSegment> lines;

    /**
     * constructor ~ n*nlg(n)
     *
     * @param points
     */
    public FastCollinearPoints(Point[] points) {

        checkNullArgument(points);
        this.pts = new Point[points.length];
        for (int k = 0; k < points.length; k++) {
            pts[k] = points[k];
        }

        checkDuplicatedElement(pts); // mergesort ~ nlg(n)
        lines = new ArrayList<LineSegment>(); // to avoid NullPointerException

        // IndexOutOfBoundsException for i < pts.length
        for (int i = 0; i < pts.length - 1; i++) {

            Double[] slopesB4 = new Double[i]; // slopes with points upstream
            Point[] pointsAf = new Point[pts.length - i - 1]; // points downstream

            for (int k = 0; k < i; k++) {
                slopesB4[k] = pts[i].slopeTo(pts[k]);
            }

            for (int j = 0; j < pts.length - i - 1; j++) {
                pointsAf[j] = pts[j + i + 1];
            }

            // sort upstream slopes by natural order ~ nlg(n)
            Arrays.sort(slopesB4);

            // sort downstream points by slope order to pts[i] ~ nlg(n)
            Arrays.sort(pointsAf, pts[i].slopeOrder());

            addSegment(slopesB4, pts[i], pointsAf); // ~ nlg(n)
        }
    }

    /**
     * add appropriate line segment ~ nlg(n)
     *
     * @param slopesB4: slopes of upstream points to point p
     * @param p:        the origin point
     * @param pointsAf: downstream points
     */
    private void addSegment(Double[] slopesB4, Point p, Point[] pointsAf) {

        int count = 1;
        double lastSlope = p.slopeTo(pointsAf[0]);

        for (int i = 1; i < pointsAf.length; i++) {
            double slope = p.slopeTo(pointsAf[i]);

            if (slope != lastSlope) {
                if (count >= 3 && !subSegment(lastSlope, slopesB4)) {
                    lines.add(new LineSegment(p, pointsAf[i - 1]));
                }
                count = 1;
            } else {
                count++; // the loop terminates with the last possible segment unchecked
            }

            lastSlope = slope;
        }

        // check the last point
        if (count >= 3 && !subSegment(lastSlope, slopesB4)) {
            lines.add(new LineSegment(p, pointsAf[pointsAf.length - 1]));
        }
    }

    /**
     * binary search the given slope in slopsB4 ~ lg(n)
     *
     * @param s:      the given slope
     * @param slopes: of upstream points to the origin point
     * @return
     */
    private boolean subSegment(double s, Double[] slopes) {

        int lo = 0;
        int hi = slopes.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (s < slopes[mid]) hi = mid - 1;
            else if (s > slopes[mid]) lo = mid + 1;
            else return true;
        }

        return false;
    }

    public int numberOfSegments() {
        return lines.size();
    }

    public LineSegment[] segments() {

        return lines.toArray(new LineSegment[numberOfSegments()]);
    }

    private void checkDuplicatedElement(Point[] points) {

        // sort input array by natural order
        Arrays.sort(points);

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

    public static void main(String[] args) {


    }
}

