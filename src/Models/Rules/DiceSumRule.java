package Models.Rules;

import Controllers.DiceGameController;
import Controllers.PlayerController;
import Models.PlayerIndexer;
import Models.RollResult;

public class DiceSumRule extends GameRule {

    public DiceSumRule(PlayerController[] playerStates, PlayerIndexer index) {
        super(playerStates, index);
    }

    private RollResult _result;

    @Override
    public boolean isApplicaple(RollResult result) {
        _result = result;
        return result.sum != 2;
    }

    @Override
    public void apply(DiceGameController diceGameState) {
        playerStates[index.index].grantPoints(_result.sum);
    }

}
