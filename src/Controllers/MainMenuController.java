package Controllers;

import Views.Interfaces.IMainMenuView;
import Models.PlayerModel;

public class MainMenuController {
    private IMainMenuView _view;

    public MainMenuController(IMainMenuView view){
        _view = view;
    }

    public PlayerModel[] getPlayers(int count){
        PlayerModel[] players = new PlayerModel[count];

        for(int i = 0; i < count; i++) {
            players[i] = new PlayerModel(_view.getPlayerName());
        }

        return players;
    }

    public void startGame(){
        _view.startGame();
    }
}
