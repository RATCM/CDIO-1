package Views.Interfaces;

public interface IDiceGameView {
    void getUserInput();
    void outputPlayerDetails();
    void outputPlayerDetails(int index);
    void outputAllPlayerDetails();
    void outputDiceRollResult(int sum, boolean isEqual);
}
