package Models.Rules;

import Controllers.DiceGameController;
import Controllers.PlayerController;
import Models.PlayerIndexer;
import Models.RollResult;

public class DiceSumRule extends GameRule {
    private final int _pointsToWin;
    public DiceSumRule(PlayerController[] playerStates, PlayerIndexer index, int pointsToWin) {
        super(playerStates, index);

        _pointsToWin = pointsToWin;
    }

    @Override
    public boolean isApplicaple(RollResult result) {
        return result.sum != 2 && getCurrentPlayer().getPoints() < _pointsToWin;
    }

    @Override
    public void apply(DiceGameController diceGameState, RollResult result) {
        getCurrentPlayer().grantPoints(result.sum);
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
