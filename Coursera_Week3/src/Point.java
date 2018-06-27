import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return ("(" + this.x + "," + this.y + ")");
    }

    public int compareTo(Point that) {
        if ((this.y<that.y)||((this.y==that.y)&&(this.x<that.x))) {return -1;}
        if ((that.y == this.y)&&(that.x == this.x)) return 0;
        else return 1;
    }

    public double slopeTo(Point that) {
        if (this.compareTo(that)==0) {return Double.NEGATIVE_INFINITY;}
        if (that.x == this.x){return Double.POSITIVE_INFINITY;}
        if ((that.y == this.y)&(that.x != this.x)) {return 0;}
        double slope = (double)(that.y - this.y) / (that.x - this.x);
        return slope;
    }

    public Comparator<Point> slopeOrder(){
        return new SlopeOrder();
    }
    private class SlopeOrder implements Comparator<Point>{
        public int compare(Point p1, Point p2) {
            if (Point.this.slopeTo(p1)>Point.this.slopeTo(p2)) {return +1;}
            if (Point.this.slopeTo(p1)<Point.this.slopeTo(p2)) {return -1;}
            return 0;
        }
    }

    public static void main(String[] args) {

        Point p1 = new Point(115,365);
        Point p2 = new Point(9,365);
        StdOut.println(p1.slopeTo(p2));
    }
}
