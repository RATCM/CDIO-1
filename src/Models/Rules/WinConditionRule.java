package Models.Rules;

import Controllers.DiceGameController;
import Controllers.PlayerController;
import Models.PlayerIndexer;
import Models.RollResult;


public class WinConditionRule extends GameRule {
    private int _pointsToWin;
    public WinConditionRule(PlayerController[] playerStates, PlayerIndexer index, int pointsToWin) {
        super(playerStates, index);
        _pointsToWin = pointsToWin;
    }

    @Override
    public boolean isApplicaple(RollResult result) {
        return !getCurrentPlayer().hasWon() && (getCurrentPlayer().getPoints() >= _pointsToWin) && result.isIdentical;
    }

    @Override
    public void apply(DiceGameController diceGameState, RollResult unused) {
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
