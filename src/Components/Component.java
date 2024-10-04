package Components;

import Utils.Point;
import Utils.Size;

public abstract class Component {
    public Point location;
    public Size size;

    public Component(Point location, Size size){
        this.location = location;
        this.size = size;
    }
    
    public abstract void update();
}
