package Models;

public class DiceGameModel {
    private DieModel d1;
    private DieModel d2;

    public DiceGameModel(){
        d1 = new DieModel();
        d2 = new DieModel();
    }

    
    
    /** 
     * <p> This method rolls the dice
     * 
     * @return The result of the roll
     */
    public RollResult rollDices(){
        d1.roll();
        d2.roll();

        return new RollResult(d1.getValue()+d2.getValue(), d1.getValue() == d2.getValue());
    }
}
