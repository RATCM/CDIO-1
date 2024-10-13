package Views.Interfaces;

import Models.Rules.GameRule;

/**
 * <p> Represents the Dice game
 * <p> This is where the program runs for most of the time
 */
public interface IDiceGameView {
    /**
     * Halts the program until the user have given some input to continue
     */
    void getUserInput();

    /**
     * Outputs the player detail of the selected player
     */
    // annoying warning
    @SuppressWarnings("unused")
    void outputPlayerDetails();
    
    /**
     * Outputs the player details of the player at some index
     * @param index the index of the player
     */
    void outputPlayerDetails(int index);

    /**
     * Outputs the details of all the players
     */
    void outputAllPlayerDetails();

    /**
     * Outputs the roll result of the dice throw
     * @param sum the sum of dice
     * @param isEqual Whether the dice values are equal
     */
    void outputDiceRollResult(int sum, boolean isEqual);

    /**
     * <p> This displays the player who has won,
     * <p> It shouldn't be called if no player has won yet
     */
    void outputWinningPlayer();

    /**
     * This outputs a rule that has been applied
     * @param rule the applied {@link Models.Rules.GameRule}
     */
    void outputAppliedRule(GameRule rule);
}
