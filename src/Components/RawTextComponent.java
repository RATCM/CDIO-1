package Components;

import Utils.Color;
import Utils.Console;
import Utils.Point;
import Utils.Size;

public class RawTextComponent extends Component{
    private final String _text;

    public RawTextComponent(Point location, Size size, String text) {
        super(location, size);
        this._text = text;
    }

    public RawTextComponent(Point location, Size size, String text, Color backgroundColor, Color foregroundColor){
        super(location, size, backgroundColor, foregroundColor);
        this._text = text;
    }


    @Override
    public void update() {
        clearComponent();
        String[] outputString = _text.split("\n");
        
        Console.setCursorPosition(this.location);

        Console.setBackgroundColor(this.backgroundColor);
        Console.setForegroundColor(this.forgroundColor);    

        for(int i = 0; i < outputString.length; i++){
            Point p = new Point(this.location.x, this.location.y + i);
            Console.setCursorPosition(p);

            System.out.println(outputString[i]);
        }

        Console.resetColor();
    }
}
