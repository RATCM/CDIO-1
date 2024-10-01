import java.util.Random;

public class Die {
    private int _value;
    private Random _rnd;

    public Die(){
        _rnd = new Random();
    }
    
    public int getValue(){
        return _value;
    }
    public void roll(){
        _value = _rnd.nextInt(1,7);
    }
}
