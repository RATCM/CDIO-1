package Views;

import Components.BoxComponent;
import Components.CenteredTextComponent;
import Components.RawTextComponent;
import Models.Rules.GameRule;
import Utils.Color;
import Utils.Point;
import Utils.Size;

class AppliedRuleView extends View {
    private BoxComponent _outerBox;
    private BoxComponent _innerBox;
    private CenteredTextComponent _labelText;
    private RawTextComponent _textRules;

    private java.util.ArrayList<GameRule> _rulesToDisplay;

    public AppliedRuleView(Point location, Size size) {
        super(location, size);

        _rulesToDisplay = new java.util.ArrayList<GameRule>();
        initializeView();
    }

    @Override
    public void update() {
        clearView();
        setHeight();
        setText();

        _outerBox.update();
        _innerBox.update();
        _labelText.update();
        _textRules.update();

        _rulesToDisplay.clear();
    }

    @Override
    protected void initializeView() {
        
        _outerBox = new BoxComponent(location, size, '#', Color.None, Color.Blue);
        _innerBox = new BoxComponent(new Point(_outerBox.location.x+1, _outerBox.location.y+1), new Size(_outerBox.size.width-2, _outerBox.size.height-2), ' ');
        _labelText = new CenteredTextComponent(_innerBox.location, new Size(_innerBox.size.width, 1), "Results", Color.None, Color.Purple);
        _textRules = new RawTextComponent(new Point(_innerBox.location.x, _innerBox.location.y+1), new Size(_innerBox.size.width, _innerBox.size.height-1), "", Color.None, Color.Green);
    }

    public void addAppliedRule(GameRule rule) {
        _rulesToDisplay.add(rule);
    }

    private void setHeight(){
        var heightTotal = _rulesToDisplay.size()+3;
        _outerBox = new BoxComponent(location, new Size(size.width, heightTotal), '#', Color.None, Color.Blue);
        _innerBox = new BoxComponent(new Point(_outerBox.location.x+1, _outerBox.location.y+1), new Size(_outerBox.size.width-2, _outerBox.size.height-2), ' ');
        _textRules = new RawTextComponent(new Point(_innerBox.location.x, _innerBox.location.y+1), new Size(_innerBox.size.width, _innerBox.size.height-1), "", Color.None, Color.Green);
    }

    private void setText(){
        StringBuilder text = new StringBuilder();
        for(var rule: _rulesToDisplay){
            text.append(rule.getDescription());
            text.append("\n");
        }

        _textRules.setText(text.toString());
    }
}
