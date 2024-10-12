package Components;

import Utils.Color;
import Utils.Console;
import Utils.Point;
import Utils.Size;

/**
 * <p> This represents raw text, i.e.
 * <p> text that doesn't have any fancy beaviour
 */
public class RawTextComponent extends Component{
    private String _text;

    public RawTextComponent(Point location, Size size, String text) {
        super(location, size);
        this._text = text;
    }

    public RawTextComponent(Point location, Size size, String text, Color backgroundColor, Color foregroundColor){
        super(location, size, backgroundColor, foregroundColor);
        this._text = text;
    }

    /**
     * Sets the text to the component
     * @param text
     */
    public void setText(String text){
        _text = text;
    }

    /**
     * Gets the text from the component
     * @return the text inside the component
     */
    public String getText(){
        return _text;
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
