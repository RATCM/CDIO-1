package Views;

import Models.PlayerIndexer;
import Models.PlayerModel;
import Views.Interfaces.IDiceGameView;
import java.util.Scanner;

// Note that this class doesn't extend the View class
// this is because this view is way more simple
// than the other view classes, and it doesn't
// need any of the methods in the View class
public class SimpleDiceGameView implements IDiceGameView {
    private final PlayerModel[] _players;
    private PlayerIndexer _index;
    private Scanner _scanner;

    public SimpleDiceGameView(PlayerModel[] players, PlayerIndexer index, Scanner scanner){
        _players = players;
        this._index = index;
        _scanner = scanner;

        showTitle();
    }

    private void showTitle(){
        System.out.println("[Dice Game]");
        System.out.println();
    }

    private void showInputPrompt(){
        System.out.println();
        System.out.printf("%s's turn\n", _players[_index.index].name);
        System.out.println("Press Enter to roll dice");
    }

    @Override
    public void outputPlayerDetails() {
        var player = _players[_index.index];

        System.out.println();
        System.out.println("["+player.name + "] has:");
        System.out.println(player.points + " points");
        System.out.println();
    }

    @Override
    public void outputPlayerDetails(int index) {
        var player = _players[index];

        System.out.println();
        System.out.println("["+player.name + "] has:");
        System.out.println(player.points + " points");
        System.out.println();
    }


    @Override
    public void outputDiceRollResult(int sum, boolean isEqual) {
        var player = _players[_index.index];

        // The empty println calls is just a buffer so it's easier to see the results
        System.out.println();
        System.out.println("[" + player.name + "] rolled the dice");
        System.out.println("Sum of dice: " + sum);
        if(isEqual){
            System.out.println("The dice have the same value");
        }
        else{
            System.out.println("The dice don't have the same value");
        }
        System.out.println();
    }

    @Override
    public void getUserInput() {
        showInputPrompt();

        _scanner.nextLine();
    }

    @Override
    public void outputAllPlayerDetails() {
        for(int i = 0; i < _players.length; i++){
            outputPlayerDetails(i);
        }
    }
}
