import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private ArrayList<LineSegment> ls;

    private void add(LinkedList<Point> list,Point p){
        if (list.size() >= 3) {
            list.add(p);
            Collections.sort(list);
            if (p == list.getFirst()) {
                ls.add(new LineSegment(p, list.getLast()));
            }
        }
    }
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {
            if (points[i - 1].compareTo(points[i])==0) {
                throw new IllegalArgumentException();
            }
        }
        ls = new ArrayList<>();
        Point[] b = copy(points);

        LinkedList<Point> list = new LinkedList<>();
        for (int i = 0; i < b.length; i++) {
            Arrays.sort(points, b[i].slopeOrder());
            double prevslope = Double.NEGATIVE_INFINITY;
            for (int j = 0; j < points.length; j++) {
                if (j == 0 || points[j].slopeTo(b[i]) != prevslope) {
                    add(list,b[i]);
                    list.clear();
                }
                list.add(points[j]);
                prevslope = points[j].slopeTo(b[i]);
            }
            add(list,b[i]);
            list.clear();
        }

    }

    private static Point[] copy(Point[] points) {
        Point[] copy = new Point[points.length];
        for (int i = 0; i < points.length; i++) copy[i] = points[i];

        return copy;
    }

    public int numberOfSegments() {
        return ls.size();
    }

    public LineSegment[] segments() {
        LineSegment[] r = new LineSegment[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            r[i] = ls.get(i);
        }
        return r;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
