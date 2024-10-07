package Components;

import Utils.Color;
import Utils.Console;
import Utils.Point;
import Utils.Size;

public abstract class Component {
    public final Point location;
    public final Size size;
    public final Color backgroundColor;
    public final Color forgroundColor;

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

    public void clearComponent(){
        var str = " ".repeat(size.width);
        for(int y = location.y; y < location.y + size.height; y++){
            Console.setCursorPosition(new Point(location.x, y));
            System.out.println(str);
        }
    }
    public abstract void update();
}
