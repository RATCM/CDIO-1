package Controllers;

import Models.DiceGameModel;
import Models.PlayerIndexer;
import Models.RollResult;
import Models.Rules.GameRule;
import Views.Interfaces.IDiceGameView;

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

    public void switchToPlayer(int index){
        _currentPlayerIndex.index = index;
    }

    // This method is called at the end of each round
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

    public void grantPlayerExtraTurn(){
        _playerHasExtraTurn = true;
    }

    public PlayerIndexer getCurrentPlayerIndex(){
        return _currentPlayerIndex;
    }

    public void getUserInput(){
        _view.getUserInput();
    }

    RollResult rollDice(){
        return _model.rollDices();
    }

    public void makePlayerWin(){
        _playerHasWon = true;
    }

    public boolean somePlayerHasWon(){
        return _playerHasWon;
    }
}
