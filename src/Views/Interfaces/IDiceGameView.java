package Views.Interfaces;

public interface IDiceGameView {
    void outputPlayerDetails();
    void outputPlayerDetails(int index);
    void outputDiceRollResult(int sum, boolean isEqual);
}
