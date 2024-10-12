package Views;

import Components.BoxComponent;
import Components.CenteredTextComponent;
import Components.RawTextComponent;
import Models.PlayerIndexer;
import Models.PlayerModel;
import Models.RollResult;
import Utils.Color;
import Utils.Console;
import Utils.Point;
import Utils.Size;
import Views.Interfaces.IDiceGameView;
import java.util.Scanner;

// This class is essentialy the view for the "Good UI".
// I.e it has a GUI with colored text and a pretty layout.
public class VisualDiceGameView extends View implements IDiceGameView {
    private PlayerIndexer _currentSelectedPlayer;
    private final PlayerModel[] _players;
    private AllPlayersView  _allPlayersView;
    private RawTextComponent _gameTitleText;
    private DiceThrowResultView _diceThrowResultView;
    private Scanner _scanner;

    public VisualDiceGameView(PlayerModel[] players, PlayerIndexer index, Scanner scanner){
        // For now we just assume there are only two players,
        // hence the fixed size.
        super(new Point(1,1),new Size(60, 40));

        // I'm using an array for players even,
        // tough there are only two players because
        // it makes it easier to switch between players.
        _players = players;
        _currentSelectedPlayer = index;
        _scanner = scanner;

        initializeView();
    }

    // This should essentially behave as a constant string
    // Its probably bad idea to have this hard-coded, and
    // you would probably rather have this text stored in a file.
    private final String gameTitleText(){
        // Ascii art made using:
        // https://patorjk.com/software/taag/
        return
            " ________  ___  ________  _______\n" +
            "|\\   ___ \\|\\  \\|\\   ____\\|\\  ___ \\\n" +
            "\\ \\  \\_|\\ \\ \\  \\ \\  \\___|\\ \\   __/|\n" +
            " \\ \\  \\ \\\\ \\ \\  \\ \\  \\    \\ \\  \\_|/__\n" +
            "  \\ \\  \\_\\\\ \\ \\  \\ \\  \\____\\ \\  \\_|\\ \\\n" +
            "   \\ \\_______\\ \\__\\ \\_______\\ \\_______\\\n" +
            "    \\|_______|\\|__|\\|_______|\\|_______|\n" +
            " ________  ________  _____ ______   _______\n" +
            "|\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\\n" +
            "\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|\n" +
            " \\ \\  \\  __\\ \\   __  \\ \\  \\\\|__| \\  \\ \\  \\_|/__\n" +
            "  \\ \\  \\|\\  \\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\\n" +
            "   \\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\_______\\\n" +
            "    \\|_______|\\|__|\\|__|\\|__|     \\|__|\\|_______|\n";
    }

    @Override
    public void outputPlayerDetails() {
        // It is generally not good practice to use the fields inside
        // the view itself to update the view.
        // This should probably be avioided if possible
        PlayerModel curPlayer = _players[_currentSelectedPlayer.index];

        _allPlayersView.setPoints(_currentSelectedPlayer.index, curPlayer.points);
        _allPlayersView.setName(_currentSelectedPlayer.index, curPlayer.name);

        update();
    }

    @Override
    public void outputPlayerDetails(int index) {
        PlayerModel curPlayer = _players[index];

        _allPlayersView.setPoints(_currentSelectedPlayer.index, curPlayer.points);
        _allPlayersView.setName(_currentSelectedPlayer.index, curPlayer.name);

        update();
    }

    @Override
    public void outputDiceRollResult(int sum, boolean isEqual) {
        var res = new RollResult(sum, isEqual);

        _diceThrowResultView.setResult(_players[_currentSelectedPlayer.index], res);
        _diceThrowResultView.updateResult();
    }

    private void highlightSelectedPlayer(){
        var name = _players[_currentSelectedPlayer.index].name;
        _allPlayersView.setNameNoResize(_currentSelectedPlayer.index, "["+name+"]");
        _allPlayersView.update();
    }

    private void unHighlightSelectedPlayer(){
        var name = _players[_currentSelectedPlayer.index].name;
        _allPlayersView.setNameNoResize(_currentSelectedPlayer.index, name);
        _allPlayersView.update();
    }

    private void showInputPrompt(){
        highlightSelectedPlayer();
        Point loc = new Point(location.x, location.y+size.height);
        Console.setCursorPosition(loc);

        System.out.print("Press [Enter] to roll dice");
    }

    private void clearInputPrompt(){
        unHighlightSelectedPlayer();
        Point loc1Point = new Point(location.x, location.y+size.height);
        Point loc2Point =  new Point(location.x + size.width, location.y+size.height);
        Console.clearRange(loc1Point, loc2Point);
    }

    // This method will generally not get called outside of this class
    // because you would use the IDiceGameView interface which doesn't.
    // contain the update() method.
    @Override
    public void update() {
        this.clearView();
        _allPlayersView.update();
        _gameTitleText.update();
        _diceThrowResultView.update();
    }

    // This sets up all the components and sub-views
    @Override
    protected final void initializeView() {
        _gameTitleText = new RawTextComponent(new Point(7, 1), new Size(60,15), gameTitleText(), Color.Black, Color.Blue);
        
        int widthAllPlayersView = 25 * (_players.length-1);
        widthAllPlayersView += _players[_players.length-1].name.length() + 6;

        _allPlayersView = new AllPlayersView(new Point(5,20), new Size(widthAllPlayersView, 10), _players);

        Point locDiceThrowView = new Point(_allPlayersView.location.x, _allPlayersView.location.y + _allPlayersView.size.height + 3);
        _diceThrowResultView = new DiceThrowResultView(locDiceThrowView, _allPlayersView.size.width);

        for(int i = 0; i < _players.length; i++){
            _allPlayersView.setPoints(i,0);
            _allPlayersView.setName(i, _players[i].name);
        }

        highlightSelectedPlayer();

        this.clearView();
        _allPlayersView.update();
        _gameTitleText.update();
    }

    @Override
    public void getUserInput() {
        this.showInputPrompt();

        _scanner.nextLine();
        
        this.clearInputPrompt();
    }

    @Override
    public void outputAllPlayerDetails() {
        for(int i = 0; i < _players.length; i++){
            PlayerModel curPlayer = _players[i];
            _allPlayersView.setPoints(i, curPlayer.points);
        }
        update();
    }

    @Override
    public void outputWinningPlayer() {
        clearView();

        // Java will complain if we don't initialize it.
        PlayerModel winningPlayer = _players[0];

        for(int i = 0; i < _players.length; i++){
            if(_players[i].getHasPlayerWon()){
                winningPlayer = _players[i];
            }
        }

        var boxOuter = new BoxComponent(location, size, '#', Color.None, Color.Blue);
        var boxInner = new BoxComponent(new Point(location.x+3, location.y+3), new Size(size.width-6,size.height-6), ' ');
        var winText = new CenteredTextComponent(boxInner.location, boxInner.size, winningPlayer.name + " has won!", Color.None, Color.Yellow);

        boxOuter.update();
        boxInner.update();
        winText.update();
    }
}
