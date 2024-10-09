package Views.Interfaces;

public interface IDiceGameView {
    void selectPlayer(int index);
    void selectNextPlayer();
    void outputPlayerDetails();
    void outputDiceRollResult(int sum, boolean isEqual);
}
