package Utils;

/**
 * This represents a size in 2d space
 */
// Some IDE's might want to change this to a record,
// And we don't want them to do that.
@SuppressWarnings("All")
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
     * @param s1 the first size
     * @param s2 the second size
     * @return the resulting size from the addition
     */
    // Java complains that this method isn't used anywhere.
    @SuppressWarnings("unused")
    public static Size Add(Size s1, Size s2){
        return new Size(s1.width+s2.width, s1.height+s2.height);
    }
    
    /**
     * Subtracts the 2 sizes
     * 
     * @param s1 the first size
     * @param s2 the second size
     * @return the resulting size from the subtraction
     */
    // Java complains that this method isn't used anywhere.
    @SuppressWarnings("unused")
    public static Size Subtract(Size s1, Size s2){
        return new Size(s1.width-s2.width, s1.height-s2.height);
    }
}
