import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class KdTree {

    private Node root;
    private int num;

    public KdTree() {
        root = null;
        num = 0;
    }

    public static void main(String[] args) {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(0.7, 0.2));
        tree.insert(new Point2D(0.5, 0.4));
        tree.insert(new Point2D(0.2, 0.3));
        tree.insert(new Point2D(0.4, 0.7));
        tree.insert(new Point2D(0.9, 0.6));
 
        // tree.insert(new Point2D(0.372, 0.497));  //A
        // tree.insert(new Point2D(0.564, 0.413)); // B
        // tree.insert(new Point2D(0.226, 0.577));  //C
        // tree.insert(new Point2D(0.144, 0.179)); //D
        // tree.insert(new Point2D(0.083, 0.51)); //E
        // tree.insert(new Point2D(0.32, 0.708)); // F
        // tree.insert(new Point2D(0.417, 0.362)); // G
        // tree.insert(new Point2D(0.862, 0.825)); // H
        // tree.insert(new Point2D(0.785, 0.725)); // I
        // tree.insert(new Point2D(0.499, 0.208)); // J


        // Point2D pp = new Point2D(0.38, 0.93); // F
        // StdOut.println();
        // tree.nearest(pp);
//        tree.draw();
////        RectHV a = new RectHV(0.2, 0.35, 0.55, 0.8);
//        StdDraw.setPenRadius(0.015);
//        StdDraw.setPenColor(Color.green);
//
//
//        Point2D pp = new Point2D(0.2, 0.93);
//        pp.draw();
//        StdOut.print(tree.nearest(pp));
//        for (int i = 0; i < 20000; i++) {
//            tree.insert(new Point2D(StdRandom.uniform(), StdRandom.uniform()));
//        }
//
//
//        tree.draw();
//        StdOut.print(tree.size());
    }

    public int size() {
        return num;
    }

    public boolean isEmpty() {
        return num == 0;
    }


    private Node insert(Node node, Point2D p, boolean color) {

        if (node == null) {
            node = new Node(p, color, null, null);
            num += 1;
            return node;
        }
        int cmp = compare(p, node.point, color);
        if (cmp < 0) {
            node.leftOrDown = insert(node.leftOrDown, p, flip(color));
        } else if (node.point.x() == p.x() && node.point.y() == p.y()) {
            return node;
        } else {
            node.rightOrUp = insert(node.rightOrUp, p, flip(color));
        }
        return node;
    }


    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        root = insert(root, p, true);

//
//        if (p == null) throw new IllegalArgumentException();
//        if (isEmpty()) {
//            root = new Node(p, true, null, null);
//            num += 1;
//            return;
//        }
//
//        Node currentNode = root;
//        Point2D currentPoint = root.point;
//        boolean currentColor = true;
//        while (true) {
//            int cmp = compare(p, currentPoint, currentColor);
//            if (p.x() == currentPoint.x() && p.y() == currentPoint.y()) {
//                StdOut.print(p.toString() + " already in tree!\n");
//                return;
//            }
//
//            if (cmp < 0) {
//                Node nextNode = currentNode.leftOrDown;
//                currentColor = flip(currentColor);
//                if (nextNode == null) {
//                    if (!currentColor) {
//                        currentNode.leftOrDown = new Node(p, currentColor, null, null);
//                    } else {
//                        currentNode.leftOrDown = new Node(p, currentColor, null, null);
//                    }
//                    num += 1;
//                    return;
//                }
//                currentNode = nextNode;
//                currentPoint = nextNode.point;
//
//            } else {
//                Node nextNode = currentNode.rightOrUp;
//                currentColor = flip(currentColor);
//                if (nextNode == null) {
//                    if (!currentColor) {
//                        currentNode.rightOrUp = new Node(p, currentColor, null, null);
//                    } else {
//                        currentNode.rightOrUp = new Node(p, currentColor, null, null);
//                    }
//                    num += 1;
//                    return;
//                }
//                currentNode = nextNode;
//                currentPoint = nextNode.point;
//
//            }
//        }
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            return false;
        }
        return get(root, p, true) != null;
    }

    private Node get(Node node, Point2D p, boolean color) {

        if (node == null) return null;
        int cmp = compare(p, node.point, color);
        if (cmp < 0) return get(node.leftOrDown, p, flip(color));
        else if (node.point.x() == p.x() && node.point.y() == p.y()) return node;
        else return get(node.rightOrUp, p, flip(color));


    }
//        if (p == null) throw new IllegalArgumentException();
//        if (isEmpty()) {
//            return false;
//        }
//
//        Node currentNode = root;
//        Point2D currentPoint = root.point;
//        boolean currentColor = true;
//        while (true) {
//            int cmp = compare(p, currentPoint, currentColor);
//            if (p.x() == currentPoint.x() && p.y() == currentPoint.y()) {
//                return true;
//            }
//            if (cmp < 0) {
//                Node nextNode = currentNode.leftOrDown;
//
//                currentColor = flip(currentColor);
//                if (nextNode == null) {
//                    return false;
//                }
//                currentNode = nextNode;
//                currentPoint = nextNode.point;
//
//            } else {
//                Node nextNode = currentNode.rightOrUp;
//                currentColor = flip(currentColor);
//                if (nextNode == null) {
//                    return false;
//                }
//                currentNode = nextNode;
//                currentPoint = nextNode.point;
//
//            }
//        }

//
//    }


    private boolean flip(boolean currentColor) {
        return !currentColor;
    }


    private int compare(Point2D p, Point2D currentPoint, boolean currentColor) {

        if (currentColor) {
            return Double.compare(p.x(), currentPoint.x());
        } else {
            return Double.compare(p.y(), currentPoint.y());
        }
    }

    private void drawVertical(RectHV rect) {
        StdDraw.line(rect.xmax(), rect.ymin(), rect.xmax(), rect.ymax());
    }

    private void drawHorizontal(RectHV rect) {
        StdDraw.line(rect.xmin(), rect.ymax(), rect.xmax(), rect.ymax());
    }

    private void draw(Node node, RectHV larger) {
        double radius = 0.01;
        RectHV ld, ru;
        StdDraw.setPenRadius(radius);
        Color color = node.color ? Color.RED : Color.BLUE;
        StdDraw.setPenColor(color);
        if (larger == null) {
            ld = new RectHV(0, 0, node.point.x(), 1);
            drawVertical(ld);
            ru = new RectHV(node.point.x(), 0, 1, 1);

        } else if (!node.color) {
            ld = new RectHV(larger.xmin(), larger.ymin(), larger.xmax(), node.point.y());
            drawHorizontal(ld);
            ru = new RectHV(larger.xmin(), node.point.y(), larger.xmax(), larger.ymax());


        } else {
            ld = new RectHV(larger.xmin(), larger.ymin(), node.point.x(), larger.ymax());
            drawVertical(ld);
            ru = new RectHV(node.point.x(), larger.ymin(), larger.xmax(), larger.ymax());
        }


        if (node.leftOrDown != null) {
            draw(node.leftOrDown, ld);
        }
        if (node.rightOrUp != null) {
            draw(node.rightOrUp, ru);
        }


        // 画点
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(radius * 1.5);
        StdDraw.point(node.point.x(), node.point.y());


    }

    public void draw() {
        draw(root, null);
    }


    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        RectHV unitsquare = new RectHV(0, 0, 1, 1);
        return range(rect, root, unitsquare);
    }

    private Iterable<Point2D> range(RectHV rect, Node node, RectHV larger) {
        Queue<Point2D> result = new Queue<Point2D>();
        if (node == null) {
            return result;
        }

        if (rect.contains(node.point)) {
            result.enqueue(node.point);
        }

        if (node.color) { // 红色
            RectHV left = new RectHV(larger.xmin(), larger.ymin(), node.point.x(), larger.ymax());
            RectHV right = new RectHV(node.point.x(), larger.ymin(), larger.xmax(), larger.ymax());
            double xy = node.point.x();


            if (rect.xmax() < xy) { //不相交，在左边
                Iterable<Point2D> leftResult = range(rect, node.leftOrDown, left);
                for (Point2D p : leftResult) {
                    result.enqueue(p);
                }


            } else if (rect.xmin() > xy) { //不相交，在右边
                Iterable<Point2D> rightResult = range(rect, node.rightOrUp, right);
                for (Point2D p : rightResult) {
                    result.enqueue(p);
                }

            } else {
                Iterable<Point2D> leftResult = range(rect, node.leftOrDown, left);
                for (Point2D p : leftResult) {
                    result.enqueue(p);
                }
                Iterable<Point2D> rightResult = range(rect, node.rightOrUp, right);
                for (Point2D nnp : rightResult) {
                    result.enqueue(nnp);
                }

            }
        } else { // 蓝色
            RectHV up = new RectHV(larger.xmin(), node.point.y(), larger.xmax(), larger.ymax());
            RectHV down = new RectHV(larger.xmin(), larger.ymin(), larger.xmax(), node.point.y());


            double xy = node.point.y();
            if (rect.ymax() < xy) { //不相交，在下边
                Iterable<Point2D> downResult = range(rect, node.leftOrDown, down);
                for (Point2D nnp : downResult) {
                    result.enqueue(nnp);
                }

            } else if (rect.ymin() > xy) { //不相交，在上边
                Iterable<Point2D> upResult = range(rect, node.rightOrUp, up);
                for (Point2D nnp : upResult) {
                    result.enqueue(nnp);
                }
            } else {

                Iterable<Point2D> upResult = range(rect, node.rightOrUp, up);
                for (Point2D nnp : upResult) {
                    result.enqueue(nnp);
                }

                Iterable<Point2D> downResult = range(rect, node.leftOrDown, down);
                for (Point2D nnp : downResult) {
                    result.enqueue(nnp);
                }
            }
        }
        return result;
    }


    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) {
            return null;
        }
        RectHV unitsquare = new RectHV(0, 0, 1, 1);
        return nearest(p, root, unitsquare, root.point, Double.POSITIVE_INFINITY);
    }

    private Point2D nearest(Point2D p, Node node, RectHV larger, Point2D currentChampion, double distance) {
        if (node == null) {
            return currentChampion;
        }
        double newDistance = node.point.distanceSquaredTo(p);
        if (newDistance < distance) {
            currentChampion = node.point;
            distance = newDistance;
        }

        if (node.color) { // 红色
            RectHV left = new RectHV(larger.xmin(), larger.ymin(), node.point.x(), larger.ymax());
            RectHV right = new RectHV(node.point.x(), larger.ymin(), larger.xmax(), larger.ymax());
            if (left.contains(p)) {
                currentChampion = nearest(p, node.leftOrDown, left, currentChampion, distance);
                distance = p.distanceSquaredTo(currentChampion);
                double toRight = right.distanceSquaredTo(p);
                if (distance > toRight) {
                    currentChampion = nearest(p, node.rightOrUp, right, currentChampion, distance);
                }


            } else {
                currentChampion = nearest(p, node.rightOrUp, right, currentChampion, distance);
                distance = p.distanceSquaredTo(currentChampion);
                double toLeft = left.distanceSquaredTo(p);
                if (distance > toLeft) {
                    currentChampion = nearest(p, node.leftOrDown, left, currentChampion, distance);
                }
            }
        } else { // 蓝色
            RectHV up = new RectHV(larger.xmin(), node.point.y(), larger.xmax(), larger.ymax());
            RectHV down = new RectHV(larger.xmin(), larger.ymin(), larger.xmax(), node.point.y());
            if (down.contains(p)) {
                currentChampion = nearest(p, node.leftOrDown, down, currentChampion, distance);
                distance = p.distanceSquaredTo(currentChampion);
                double toUp = up.distanceSquaredTo(p);
                if (distance > toUp) {
                    currentChampion = nearest(p, node.rightOrUp, up, currentChampion, distance);
                }

            } else {
                currentChampion = nearest(p, node.rightOrUp, up, currentChampion, distance);
                distance = p.distanceSquaredTo(currentChampion);
                double toDown = down.distanceSquaredTo(p);
                if (distance > toDown) {
                    currentChampion = nearest(p, node.leftOrDown, down, currentChampion, distance);
                }


            }
        }
        return currentChampion;
    }

    private static class Node {
        private final Point2D point;
        private final boolean color;
        private Node leftOrDown;
        private Node rightOrUp;


        public Node(Point2D point, boolean color, Node leftOrDown, Node rightOrUp) {
            this.point = point;
            this.color = color;
            this.leftOrDown = leftOrDown;
            this.rightOrUp = rightOrUp;
        }

    }


}
