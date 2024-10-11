import Controllers.*;
import Models.*;
import Models.Rules.*;
import Utils.Console;
import Views.*;
import Views.Interfaces.*;

public class App {
    public static void main(String[] args){
        var scanner = new java.util.Scanner(System.in);
        
        mainMenuSetup(scanner);
        handleMainMenu();
        
        gameSetup(scanner);
        gameLoop();
    }

    private static PlayerController[] playerControllers;
    private static DiceGameController diceGameController;
    private static MainMenuController mainMenuController;

    private static PlayerIndexer currentPlayerIndex;
    private static DiceGameModel diceGame;
    private static PlayerModel[] players;


    private static void mainMenuSetup(java.util.Scanner scanner){
        IMainMenuView view = new VisualMainMenuView(new Utils.Point(1,1), scanner);
        mainMenuController = new MainMenuController(view);
    }

    private static void handleMainMenu(){
        players = mainMenuController.getPlayers(2);
        mainMenuController.startGame();
    }

    private static void gameSetup(java.util.Scanner scanner){
        // [SETUP]:
        Utils.Console.clear();
        currentPlayerIndex = new PlayerIndexer(0);
        
        IDiceGameView view = new VisualDiceGameView(players, currentPlayerIndex, scanner);

        playerControllers = new PlayerController[]{
            new PlayerController(players[0], view),
            new PlayerController(players[1], view)
        };

        playerControllers[0].setPointsBound(0, 40);
        playerControllers[1].setPointsBound(0, 40);
        
        GameRule[] rules = new GameRule[]{
            new DiceSumRule(playerControllers, currentPlayerIndex),
            new TwoIdenticalRule(playerControllers, currentPlayerIndex),
            new WinConditionRule(playerControllers, currentPlayerIndex, 40),
            new TwoOnesRule(playerControllers, currentPlayerIndex),
            new TwoSixesRule(playerControllers, currentPlayerIndex)
        };

        diceGame = new DiceGameModel();
        
        diceGameController = new DiceGameController(diceGame, view, rules, currentPlayerIndex);
    }

    private static void gameLoop(){
        while(!(playerControllers[0].hasWon() || playerControllers[1].hasWon())){
            // Shows an input prompt and waits for user to press enter.
            diceGameController.getUserInput();

            // Get the result of the dice roll
            RollResult result = playerControllers[currentPlayerIndex.index].roll(diceGame);

            diceGameController.applyRules(result);

            // This does nothing if the TwoIdenticalRule/rules[1] is applied
            diceGameController.switchToNextPlayer();
        }

        Console.setCursorPosition(new Utils.Point(1, 50));
    }
}
