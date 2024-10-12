package Views;

import Components.*;
import Models.RollResult;
import Models.PlayerModel;
import Utils.Color;
import Utils.Point;
import Utils.Size;

public class DiceThrowResultView extends View{
    private BoxComponent _outerBox;
    private BoxComponent _innerBox;

    private CenteredTextComponent _resultLabel;
    private CenteredTextComponent _resultPlayer;
    private CenteredTextComponent _resultSum;
    private CenteredTextComponent _resultIsIdentical;

    public DiceThrowResultView(Point location, int width) {
        super(location, new Size(width, 7)); // The height is fixed for now

        initializeView();
    }

    public void setResult(PlayerModel player, RollResult result){
        _resultPlayer.setText(String.format("Player: [%s]", player.name));
        _resultSum.setText(String.format("Sum: [%s]", result.sum));
        if(result.isIdentical){
            _resultIsIdentical.setText("The dice are identical");
        }
        else{
            _resultIsIdentical.setText("The dice are not identical");
        }
    }

    public void updateResult(){
        _resultLabel.update();
        _resultPlayer.update();
        _resultSum.update();
        _resultIsIdentical.update();
    }

    @Override
    public void update() {
        _outerBox.update();
        _innerBox.update();

        updateResult();
    }

    @Override
    protected final void initializeView() {
        Point locOuter = new Point(location.x, location.y);
        Size sizeOuter = new Size(size.width, size.height);

        Point locInner = new Point(location.x+1, location.y+1);
        Size sizeInner = new Size(size.width-2, size.height-2); 
        
        Point locLabel = new Point(locInner.x, locInner.y);
        Size sizeLabel = new Size(sizeInner.width, 1);

        // We assign the points from the bottom up here
        Point topLeftInner = new Point(locInner.x, locInner.y);
        Point bottomRightInner = new Point(locInner.x+sizeInner.width, locInner.y+sizeInner.height);

        // Bottom
        Point locIdentical = new Point(topLeftInner.x, bottomRightInner.y-1);
        Size sizeIdentical = new Size(sizeInner.width, 1);

        // Middle
        Point locSum = new Point(topLeftInner.x, bottomRightInner.y-2); 
        Size sizeSum = new Size(sizeInner.width, 1);
        
        // Top
        Point locPlayer = new Point(topLeftInner.x, bottomRightInner.y-3);
        Size sizePlayer = new Size(sizeInner.width, 1);

        _outerBox = new BoxComponent(locOuter, sizeOuter, '#', Color.None, Color.Cyan);
        _innerBox = new BoxComponent(locInner, sizeInner, ' ');

        _resultLabel = new CenteredTextComponent(locLabel, sizeLabel, "<Dice throw result>", Color.None, Color.Blue);
        _resultPlayer = new CenteredTextComponent(locPlayer, sizePlayer, "", Color.None, Color.Green);
        _resultSum = new CenteredTextComponent(locSum, sizeSum, "", Color.None, Color.Green);
        _resultIsIdentical = new CenteredTextComponent(locIdentical, sizeIdentical, "", Color.None, Color.Green);
    }
}