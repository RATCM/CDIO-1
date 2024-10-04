package Utils;

public class Size {
    public int width;
    public int height;

    public Size(int width, int height){
        this.width = width;
        this.height = height;
    }

    public static Size Add(Size s1, Size s2){
        return new Size(s1.width+s2.width, s1.height+s2.height);
    }
    
    public static Size Subtract(Size s1, Size s2){
        return new Size(s1.width-s2.width, s1.height-s2.height);
    }
}
