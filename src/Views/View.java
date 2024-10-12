package Views;

import Utils.Size;
import Utils.Console;
import Utils.Point;

/**
 * Represents an abstract view
 */
abstract class View {
    public final Point location;
    public final Size size;

    public View(Point location, Size size){
        this.location = location;
        this.size = size;
    }

    /**
     * Clears the entire view
     */
    protected void clearView(){
        var str = " ".repeat(size.width);
        for(int y = location.y; y < location.y + size.height; y++){
            Console.setCursorPosition(new Point(location.x, y));
            System.out.println(str);
        }
    }

    /**
     * Updates the components in the view
     */
    public abstract void update();

    /**
     * Initializes the components of the view
     */
    protected abstract void initializeView();
}
