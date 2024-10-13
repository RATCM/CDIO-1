package Models;

/**
 * <p> Represents the result of a dice roll
 */
// Some IDE's might want to change this to a record,
// And we don't want them to do that.
@SuppressWarnings("All")
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
    // Java is complaining that this method isn't used.
    @SuppressWarnings("unused")
    public boolean equals(RollResult other){
        return this.sum == other.sum && this.isIdentical == other.isIdentical;
    }
}
