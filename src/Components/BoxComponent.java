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
        Point bottomRight = new Point(location.x + size.width, location.y + size.height);
        Console.setCursorPosition(this.location);
        
        Console.setBackgroundColor(this.backgroundColor);
        Console.setForegroundColor(this.forgroundColor);

        for(int y = location.y; y < bottomRight.y; y++){
            Point point = new Point(location.x, y);
            Console.setCursorPosition(point);
            String outRow = String.valueOf(_fillChar).repeat(this.size.width);
            System.out.println(outRow);
        }

        Console.resetColor();
    }
}
