package Controllers;

import Views.Interfaces.IMainMenuView;
import Models.PlayerModel;

/**
 * This controls the {@link Views.Interfaces.IMainMenuView} view.
 */
public class MainMenuController {
    private IMainMenuView _view;

    public MainMenuController(IMainMenuView view){
        _view = view;
    }

    /**
     * Gets all the players in the game.
     * 
     * @param count how many players are playing
     * @return the players
     */
    public PlayerModel[] getPlayers(int count){
        PlayerModel[] players = new PlayerModel[count];

        for(int i = 0; i < count; i++) {
            players[i] = new PlayerModel(_view.getPlayerName());
        }

        return players;
    }

    /**
     * Calls {@link Views.Interfaces.IMainMenuView#startGame()}
     */
    public void startGame(){
        _view.startGame();
    }
}
