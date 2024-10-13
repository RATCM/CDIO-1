package Models.Rules;

import Controllers.DiceGameController;
import Controllers.PlayerController;
import Models.PlayerIndexer;
import Models.RollResult;

/**
 * Represents an abstract game rule
 */
public abstract class GameRule {
    protected PlayerController[] playerStates;
    protected PlayerIndexer index;
    protected boolean staged;

    public GameRule(PlayerController[] playerStates, PlayerIndexer index){
        this.playerStates = playerStates;
        this.index = index;
        staged = false;
    }
    
    /** 
     * This method returns the current {@link Controllers.PlayerController} in use
     * 
     * @return PlayerController
     */
    protected PlayerController getCurrentPlayer(){
        return playerStates[index.index];
    }
    
    /** 
     * Validates a rule
     * 
     * @param result the result of the dice roll
     * @return true is the rule is applicable, otherwise false
     */
    public abstract boolean isApplicaple(RollResult result);

    /** 
     * <p> This method contains the actual logic for how the rule is applied.
     * <p> This shouldn't be called anywhere outside of this class
     * 
     * @param diceGameState the DiceGameController
     * @param result the result of the dice roll
     */
    protected abstract void applyRule(DiceGameController diceGameState, RollResult result);
    
    /** 
     * <p> This method applies the rule for the {@link Controllers.DiceGameController} if the rule has been staged.
     * <p> The rule gets unstaged after this method is called
     *
     * @param diceGameState the DiceGameController
     * @param result the result of the dice roll
     */
    public final void apply(DiceGameController diceGameState, RollResult result){
        if(staged){
            applyRule(diceGameState, result);
        }
        staged = false;
    }

    /** 
     * <p> This stages the rule to be applied  after {@link #apply} is called
     */
    public void stage(){
        staged = true;
    }

    /** 
     * @return A description of when the rule is applied
     */
    // Java complains that this method is unused.
    @SuppressWarnings("unused")
    public abstract String getConditionDescription();

    /** 
     * @return A description of what the rule does when applied
     */
    public abstract String getDescription();
}
