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

    public DiceGameController(DiceGameModel model, IDiceGameView view, GameRule[] rules, PlayerIndexer playerIndex){
        _model = model;
        _view = view;
        _rules = rules;

        numberOfPlayers = 2;
        _currentPlayerIndex = playerIndex;
        _playerHasExtraTurn = false;
    }

    public void switchToPlayer(int index){
        _currentPlayerIndex.index = index;
        //_view.outputPlayerDetails();
    }

    public void switchToNextPlayer(){
        // Don't switch if player has extra turn
        if(!_playerHasExtraTurn){
            _currentPlayerIndex.index = (_currentPlayerIndex.index+1)%numberOfPlayers;
        }

        // Make sure the field is always false afterwards
        _playerHasExtraTurn = false;
        
        //_view.outputPlayerDetails();
    }

    public void applyRules(RollResult result){
        for(var rule: _rules){
            if(rule.isApplicaple(result)){
                rule.apply(this);
            }
        }
    }

    public void grantPlayerExtraTurn(){
        _playerHasExtraTurn = true;
    }

    public PlayerIndexer getCurrentPlayerIndex(){
        return _currentPlayerIndex;
    }
}
