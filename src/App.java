import Controllers.*;
import Models.*;
import Models.Rules.*;
import Utils.Console;
import Views.*;
import Views.Interfaces.*;

public class App {
    public static void main(String[] args){
        PlayerIndexer playerIndex = new PlayerIndexer(0);
        var scanner = new java.util.Scanner(System.in);
        
        // Opens the main menu
        // Prompts the players to enter their initials
        // and continues afterwards
        var players = getPlayersFromUserInput(scanner);
        
        // Creates the dice game view and outputs
        // it to the screen
        var diceGameView = createDiceGameView(players, playerIndex, scanner);

        var playerControllers = createPlayerControllers(players, diceGameView);
        
        var rules = createRules(playerControllers, playerIndex);

        var diceGameController = createDiceGameController(diceGameView, rules, playerIndex);

        // The loop terminates when a player has won
        runGameLoop(diceGameController, playerControllers, playerIndex);
    }

    private static PlayerModel[] getPlayersFromUserInput(java.util.Scanner scanner){
        IMainMenuView view = new VisualMainMenuView(new Utils.Point(1,1), scanner);
        var mainMenuController = new MainMenuController(view);

        var players = mainMenuController.getPlayers(2);
        mainMenuController.startGame();

        return players;
    }

    private static PlayerController[] createPlayerControllers(PlayerModel[] players, IDiceGameView view){
        var controllers = new PlayerController[]{
            new PlayerController(players[0], view),
            new PlayerController(players[1], view)
        };

        controllers[0].setPointsBound(0, 40);
        controllers[1].setPointsBound(0, 40);

        return controllers;
    }

    private static GameRule[] createRules(PlayerController[] playerControllers, PlayerIndexer playerIndex){
        final int pointsToWin = 40;

        return new GameRule[]{
            new WinConditionRule(playerControllers, playerIndex, pointsToWin), // Always check the win condition first
            new DiceSumRule(playerControllers, playerIndex, pointsToWin),
            new TwoIdenticalRule(playerControllers, playerIndex),
            new TwoOnesRule(playerControllers, playerIndex),
            new TwoSixesRule(playerControllers, playerIndex)
        };
    }


    private static IDiceGameView createDiceGameView(PlayerModel[] players, PlayerIndexer playerIndex, java.util.Scanner scanner){
        return new VisualDiceGameView(players, playerIndex, scanner);
    }

    private static DiceGameController createDiceGameController(IDiceGameView view, GameRule[] rules, PlayerIndexer playerIndex){
        var diceGame = new DiceGameModel();
        return new DiceGameController(diceGame, view, rules, playerIndex);
    }

    private static void runGameLoop(DiceGameController diceGameController, PlayerController[] playerControllers, PlayerIndexer playerIndex){
        while(!(diceGameController.somePlayerHasWon())){
            // Shows an input prompt and waits for user to press enter.
            diceGameController.getUserInput();

            // Get the result of the dice roll
            RollResult result = playerControllers[playerIndex.index].roll(diceGameController);

            diceGameController.applyRules(result);

            // Break the loop if a player has won
            // This is impotant so we dont call
            // switchToNextPlayer when a player has won.
            if(diceGameController.somePlayerHasWon()){
                break;
            }

            // This does nothing if the TwoIdenticalRule/rules[1] is applied
            diceGameController.switchToNextPlayer();
        }

        Console.setCursorPosition(new Utils.Point(1, 50));
    }
}
