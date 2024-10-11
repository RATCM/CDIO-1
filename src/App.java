import Controllers.*;
import Models.*;
import Models.Rules.*;
import Utils.Console;
import Views.*;
import Views.Interfaces.*;

public class App {
    public static void main(String[] args){
        gameLoop();
    }

    private static void gameLoop(){
        // [SETUP]:
        Utils.Console.clear();
        var scanner = new java.util.Scanner(System.in);
        PlayerIndexer currentPlayerIndex = new PlayerIndexer(0);
        PlayerModel[] players = new PlayerModel[]{
            new PlayerModel("Player1"),
            new PlayerModel("Player2")
        };

        IDiceGameView view = new VisualDiceGameView(players, currentPlayerIndex, scanner);

        PlayerController[] playerControllers = new PlayerController[]{
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

        DiceGameModel diceGame = new DiceGameModel();
        
        DiceGameController diceGameController = new DiceGameController(diceGame, view, rules, currentPlayerIndex);

        // [Game Logic]
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
