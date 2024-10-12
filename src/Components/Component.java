package Components;

import Utils.Color;
import Utils.Console;
import Utils.Point;
import Utils.Size;

/**
 * This represents an abstract component
 */
public abstract class Component {
    public final Point location;
    public final Size size;
    public Color backgroundColor;
    public Color forgroundColor;

    public Component(Point location, Size size){
        this.location = location;
        this.size = size;
        this.backgroundColor = Color.None;
        this.forgroundColor = Color.None;
    }

    public Component(Point location, Size size, Color backgroundColor, Color foregroundColor){
        this.location = location;
        this.size = size;
        this.backgroundColor = backgroundColor;
        this.forgroundColor = foregroundColor;
    }

    /**
     * Clears the entire component
     */
    public void clearComponent(){
        var str = " ".repeat(size.width);
        for(int y = location.y; y < location.y + size.height; y++){
            Console.setCursorPosition(new Point(location.x, y));
            System.out.println(str);
        }
    }

    /**
     * Updates the component and displays it
     */
    public abstract void update();
}
