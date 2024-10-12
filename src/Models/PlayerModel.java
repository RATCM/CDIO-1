package Models;

/**
 * <p> Represents a the player,
 * <p> Is mainly used by {@link Controllers.PlayerController}
 * <p> And is created by {@link Controllers.MainMenuController}
 */
public class PlayerModel {
    public final String name;
    public int points;
    private boolean _hasWon;

    public PlayerModel(String name){
        this.name = name;
        points = 0;
        _hasWon = false;
    }

    /**
     * Sets the {@link _hasWon} flag to true
     */
    public void makePlayerWin(){
        _hasWon = true;
    }

    
    /**
     * Returns the {@link _hasWon} flag
     */
    public boolean getHasPlayerWon(){
        return _hasWon;
    }
}
