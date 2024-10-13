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
    public boolean isApplicable(RollResult result) {
        // The rules in the vision doesn't explicitly say
        // that the rule doesn't apply if they hit two ones,
        // but we decided to do so anyway        
        return result.isIdentical && result.sum != 2;
    }

    @Override
    protected void applyRule(DiceGameController diceGameState, RollResult unused) {
        diceGameState.grantPlayerExtraTurn();
    }
    
    @Override
    public String getConditionDescription() {
        return "The dice values are equal and not 1";
    }

    @Override
    public String getDescription() {
        return "Player gets an extra turn";
    }
}
