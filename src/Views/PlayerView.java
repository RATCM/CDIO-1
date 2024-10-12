package Views;
import Components.BoxComponent;
import Components.CenteredTextComponent;
import Utils.Color;
import Utils.Point;
import Utils.Size;

/**
 * Displays player info
 */
class PlayerView extends View {
    BoxComponent _playerNameBox1;
    BoxComponent _playerNameBox2;
    CenteredTextComponent _playerNameText;

    BoxComponent _playerPointsBox1;
    BoxComponent _playerPointsBox2;
    CenteredTextComponent _playerPointsText;

    public PlayerView(Point location){
        // The size is fixed
        super(location, new Size(15,10));
        
        initializeView();
    }

    public PlayerView(Point location, Size size){
        super(location, size);
        
        initializeView();
    }

    @Override
    protected final void initializeView(){
        _playerNameBox1 =
            new BoxComponent(
                this.location,
                new Size(size.width, size.height/2),
                '#',
                Color.None,
                Color.Cyan);

        _playerNameBox2 =
            new BoxComponent(
                Point.Add(_playerNameBox1.location, new Point(1,1)),
                new Size(_playerNameBox1.size.width-2, _playerNameBox1.size.height-2),
                ' ');
        
        _playerNameText =
            new CenteredTextComponent(
                _playerNameBox2.location,
                _playerNameBox2.size,
                "",
                Color.None,
                Color.Green);


        _playerPointsBox1 =
            new BoxComponent(
                Point.Add(this.location, new Point(0,_playerNameBox1.size.height)),
                new Size(size.width, size.height/2),
                '#',
                Color.None,
                Color.Cyan);

        _playerPointsBox2 =
        new BoxComponent(
            Point.Add(_playerPointsBox1.location, new Point(1,1)),
            new Size(_playerPointsBox1.size.width-2, _playerPointsBox1.size.height-2),
            ' ');

        _playerPointsText =
            new CenteredTextComponent(
                _playerPointsBox2.location,
                _playerPointsBox2.size,
                "0",
                Color.None,
                Color.Green);
    }

    @Override
    public void update() {
        clearView();
        _playerNameBox1.update();
        _playerNameBox2.update();
        _playerNameText.update();
        
        _playerPointsBox1.update();
        _playerPointsBox2.update();
        _playerPointsText.update();
    }

    /**
     * Sets the points of the player to the component
     * @param points
     */
    public void setPoints(int points){
        _playerPointsText.setText(String.valueOf(points));
    }

    /**
     * Sets the name of the player to the component
     * @param name
     */
    public void setName(String name){
        _playerNameText.setText(name);
    }

    /**
     * Changed the foregroundColor of the player name
     */
    public void setNameForegroundColor(Color color){
        _playerNameText.forgroundColor = color;
    }
}
