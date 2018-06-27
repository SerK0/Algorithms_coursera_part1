import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> ls;

    public BruteCollinearPoints(Point[] points) {
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
        int size = points.length;
        for (int p = 0; p < size; p++) {
            for (int q = p + 1; q < size; q++) {
                for (int r = q + 1; r < size; r++) {
                    for (int s = r + 1; s < size; s++) {
                        double s1 = points[p].slopeTo(points[q]);
                        double s2 = points[p].slopeTo(points[r]);
                        double s3 = points[p].slopeTo(points[s]);
                        if ((s1 == s2) & (s1 == s3) & (s2 == s3)) {
                            ls.add(new LineSegment(points[p], points[s]));
                        }
                    }
                }
            }
        }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
