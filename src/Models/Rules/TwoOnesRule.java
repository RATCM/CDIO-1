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
    public boolean isApplicable(RollResult result) {
        return result.sum == 2;
    }

    @Override
    protected void applyRule(DiceGameController diceGameState, RollResult unused) {
        getCurrentPlayer().resetPoints();
    }

    @Override
    public String getConditionDescription() {
        return "The dice are two ones";
    }
    @Override
    public String getDescription() {
        return "Player loses all points";
    }
    
}
