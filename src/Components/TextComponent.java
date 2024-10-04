package Components;

import Utils.Console;
import Utils.Point;
import Utils.Size;

public class TextComponent extends Component{
    private String _text;

    public TextComponent(Point location, Size size, String text) {
        super(location, size);
        this._text = text;
    }

    @Override
    public void update() {
        String[] outputString = calculateOutputString();
        Console.setCursorPosition(this.location);

        for(int i = 0; i < outputString.length; i++){
            Point p = new Point(this.location.x, this.location.y + i);
            Console.setCursorPosition(p);

            System.out.println(outputString[i]);
        }
    }

    private String[] calculateOutputString(){
        int numLines = Math.min(1 + _text.length()/this.size.width, this.size.height);
        String[] outputString = new String[numLines];

        for(int i = 0; i < outputString.length; i++){
            int startIndex = i*this.size.width;
            int endIndex = Math.min(startIndex + this.size.width, _text.length());
            outputString[i] = _text.substring(startIndex, endIndex);
        }

        return outputString;
    }
}
