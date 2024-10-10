package Controllers;

import Models.DiceGameModel;
import Models.PlayerModel;
import Models.RollResult;
import Views.Interfaces.IDiceGameView;
import javax.swing.text.View;

public class PlayerController {
    private PlayerModel _player;
    private IDiceGameView _view;
    public java.util.ArrayList<RollResult> rolls;
    
    private int _minPoints;
    private int _maxPoints;
    private RollResult _lastRoll;

    public PlayerController(PlayerModel player, IDiceGameView view){
        _player = player;
        _view = view;
        rolls = new java.util.ArrayList<RollResult>();

        // The default bounds
        _minPoints = Integer.MIN_VALUE;
        _maxPoints = Integer.MAX_VALUE;
        _lastRoll = new RollResult(-1, false);
    }

    public RollResult roll(DiceGameModel diceGame){
        if(!rolls.isEmpty()){
            _lastRoll = rolls.getFirst();
        }
        var result = diceGame.rollDices();
        rolls.add(result);

        _view.outputDiceRollResult(result.sum, result.isIdentical);
        
        return result;
    }

    // Bounds the points so they dont increase 
    public void setPointsBound(int min, int max){
        _minPoints = min;
        _maxPoints = max;
    }

    public int getPoints(){
        return _player.points;
    }

    public void resetPoints(){
        _player.points = 0;
    }

    public void grantPoints(int points){
        _player.points = Math.clamp(_player.points + points, _minPoints, _maxPoints);
        _view.outputPlayerDetails();
    }

    public void removePoints(int points){
        _player.points = Math.clamp(_player.points - points, _minPoints, _maxPoints);
        _view.outputPlayerDetails();
    }

    public RollResult getPreviousRoll(){
        return _lastRoll;
    }

    public void makePlayerWin(){
        _player.makePlayerWin();
    }

    public boolean hasWon(){
        return _player.getHasPlayerWon();
    }
}
