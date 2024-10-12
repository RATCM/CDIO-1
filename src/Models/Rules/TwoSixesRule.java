package Models.Rules;

import Controllers.DiceGameController;
import Controllers.PlayerController;
import Models.PlayerIndexer;
import Models.RollResult;


public class TwoSixesRule extends GameRule {
    public TwoSixesRule(PlayerController[] playerStates, PlayerIndexer index) {
        super(playerStates, index);
    }

    @Override
    public boolean isApplicaple(RollResult result) {
        return !getCurrentPlayer().hasWon() && result.sum == 12 && getCurrentPlayer().getPreviousRoll().sum == 12;
    }

    @Override
    public void apply(DiceGameController diceGameState, RollResult unused) {
        getCurrentPlayer().makePlayerWin();
        diceGameState.makePlayerWin();
    }
}
