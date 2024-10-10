package Models.Rules;

import Controllers.DiceGameController;
import Controllers.PlayerController;
import Models.PlayerIndexer;
import Models.RollResult;


public class TwoOnesRule extends GameRule { 
    public TwoOnesRule(PlayerController[] playerStates, PlayerIndexer index) {
        super(playerStates, index);
    }

    @Override
    public boolean isApplicaple(RollResult result) {
        return !getCurrentPlayer().hasWon() && result.sum == 2;
    }

    @Override
    public void apply(DiceGameController diceGameState) {
        getCurrentPlayer().resetPoints();
    }
}
