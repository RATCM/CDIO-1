package Views;

import Utils.Size;
import Utils.Console;
import Utils.Point;

public abstract class View {
    public final Point location;
    public final Size size;

    public View(Point location, Size size){
        this.location = location;
        this.size = size;
    }

    protected void clearView(){
        var str = " ".repeat(size.width);
        for(int y = location.y; y < location.y + size.height; y++){
            Console.setCursorPosition(new Point(location.x, y));
            System.out.println(str);
        }
    }

    public abstract void update();
    protected abstract void initializeView();
}
