package Models;

/**
 * <p> Represents the result of a dice roll
 */
public final class RollResult {
    /**
     * Sum of the dice
     */
    public final int sum;

    /**
     * Whether or not, the dice have the same value
     */
    public final boolean isIdentical;
    
    public RollResult(int sum, boolean isIdentical){
        this.sum = sum;
        this.isIdentical = isIdentical;
    }
    
    /**
     * <p> Checks if the RollResult is equal to some other RollResult
     * 
     * @return true if they are equal, otherwise false
     */
    public boolean equals(RollResult other){
        return this.sum == other.sum && this.isIdentical == other.isIdentical;
    }
}
