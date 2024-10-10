package Models;

public class DiceGameModel {
    private DieModel d1;
    private DieModel d2;

    public DiceGameModel(){
        d1 = new DieModel();
        d2 = new DieModel();
    }

    public RollResult rollDices(){
        d1.roll();
        d2.roll();

        return new RollResult(d1.getValue()+d2.getValue(), d1.getValue() == d2.getValue());
    }
}
