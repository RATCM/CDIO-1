package Utils;

public class Point {
    public int x;
    public int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public static Point Add(Point p1, Point p2){
        return new Point(p1.x+p2.x, p1.y+p2.y);
    }
    
    public static Point Subtract(Point p1, Point p2){
        return new Point(p1.x-p2.x, p1.y-p2.y);
    }
}
