package Models.Rules;

import Controllers.DiceGameController;
import Controllers.PlayerController;
import Models.PlayerIndexer;
import Models.RollResult;


public class WinConditionRule extends GameRule {
    private final int _pointsToWin;
    public WinConditionRule(PlayerController[] playerStates, PlayerIndexer index, int pointsToWin) {
        super(playerStates, index);
        _pointsToWin = pointsToWin;
    }

    @Override
    public boolean isApplicable(RollResult result) {
        return result.sum != 2 && result.isIdentical && (getCurrentPlayer().getPoints() >= _pointsToWin);
    }

    @Override
    protected void applyRule(DiceGameController diceGameState, RollResult unused) {
        getCurrentPlayer().makePlayerWin();
        diceGameState.makePlayerWin();
    }
    
    @Override
    public String getConditionDescription() {
        return "Player has " + this._pointsToWin + " points and the dice values are equal";
    }

    @Override
    public String getDescription() {
        return "Player wins";
    }

}
