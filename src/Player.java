public class Player {
    private String _playerName;
    private int _points;
    private DiceGame _game;

    public Player(String playerName, DiceGame game){
        _playerName = playerName;
        _game = game;
        _points = 0;
    }

    public int getPoints(){
        return _points;
    }

    public void playRound(){
        _game.play();
    }
}
