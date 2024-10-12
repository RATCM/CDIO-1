package Views;

import Models.PlayerModel;
import Utils.Color;
import Utils.Point;
import Utils.Size;

/**
 * <p> Displays multiple instances of {@link Views.PlayerView}
 */
class AllPlayersView extends View {
    public final PlayerView[] playerViews;
    
    public AllPlayersView(Point location, Size size, PlayerModel[] players) {
        super(location, size);

        playerViews = new PlayerView[players.length];

        initializeView();
    }


    public void update() {
        clearView();
        for(var view: playerViews){
            view.update();
        }
    }

    protected final void initializeView() {
        playerViews[0] = new PlayerView(location);
        for (int i = 1; i < playerViews.length; i++) {
            int spacing = 25;
            Point p = Point.Add(location, new Point(i*spacing, 0));
            playerViews[i] = new PlayerView(p);
        }
    }

    /**
     * <p> This sets the name to some {@link PlayerView}
     * <p> and resizes the component to fit the name
     * 
     * @param index the index of the player 
     * @param name the name of the player
     */
    public void setName(int index, String name){
        Size newSize = new Size(name.length()+6, 10);

        playerViews[index] = new PlayerView(playerViews[index].location, newSize);
        playerViews[index].setName(name);
    }
    
    /**
     * <p> This sets the name to some {@link PlayerView}
     * <p> but doesn't resize the component to fit the name
     * 
     * @param index the index of the player 
     * @param name the name of the player
     */
    public void setNameNoResize(int index, String name) {
        playerViews[index].setName(name);
    }

    /**
     * <p> This sets the points to some {@link PlayerView}
     * 
     * @param index the index of the player 
     * @param points the amount of points
     */
    public void setPoints(int index, int points){
        playerViews[index].setPoints(points);
    }

    /**
     * <p> This changes the foreground color
     * <p> of the name to some {@link PlayerView}
     * 
     * @param index the index of the player 
     * @param color the new color
     */
    public void setPlayerNameColor(int index, Color color){
        playerViews[index].setNameForegroundColor(color);
    }
}
