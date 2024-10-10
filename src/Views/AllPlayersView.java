package Views;

import Utils.Color;
import Utils.Point;
import Utils.Size;
import Views.Interfaces.IAllPlayersView;

public class AllPlayersView extends View implements IAllPlayersView {
    public final PlayerView[] playerViews;
    
    public AllPlayersView(Point location, int numPlayers) {
        // width(1): 15 
        // width(2): 15 + 20 + 15 = 50
        // width(3): 15 + 20 + 20 + 15 = 70
        // width(4): 15 + 20 + 20 + 20 + 15 = 90
        // width(n): 30 + (n-1)*20, (n > 1)
        super(location, new Size(numPlayers > 1 ? 30 + 20*(numPlayers-1) : 15, 10));

        playerViews = new PlayerView[numPlayers];

        initializeView();
    }


    @Override
    public void update() {
        clearView();
        for(var view: playerViews){
            view.update();
        }
    }

    @Override
    protected final void initializeView() {
        playerViews[0] = new PlayerView(location);
        for (int i = 1; i < playerViews.length; i++) {
            Point p = Point.Add(location, new Point(15 + i*20, 0));
            playerViews[i] = new PlayerView(p);
        }
    }

    @Override  
    public void setName(int index, String name){
        playerViews[index].setName(name, true);
    }

    @Override
    public void setPoints(int index, int points){
        playerViews[index].setPoints(points, true);
    }

    public void setPlayerNameColor(int index, Color color){
        playerViews[index].setNameForegroundColor(color);
    }
}
