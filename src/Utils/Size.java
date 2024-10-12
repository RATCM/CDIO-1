package Utils;

/**
 * This represents a size in 2d space
 */
public class Size {
    public final int width;
    public final int height;

    public Size(int width, int height){
        this.width = width;
        this.height = height;
    }

    /**
     * Sums the 2 sizes
     * 
     * @param s1
     * @param s2
     * @return the resulting size from the addition
     */
    public static Size Add(Size s1, Size s2){
        return new Size(s1.width+s2.width, s1.height+s2.height);
    }
    
    /**
     * Subtracts the 2 sizes
     * 
     * @param s1
     * @param s2
     * @return the resulting size from the subtraction
     */
    public static Size Subtract(Size s1, Size s2){
        return new Size(s1.width-s2.width, s1.height-s2.height);
    }
}
