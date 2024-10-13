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
    public boolean isApplicable(RollResult result) {
        return result.sum == 12 && getCurrentPlayer().getPreviousRoll().sum == 12;
    }

    @Override
    protected void applyRule(DiceGameController diceGameState, RollResult unused) {
        getCurrentPlayer().makePlayerWin();
        diceGameState.makePlayerWin();
    }

    @Override
    public String getConditionDescription() {
        return "The player hits two sixes, two times in a row";
    }

    @Override
    public String getDescription() {
        return "Player wins";
    }
}
