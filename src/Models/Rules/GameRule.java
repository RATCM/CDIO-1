package Models.Rules;

import Controllers.DiceGameController;
import Controllers.PlayerController;
import Models.PlayerIndexer;
import Models.RollResult;

public abstract class GameRule {
    protected PlayerController[] playerStates;
    protected PlayerIndexer index;

    public GameRule(PlayerController[] playerStates, PlayerIndexer index){
        this.playerStates = playerStates;
        this.index = index;
    }
    
    protected PlayerController getCurrentPlayer(){
        return playerStates[index.index];
    }
    

    public abstract boolean isApplicaple(RollResult result);
    public abstract void apply(DiceGameController diceGameState, RollResult result);
}
