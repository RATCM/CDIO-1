package Views;

import Utils.Color;
import Utils.Console;
import Utils.Point;
import Utils.Size;
import Views.Interfaces.IMainMenuView;
import Components.*;

@java.lang.SuppressWarnings("unused")
public class VisualMainMenuView extends View implements IMainMenuView {
    private RawTextComponent _titleText;
    
    private BoxComponent _outerInputBox;
    private BoxComponent _innerInputBox;
    private CenteredTextComponent _inputLabel;

    private final java.util.Scanner _scanner;


    public VisualMainMenuView(Point location, java.util.Scanner scanner) {
        super(location, new Size(25,50));

        _scanner = scanner;
        initializeView();
    }

    // Java gives us an annoying warning on this method
    @SuppressWarnings("All")
    private String menuTitleText(){
        // Ascii art made using:
        // https://patorjk.com/software/taag/

        return  " __  __    _    ___ _   _ \n" +
                "|  \\/  |  / \\  |_ _| \\ | |\n" +
                "| |\\/| | / _ \\  | ||  \\| |\n" +
                "| |  | |/ ___ \\ | || |\\  |\n" +
                "|_|  |_/_/___\\_\\___|_| \\_|\n" +
                "|  \\/  | ____| \\ | | | | |\n" +
                "| |\\/| |  _| |  \\| | | | |\n" +
                "| |  | | |___| |\\  | |_| |\n" +
                "|_|  |_|_____|_| \\_|\\___/ \n";
    }
    
    @Override
    public String getPlayerName() {
        Console.clear();
        update();
        
        Point loc1 = new Point(_inputLabel.location.x, _inputLabel.location.y+2);
        Point locInput = new Point(_inputLabel.location.x+1, _inputLabel.location.y+2);

        Console.setCursorPosition(loc1);
        System.out.print("[" + " ".repeat(_inputLabel.size.width-2) + "]");

        Console.setCursorPosition(locInput);

        var name = _scanner.nextLine();

        // We just shorten the name if it's
        // above the allowed length
        if(name.length() > _inputLabel.size.width-2){
            name = name.substring(0, _inputLabel.size.width-3);
        }

        update();
        return name;
    }

    @Override
    public void startGame() {
        _outerInputBox.clearComponent();
        Console.setCursorPosition(_innerInputBox.location);
        System.out.println("Press [Enter] to begin game");
        _scanner.nextLine();
        clearView();
    }

    @Override
    public void update() {
        _titleText.update();
        _outerInputBox.update();
        _innerInputBox.update();
        _inputLabel.update();
    }

    @Override
    protected void initializeView() {
        Console.clear();

        Size sizeTitle = new Size(25, 9);
        Point locTitle = new Point(location.x, location.y);

        Size sizeOuterBox = new Size(20, 6);
        Point locOuterBox = new Point(location.x + sizeTitle.width/2 - sizeOuterBox.width/2, location.y+sizeTitle.height+3);

        Size sizeInnerBox = new Size(sizeOuterBox.width-2, sizeOuterBox.height-2);
        Point locInnerBox = new Point(locOuterBox.x+1, locOuterBox.y+1);

        Size sizeInputLabel = new Size(sizeInnerBox.width, 1);
        Point locInputLabel = new Point(locInnerBox.x, locInnerBox.y);

        _outerInputBox = new BoxComponent(locOuterBox, sizeOuterBox, '#', Color.None, Color.Cyan);
        _innerInputBox = new BoxComponent(locInnerBox, sizeInnerBox, ' ');
        _titleText = new RawTextComponent(locTitle, sizeTitle, menuTitleText(), Color.None, Color.Blue);

        _inputLabel = new CenteredTextComponent(locInputLabel, sizeInputLabel, ":Enter name:", Color.None, Color.Blue);

        update();
    }
}
