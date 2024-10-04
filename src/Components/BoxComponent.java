package Components;

import Utils.Console;
import Utils.Point;
import Utils.Size;

public class BoxComponent extends Component {
    private char _fillChar;

    public BoxComponent(Point location, Size size, char fillChar) {
        super(location, size);
        this._fillChar = fillChar;
    }

    @Override
    public void update() {
        Point topLeft = this.location;
        Point bottomRight = new Point(location.x + size.width, location.y + size.height);

        for(Point point = new Point(location.x,location.y); point.y < bottomRight.y; point.y++){
            Console.setCursorPosition(point);
            String outRow = String.valueOf(_fillChar).repeat(this.size.width);
            System.out.println(outRow);
        }
    }
}
