package Components;

import Utils.Color;
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
        this.backgroundColor = Color.Black;
        this.forgroundColor = Color.White;
    }

    public Component(Point location, Size size, Color backgroundColor, Color foregroundColor){
        this.location = location;
        this.size = size;
        this.backgroundColor = backgroundColor;
        this.forgroundColor = foregroundColor;
    }

    public abstract void update();
}
