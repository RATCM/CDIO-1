package Controllers;

import Models.DiceGameModel;
import Models.PlayerIndexer;
import Models.RollResult;
import Models.Rules.GameRule;
import Views.Interfaces.IDiceGameView;

/**
 * <p> This controls the {@link Models.DiceGameModel} and {@link Views.Interfaces.IDiceGameView}
 * 
 * <p> It essentially contains the state of the dice game.
 */
public class DiceGameController {
    private DiceGameModel _model;
    private IDiceGameView _view;
    private GameRule[] _rules;
    
    public final int numberOfPlayers;
    private PlayerIndexer _currentPlayerIndex;
    private boolean _playerHasExtraTurn;
    private boolean _playerHasWon;

    public DiceGameController(DiceGameModel model, IDiceGameView view, GameRule[] rules, PlayerIndexer playerIndex){
        _model = model;
        _view = view;
        _rules = rules;

        numberOfPlayers = 2;
        _currentPlayerIndex = playerIndex;
        _playerHasExtraTurn = false;
        _playerHasWon = false;
    }

    /**
     * Changes the {@link Models.PlayerIndexer} field
     * @param index new index
     */
    public void switchToPlayer(int index){
        _currentPlayerIndex.index = index;
    }

    /**
     * <p> Changes the {@link Models.PlayerIndexer} field to the next player and doesn't switch if the current player has an extra turn.
     * 
     * <p> This method should always be called at the end of each round
     */
    public void switchToNextPlayer(){
        // Show the user the new scores
        _view.outputAllPlayerDetails();

        // Don't switch if player has extra turn
        if(!_playerHasExtraTurn){
            _currentPlayerIndex.index = (_currentPlayerIndex.index+1)%numberOfPlayers;
        }

        // Make sure the field is always false afterwards
        _playerHasExtraTurn = false;
    }

    /**
     * Applies all the rules to some {@link Models.RollResult}
     * 
     * @param result The roll for the current round
     */
    public void applyRules(RollResult result){
        for(var rule: _rules){
            if(rule.isApplicaple(result)){
                rule.stage();
                _view.outputAppliedRule(rule);
            }
        }

        for(var rule: _rules){
            rule.apply(this, result);
        }
    }

    /**
     * <p> Grants the player an extra turn.
     * <p> Should only be called by a subclass of {@link Models.Rules.GameRule}
     */
    public void grantPlayerExtraTurn(){
        _playerHasExtraTurn = true;
    }

    /**
     * Calls {@link Views.Interfaces.IDiceGameView#getUserInput()}
     */
    public void getUserInput(){
        _view.getUserInput();
    }

    /**
     * <p> Rolls the dice in the {@link Models.DieModel}
     * 
     * <p> This should only be called by a {@link Controllers.PlayerController}
     * @return The result of the dice throw
     */
    RollResult rollDice(){
        return _model.rollDices();
    }

    /**
     * Tells the class that some player has won.
     */
    public void makePlayerWin(){
        _playerHasWon = true;
    }

    /**
     * @return Whether or not some player has won
     */
    public boolean somePlayerHasWon(){
        return _playerHasWon;
    }
}
