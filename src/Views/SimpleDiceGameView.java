package Views;

import Models.PlayerModel;
import Views.Interfaces.IDiceGameView;

// Note that this class doesn't extend the View class
// this is because this view is way more simple
// than the other view classes, and it doesn't
// need any of the methods in the View class
public class SimpleDiceGameView implements IDiceGameView {
    private final PlayerModel[] _players;
    private int _currentSelectedPlayer;

    public SimpleDiceGameView(PlayerModel[] players){
        _players = players;
        _currentSelectedPlayer = 0;

        showTitle();
    }

    private void showTitle(){
        System.out.println("[Dice Game]");
        System.out.println();
        System.out.println();
    }

    @Override
    public void selectPlayer(int index) {
        _currentSelectedPlayer = index;
    }

    @Override
    public void selectNextPlayer() {
        _currentSelectedPlayer = (_currentSelectedPlayer + 1) % _players.length;
    }

    @Override
    public void outputPlayerDetails() {
        var player = _players[_currentSelectedPlayer];

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
        var player = _players[_currentSelectedPlayer];

        // The empty println calls is just a buffer so it's easier to see the results
        System.out.println();
        System.out.println("[" + player.name + "] rolled the dice");
        System.out.println("Sum of dice: " + sum);
        if(isEqual){
            System.out.println("The dices have the same value");
        }
        else{
            System.out.println("The dices doesn't have the same value");
        }
        System.out.println();
    }


}
