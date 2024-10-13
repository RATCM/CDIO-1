package Views;

import Views.Interfaces.IMainMenuView;

@SuppressWarnings("unused")
public class SimpleMainMenuView implements IMainMenuView {
    private final java.util.Scanner _scanner;

    public SimpleMainMenuView(java.util.Scanner scanner){
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
