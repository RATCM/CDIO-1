package Views.Interfaces;

public interface IDiceGameView {
    void selectPlayer(int index);
    void selectNextPlayer();
    void outputPlayerDetails();
    void outputPlayerDetails(int index);
    void outputDiceRollResult(int sum, boolean isEqual);
}
