package Controllers;

import Models.PlayerModel;
import Models.RollResult;
import Views.Interfaces.IDiceGameView;

/**
 * <p> This controls the {@link Models.PlayerModel} and {@link Views.Interfaces.IDiceGameView}
 * 
 * <p> It essentially contains the state of a given player.
 */
public class PlayerController {
    private final PlayerModel _player;
    private final IDiceGameView _view;
    public java.util.ArrayList<RollResult> rolls;
    
    private int _minPoints;
    private int _maxPoints;
    private RollResult _lastRoll;

    public PlayerController(PlayerModel player, IDiceGameView view){
        _player = player;
        _view = view;
        rolls = new java.util.ArrayList<>();

        // The default bounds
        _minPoints = Integer.MIN_VALUE;
        _maxPoints = Integer.MAX_VALUE;
        _lastRoll = new RollResult(-1, false);
    }

    /**
     * Rolls the dice and stores the result in a list.
     * 
     * @param diceGame the dice game controller
     * @return the result of the dice roll
     */
    // Java wants to replace rolls.get(0) with rolls.getFirst()
    // But we don't wanna do that because the getFirst method
    // isn't avaliable in older versions of java.
    @SuppressWarnings("All")
    public RollResult roll(DiceGameController diceGame){
        if(!rolls.isEmpty()){
            _lastRoll = rolls.get(0);
        }
        var result = diceGame.rollDice();
        rolls.add(result);

        _view.outputDiceRollResult(result.sum, result.isIdentical);
        
        return result;
    }

    /**
     * Sets the bound of the playerPoints so they are constricted to a certain range
     * @param min the minimum
     * @param max the maximum
     */
    public void setPointsBound(int min, int max){
        _minPoints = min;
        _maxPoints = max;
    }

    /**
     * @return The number of points the player has
     */
    public int getPoints(){
        return _player.points;
    }

    /**
     * Sets the points to zero
     */
    public void resetPoints(){
        _player.points = 0;
    }

    /**
     * Grants the player some points
     * @param points The amount of points to grant
     */
    public void grantPoints(int points){
        _player.points = clamp(_player.points + points, _minPoints, _maxPoints);
    }

    /**
     * Removes some points from the player
     * @param points The amount of points to remove
     */
    // Java complains that this method is unused
    @SuppressWarnings("unused")
    public void removePoints(int points){
        _player.points = clamp(_player.points - points, _minPoints, _maxPoints);
    }

    /**
     * @return the previous dice roll, i.e. the dice roll before the latest dice roll
     */
    public RollResult getPreviousRoll(){
        return _lastRoll;
    }

    /**
     * Makes the current player win and outputs it to the view.
     */
    public void makePlayerWin(){
        _player.makePlayerWin();
        _view.outputWinningPlayer();
    }

    /**
     * @return Whether the player has won.
     */
    // Java complains that this method is unused
    @SuppressWarnings("unused")
    public boolean hasWon(){
        return _player.getHasPlayerWon();
    }

    // The Math.Clamp method doesn't exist for older
    // versions of Java, so we're defining it here
    // If this becomes used in other classes, it should
    // be put into a class in Utils
    /**
     * Clamps the value between 2 points
     * @param value the value to clamp
     * @param min the minimum
     * @param max the maximum
     * @return the resulting value
     */
    private static int clamp(int value, int min, int max){
        var result = value;

        result = Math.max(result, min); // result can't smaller than min
        result = Math.min(result, max); // result can't be larger than max

        return result;
    }
}
