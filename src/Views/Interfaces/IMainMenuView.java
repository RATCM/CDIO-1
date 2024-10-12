package Views.Interfaces;

/**
 * <p> Represents the Main menu
 * <p> The program starts with this view
 */
public interface IMainMenuView {
    /**
     * <p> Displays a prompt for the player to
     * <p> enter their name and then returns their name
     */
    String getPlayerName();
    
    /**
     * <p> Displays a prompt to start the game,
     * <p> and continues afterwards
     */
    void startGame();
}
