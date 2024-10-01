public class DiceGame {
    private Die d1;
    private Die d2;

    public DiceGame(){
        d1 = new Die();
        d2 = new Die();
    }

    public void play(){
        d1.roll();
        d2.roll();
    }

    public int getSum(){
        return d1.getValue() + d2.getValue();
    }

    public boolean getIdentical(){
        return d1.getValue() == d2.getValue();
    }
}
