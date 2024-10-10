package Views;
import Components.BoxComponent;
import Components.TextComponent;
import Utils.Color;
import Utils.Point;
import Utils.Size;
import Views.Interfaces.IPlayerView;

public class PlayerView extends View implements IPlayerView {
    BoxComponent _playerNameBox1;
    BoxComponent _playerNameBox2;
    TextComponent _playerNameText;

    BoxComponent _playerPointsBox1;
    BoxComponent _playerPointsBox2;
    TextComponent _playerPointsText;

    public PlayerView(Point location){
        // The size is fixed
        super(location, new Size(15,10));
        
        initializeView();
    }

    @Override
    protected final void initializeView(){
        _playerNameBox1 =
            new BoxComponent(
                this.location,
                new Size(size.width, size.height/2),
                '#');

        _playerNameBox2 =
            new BoxComponent(
                Point.Add(_playerNameBox1.location, new Point(1,1)),
                new Size(_playerNameBox1.size.width-2, _playerNameBox1.size.height-2),
                ' ');
        
        _playerNameText =
            new TextComponent(
                _playerNameBox2.location,
                _playerNameBox2.size,
                "");


        _playerPointsBox1 =
            new BoxComponent(
                Point.Add(this.location, new Point(0,_playerNameBox1.size.height)),
                new Size(size.width, size.height/2),
                '#');

        _playerPointsBox2 =
        new BoxComponent(
            Point.Add(_playerPointsBox1.location, new Point(1,1)),
            new Size(_playerPointsBox1.size.width-2, _playerPointsBox1.size.height-2),
            ' ');

        _playerPointsText =
            new TextComponent(
                _playerPointsBox2.location,
                _playerPointsBox2.size,
                "0");
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

    @Override
    public void setPoints(int points, boolean centered){
        if(centered){
            var str = String.valueOf(points);

            Size newSize = new Size(str.length(), 1);
            Point newPoint = Point.Add(_playerPointsBox2.location, new Point(_playerPointsBox2.size.width/2 - str.length()/2, _playerPointsBox2.size.height/2));

            _playerPointsText =
                new TextComponent(
                    newPoint,
                    newSize,
                    str);
        }
        else{
            _playerPointsText =
                new TextComponent(
                    _playerPointsBox2.location,
                    _playerPointsBox2.size,
                    String.valueOf(points));
        }
    }

    @Override
    public void setName(String name, boolean centered){
        if(centered){
            Size newSize = new Size(name.length(), 1);
            Point newPoint = Point.Add(_playerNameBox2.location, new Point(_playerNameBox2.size.width/2 - name.length()/2, _playerNameBox2.size.height/2));

            _playerNameText =
                new TextComponent(
                    newPoint,
                    newSize,
                    name);
        }
        else{
            _playerNameText =
                new TextComponent(
                    _playerNameBox2.location,
                    _playerNameBox2.size,
                    name);
        }
    }

    public void setNameForegroundColor(Color color){
        _playerNameText.forgroundColor = color;
    }
}
