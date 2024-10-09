package Views;

import Utils.Color;
import Utils.Point;
import Utils.Size;
import Views.Interfaces.IAllPlayersView;

public class AllPlayersView extends View implements IAllPlayersView {
    private final PlayerView[] _playerViews;
    
    public AllPlayersView(Point location, int numPlayers) {
        // width(1): 15 
        // width(2): 15 + 20 + 15 = 50
        // width(3): 15 + 20 + 20 + 15 = 70
        // width(4): 15 + 20 + 20 + 20 + 15 = 90
        // width(n): 30 + (n-1)*20, (n > 1)
        super(location, new Size(numPlayers > 1 ? 30 + 20*(numPlayers-1) : 15, 10));

        _playerViews = new PlayerView[numPlayers];

        initializeView();
    }


    @Override
    public void update() {
        clearView();
        for(var view: _playerViews){
            view.update();
        }
    }

    @Override
    protected final void initializeView() {
        _playerViews[0] = new PlayerView(location);
        for (int i = 1; i < _playerViews.length; i++) {
            Point p = Point.Add(location, new Point(15 + i*20, 0));
            _playerViews[i] = new PlayerView(p);
        }
    }

    @Override  
    public void setName(int index, String name){
        _playerViews[index].setName(name, true);
    }

    @Override
    public void setPoints(int index, int points){
        _playerViews[index].setPoints(points, true);
    }

    public void setPlayerNameColor(int index, Color color){
        _playerViews[index].setNameForegroundColor(color);
    }
}
