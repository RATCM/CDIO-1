package Components;

import Utils.Console;
import Utils.Point;
import Utils.Size;

public class CenteredTextComponent extends RawTextComponent {

    public CenteredTextComponent(Point location, Size size, String text) {
        super(location, size, text);
    }

    @Override
    public void update(){
        int textLength = getText().length();
        Point writePoint = new Point(location.x + size.width/2 -textLength/2, location.y + size.height/2);

        Console.setCursorPosition(writePoint);
        System.out.print(getText());
    }

}
