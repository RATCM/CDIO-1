package Views;

import Models.PlayerModel;
import Utils.Color;
import Utils.Point;
import Utils.Size;
import Views.Interfaces.IAllPlayersView;

public class AllPlayersView extends View implements IAllPlayersView {
    public final PlayerView[] playerViews;
    
    public AllPlayersView(Point location, Size size, PlayerModel[] players) {
        super(location, size);

        playerViews = new PlayerView[players.length];

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
            int spacing = 25;
            Point p = Point.Add(location, new Point(i*spacing, 0));
            playerViews[i] = new PlayerView(p);
        }
    }

    @Override  
    public void setName(int index, String name){
        Size newSize = new Size(name.length()+6, 10);

        playerViews[index] = new PlayerView(playerViews[index].location, newSize);
        playerViews[index].setName(name);
    }
    
    @Override
    public void setNameNoResize(int index, String name) {
        playerViews[index].setName(name);
    }

    @Override
    public void setPoints(int index, int points){
        playerViews[index].setPoints(points);
    }

    public void setPlayerNameColor(int index, Color color){
        playerViews[index].setNameForegroundColor(color);
    }


}
