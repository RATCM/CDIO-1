package Views;

import Components.RawTextComponent;
import Models.PlayerIndexer;
import Models.PlayerModel;
import Utils.Color;
import Utils.Point;
import Utils.Size;
import Views.Interfaces.IDiceGameView;

// This class is essentialy the view for the "Good UI".
// I.e it has a GUI with colored text and a pretty layout.
public class VisualDiceGameView extends View implements IDiceGameView {
    private PlayerIndexer _currentSelectedPlayer;
    private final PlayerModel[] _players;
    private AllPlayersView  _allPlayersView;
    private RawTextComponent _gameTitleText;

    public VisualDiceGameView(PlayerModel[] players, PlayerIndexer index){
        // For now we just assume there are only two players,
        // hence the fixed size.
        super(new Point(1,1),new Size(60, 35));

        // I'm using an array for players even,
        // tough there are only two players because
        // it makes it easier to switch between players.
        _players = players;
        _currentSelectedPlayer = index;

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


    // TODO: Create a view for the Dice roll results and use it in this method
    @Override
    public void outputDiceRollResult(int sum, boolean isEqual) {
        
    }

    private void highlightSelectedPlayer(){
        var name = _players[_currentSelectedPlayer.index].name;
        _allPlayersView.setName(_currentSelectedPlayer.index, "["+name+"]");
    }

    private void unHighlightSelectedPlayer(){
        var name = _players[_currentSelectedPlayer.index].name;
        _allPlayersView.setName(_currentSelectedPlayer.index, name);
    }

    // This method will generally not get called outside of this class
    // because you would use the IDiceGameView interface which doesn't.
    // contain the update() method.
    @Override
    public void update() {
        this.clearView();
        _allPlayersView.update();
        _gameTitleText.update();
    }

    // This sets up all the components and sub-views
    @Override
    protected final void initializeView() {
        _gameTitleText = new RawTextComponent(new Point(7, 1), new Size(60,15), gameTitleText(), Color.Black, Color.Blue);
        _allPlayersView = new AllPlayersView(new Point(5,20), _players.length);

        for(int i = 0; i < _players.length; i++){
            _allPlayersView.setPoints(i,0);
            _allPlayersView.setName(i, _players[i].name);
        }

        highlightSelectedPlayer();
        update();
    }
}
