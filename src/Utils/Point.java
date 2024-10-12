package Utils;

/**
 * This represents a 2d point in space
 */
public class Point {
    public final int x;
    public final int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Sums the 2 points
     * 
     * @param p1  
     * @param p2
     * @return the resulting point from the addition
     */
    public static Point Add(Point p1, Point p2){
        return new Point(p1.x+p2.x, p1.y+p2.y);
    }
    
    /**
     * Subtracts the 2 points
     * 
     * @param p1  
     * @param p2
     * @return the resulting point from the subtraction
     */
    public static Point Subtract(Point p1, Point p2){
        return new Point(p1.x-p2.x, p1.y-p2.y);
    }
}
