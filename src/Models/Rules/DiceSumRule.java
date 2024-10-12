package Models.Rules;

import Controllers.DiceGameController;
import Controllers.PlayerController;
import Models.PlayerIndexer;
import Models.RollResult;

public class DiceSumRule extends GameRule {
    public DiceSumRule(PlayerController[] playerStates, PlayerIndexer index) {
        super(playerStates, index);
    }

    @Override
    public boolean isApplicaple(RollResult result) {
        return result.sum != 2;
    }

    @Override
    public void apply(DiceGameController diceGameState, RollResult result) {
        playerStates[index.index].grantPoints(result.sum);
    }

    @Override
    public String getConditionDescription() {
        return "The dice are not two ones";
    }

    @Override
    public String getDescription() {
        return "Sum of dice gets added to player";
    }

}
