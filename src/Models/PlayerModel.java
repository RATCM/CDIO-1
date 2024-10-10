package Models;

public class PlayerModel {
    public final String name;
    public int points;
    private boolean _hasWon;

    public PlayerModel(String name){
        this.name = name;
        points = 0;
        _hasWon = false;
    }

    public void makePlayerWin(){
        _hasWon = true;
    }

    public boolean getHasPlayerWon(){
        return _hasWon;
    }
}
