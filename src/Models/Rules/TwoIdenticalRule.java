package Models.Rules;

import Controllers.DiceGameController;
import Controllers.PlayerController;
import Models.PlayerIndexer;
import Models.RollResult;

public class TwoIdenticalRule extends GameRule {
    public TwoIdenticalRule(PlayerController[] playerStates, PlayerIndexer index) {
        super(playerStates, index);
    }

    @Override
    public boolean isApplicaple(RollResult result) {
        // The rules in the vision doesn't explicitly say
        // that the rule doesn't apply if they hit two ones,
        // but we decided to do so anyway
        return !getCurrentPlayer().hasWon() && result.isIdentical && result.sum != 2;
    }

    @Override
    public void apply(DiceGameController diceGameState, RollResult unused) {
        diceGameState.grantPlayerExtraTurn();
    }
}
