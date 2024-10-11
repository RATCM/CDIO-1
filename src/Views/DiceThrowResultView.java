package Views;

import Components.*;
import Models.RollResult;
import Models.PlayerModel;
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
        super(location, new Size(width, 6)); // The height is fixed for now

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

        Point locPlayer = new Point(locInner.x, locInner.y+1);
        Size sizePlayer = new Size(sizeInner.width, 1);

        // Bellow player
        Point locSum = new Point(locInner.x, locPlayer.y+1); 
        Size sizeSum = new Size(sizeInner.width, 1);

        // Bellow sum
        Point locIdentical = new Point(locInner.x, locSum.y+1);
        Size sizeIdentical = new Size(sizeInner.width, 1);

        _outerBox = new BoxComponent(locOuter, sizeOuter, '#');
        _innerBox = new BoxComponent(locInner, sizeInner, ' ');

        _resultLabel = new CenteredTextComponent(locLabel, sizeLabel, "<Dice throw result>");
        _resultPlayer = new CenteredTextComponent(locPlayer, sizePlayer, "");
        _resultSum = new CenteredTextComponent(locSum, sizeSum, "");
        _resultIsIdentical = new CenteredTextComponent(locIdentical, sizeIdentical, "");
    }
}