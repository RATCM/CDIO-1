package Models;

public class DieModel {
    private int _value;
    private java.util.Random _rng;

    public DieModel(){
        _value = -1;
        _rng = new java.util.Random();
    }

    public void roll(){
        _value = _rng.nextInt(6)+1;
    }
    
    public int getValue(){
        return _value;
    }
}
