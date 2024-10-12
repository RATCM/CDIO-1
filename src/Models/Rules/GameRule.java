package Models.Rules;

import Controllers.DiceGameController;
import Controllers.PlayerController;
import Models.PlayerIndexer;
import Models.RollResult;

public abstract class GameRule {
    protected PlayerController[] playerStates;
    protected PlayerIndexer index;
    protected boolean staged;

    public GameRule(PlayerController[] playerStates, PlayerIndexer index){
        this.playerStates = playerStates;
        this.index = index;
        staged = false;
    }
    
    protected PlayerController getCurrentPlayer(){
        return playerStates[index.index];
    }
    

    public abstract boolean isApplicaple(RollResult result);
    protected abstract void applyRule(DiceGameController diceGameState, RollResult result);

    // You call thie method to apply the rule
    // The reason why we don't just do it in the abstract
    // method is beacuase of the D.R.Y. principle
    public final void apply(DiceGameController diceGameState, RollResult result){
        if(staged){
            applyRule(diceGameState, result);
        }
        staged = false;
    }

    public void stage(){
        staged = true;
    }

    public abstract String getConditionDescription();
    public abstract String getDescription();
}
