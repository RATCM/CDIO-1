import Controllers.*;
import Models.*;
import Models.Rules.*;
import Views.*;
import Views.Interfaces.*;

public class App {
    public static void main(String[] args){
        gameLoop();
    }

    private static void gameLoop(){
        // [SETUP]:
        var scanner = new java.util.Scanner(System.in);
        PlayerIndexer currentPlayerIndex = new PlayerIndexer(0);
        PlayerModel[] players = new PlayerModel[]{
            new PlayerModel("Player1"),
            new PlayerModel("Player2")
        };

        IDiceGameView view = new SimpleDiceGameView(players, currentPlayerIndex);

        PlayerController[] playerControllers = new PlayerController[]{
            new PlayerController(players[0], view),
            new PlayerController(players[1], view)
        };

        playerControllers[0].setPointsBound(0, 40);
        playerControllers[1].setPointsBound(0, 40);
        
        GameRule[] rules = new GameRule[]{
            new WinConditionRule(playerControllers, currentPlayerIndex, 40),
            new DiceSumRule(playerControllers, currentPlayerIndex),
            new TwoIdenticalRule(playerControllers, currentPlayerIndex),
            new TwoOnesRule(playerControllers, currentPlayerIndex),
            new TwoSixesRule(playerControllers, currentPlayerIndex)
        };

        DiceGameModel diceGame = new DiceGameModel();

        
        DiceGameController diceGameController = new DiceGameController(diceGame, view, rules, currentPlayerIndex);

        // [Game Logic]
        while(!(playerControllers[0].hasWon() || playerControllers[1].hasWon())){
            scanner.nextLine();
            var result = playerControllers[currentPlayerIndex.index].roll(diceGame);
            diceGameController.applyRules(result);
            diceGameController.switchToNextPlayer();
        }
    }
}
