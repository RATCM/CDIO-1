package Views;

import Components.BoxComponent;
import Components.CenteredTextComponent;
import Components.RawTextComponent;
import Models.Rules.GameRule;
import Utils.Color;
import Utils.Point;
import Utils.Size;

/**
 * <p> Displays the rules that have been applied
 * <p> to some dice throw
 */
class AppliedRuleView extends View {
    private BoxComponent _outerBox;
    private BoxComponent _innerBox;
    private CenteredTextComponent _labelText;
    private RawTextComponent _textRules;

    private final java.util.ArrayList<GameRule> _rulesToDisplay;

    public AppliedRuleView(Point location, Size size) {
        super(location, size);

        _rulesToDisplay = new java.util.ArrayList<>();
        initializeView();
    }

    /**
     * <p> This updates the views and then
     * <p> clears all the added rules
     */
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

    /**
     * Adds a rule that have been applied
     * @param rule the applied {@link Models.Rules.GameRule}
     */
    public void addAppliedRule(GameRule rule) {
        _rulesToDisplay.add(rule);
    }

    /**
     * <p> Creates new components and
     * <p> sets the height of all the new components
     * <p> with respect to the amount of added rules
     */
    private void setHeight(){
        var heightTotal = _rulesToDisplay.size()+3;
        _outerBox = new BoxComponent(location, new Size(size.width, heightTotal), '#', Color.None, Color.Blue);
        _innerBox = new BoxComponent(new Point(_outerBox.location.x+1, _outerBox.location.y+1), new Size(_outerBox.size.width-2, _outerBox.size.height-2), ' ');
        _textRules = new RawTextComponent(new Point(_innerBox.location.x, _innerBox.location.y+1), new Size(_innerBox.size.width, _innerBox.size.height-1), "", Color.None, Color.Green);
    }

    /**
     * Sets the text for the {@link #_textRules} to display
     */
    private void setText(){
        StringBuilder text = new StringBuilder();
        for(var rule: _rulesToDisplay){
            text.append(rule.getDescription());
            text.append("\n");
        }

        _textRules.setText(text.toString());
    }
}
