package Views.Interfaces;

import Models.Rules.GameRule;

public interface IDiceGameView {
    void getUserInput();
    void outputPlayerDetails();
    void outputPlayerDetails(int index);
    void outputAllPlayerDetails();
    void outputDiceRollResult(int sum, boolean isEqual);
    void outputWinningPlayer();
    void outputAppliedRule(GameRule rule);
}
