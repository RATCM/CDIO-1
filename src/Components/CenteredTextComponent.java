package Components;

import Utils.Console;
import Utils.Point;
import Utils.Size;
import Utils.Color;

/**
 * This displays centered text
 */
public class CenteredTextComponent extends RawTextComponent {

    // Java needs to stop complaining that an optional
    // constructor isn't used.
    @SuppressWarnings("unused")
    public CenteredTextComponent(Point location, Size size, String text) {
        super(location, size, text);
    }

    public CenteredTextComponent(Point location, Size size, String text, Color backgroundColor, Color foregroundColor){
        super(location, size, text, backgroundColor, foregroundColor);
    }

    @Override
    public void update(){
        clearComponent();
        int textLength = getText().length();
        Point writePoint = new Point(location.x + size.width/2 -textLength/2, location.y + size.height/2);
        
        Console.setCursorPosition(writePoint);

        Console.setBackgroundColor(this.backgroundColor);
        Console.setForegroundColor(this.forgroundColor);

        System.out.println(getText());

        Console.resetColor();
    }

}
