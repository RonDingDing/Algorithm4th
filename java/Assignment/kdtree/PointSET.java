import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
    private SET<Point2D> pointset;

    public PointSET() {
        pointset = new SET<Point2D>();
    }

    public static void main(String[] args) {
//        PointSET tree = new PointSET();
//        for (int i = 0; i < 20000; i++) {
//            tree.insert(new Point2D(StdRandom.uniform(), StdRandom.uniform()));
//        }
//
//
//        tree.draw();
//        StdOut.print(tree.size());

    }

    public boolean isEmpty() {
        return pointset.isEmpty();
    }

    public int size() {
        return pointset.size();
    }

    public void insert(Point2D p) {
        if (p == null || p.x() < 0 || p.x() > 1 || p.y() < 0 || p.y() > 1)
            throw new IllegalArgumentException();


        pointset.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return pointset.contains(p);
    }

    public void draw() {
        StdDraw.setPenRadius(0.005);
        for (Point2D p : pointset) {
            StdDraw.point(p.x(), p.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        SET<Point2D> rangePoints = new SET<Point2D>();
        for (Point2D p : pointset) {
            if (rect.contains(p)) {
                rangePoints.add(p);
            }
        }
        return rangePoints;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (pointset.isEmpty()) return null;
        double distance = Double.POSITIVE_INFINITY;
        Point2D nearestPoint = null;
        for (Point2D np : pointset) {
            if (p.distanceSquaredTo(np) < distance) {
                nearestPoint = np;
                distance = p.distanceSquaredTo(np);
            }
        }
        return nearestPoint;
    }
}
