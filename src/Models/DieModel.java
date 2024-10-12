package Models;

public class DieModel {
    private int _value;
    private java.util.Random _rng;

    public DieModel(){
        _value = -1; // We set the default value to -1
        _rng = new java.util.Random();
    }

    /** 
     * <p> rolls the die and stores the value
     */
    public void roll(){
        _value = _rng.nextInt(6)+1;
    }
    
    
    /** 
     * @return The value of the die
     */
    public int getValue(){
        return _value;
    }
}
