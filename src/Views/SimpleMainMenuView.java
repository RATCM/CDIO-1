package Views;

import Models.PlayerIndexer;
import Views.Interfaces.IDiceGameView;
import Views.Interfaces.IMainMenuView;

public class SimpleMainMenuView implements IMainMenuView {
    private PlayerIndexer _playerIndex;
    private java.util.Scanner _scanner;

    public SimpleMainMenuView(PlayerIndexer playerIndex, java.util.Scanner scanner){
        _playerIndex = playerIndex;
        _scanner = scanner;

        showTitle();
    }

    private void showTitle(){
        System.out.println("Main Menu:");
        System.out.println();
    }

    @Override
    public String getPlayerName() {
        System.out.print("Enter name for player: ");
        String name = _scanner.nextLine();
        System.out.println();
        return name;
    }

    @Override
    public void startGame() {
        System.out.println("Press [Enter] to start game");
        _scanner.nextLine();
        System.out.println();
        System.out.println();
    }
}
