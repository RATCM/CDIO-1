import Controllers.*;
import Models.*;
import Models.Rules.*;
import Tests.DiceTest;
import Utils.Console;
import Views.*;
import Views.Interfaces.*;

public class App {
    public static void main(String[] args){
        PlayerIndexer playerIndex = new PlayerIndexer(0);
        var scanner = new java.util.Scanner(System.in);


        // Opens the main menu
        // Prompts the players to enter their initials
        // and continues afterward
        IDiceGameView diceGameView;
        IMainMenuView mainMenuView;
        PlayerModel[] players;

        // Check if the args wants us to test the program
        if(args.length > 0 && args[0].equals("probabilityTest")){
            DiceTest.diceSixTest();
            return;
        }
        else if(args.length > 0 && args[0].equals("timeTest")){
            DiceTest.diceTimeTest();
            return;
        }
        else if(args.length > 0 && args[0].equals("simple")){
            // The simple view:
            mainMenuView = getSimpleMainMenuView(scanner);
            players = getPlayersFromUserInput(mainMenuView);

            // Creates the dice game view and outputs
            // it to the screen
            diceGameView = createSimpleGameView(players, playerIndex, scanner);
        }
        else if (args.length == 0){
            // The visual view:
            mainMenuView = getVisualMainMenuView(scanner);
            players = getPlayersFromUserInput(mainMenuView);

            // Creates the dice game view and outputs
            // it to the screen
            diceGameView = createVisualDiceGameView(players, playerIndex, scanner);
        }
        else {
            // The program doesn't recognize the argument
            System.out.println("Invalid argument, exiting program");
            return;
        }

        var playerControllers = createPlayerControllers(players, diceGameView);
        
        var rules = createRules(playerControllers, playerIndex);

        var diceGameController = createDiceGameController(diceGameView, rules, playerIndex);

        // The loop terminates when a player has won
        runGameLoop(diceGameController, playerControllers, playerIndex);
    }

    private static PlayerModel[] getPlayersFromUserInput(IMainMenuView view){
        var mainMenuController = new MainMenuController(view);

        var players = mainMenuController.getPlayers(2);
        mainMenuController.startGame();

        return players;
    }

    private static IMainMenuView getVisualMainMenuView(java.util.Scanner scanner){
        return new VisualMainMenuView(new Utils.Point(1,1), scanner);
    }

    private static IMainMenuView getSimpleMainMenuView(java.util.Scanner scanner){
        return new SimpleMainMenuView(scanner);
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


    private static IDiceGameView createVisualDiceGameView(PlayerModel[] players, PlayerIndexer playerIndex, java.util.Scanner scanner){
        return new VisualDiceGameView(players, playerIndex, scanner);
    }

    private static IDiceGameView createSimpleGameView(PlayerModel[] players, PlayerIndexer playerIndex, java.util.Scanner scanner){
        return new SimpleDiceGameView(players, playerIndex, scanner);
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
            // This is important so we don't call
            // switchToNextPlayer when a player has won.
            if(diceGameController.somePlayerHasWon()){
                break;
            }

            // This does nothing if the TwoIdenticalRule/rules[1] is applied
            diceGameController.switchToNextPlayer();
        }
    }
}
