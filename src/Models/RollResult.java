package Models;

public final class RollResult {
    public final int sum;
    public final boolean isIdentical;
    
    public RollResult(int sum, boolean isIdentical){
        this.sum = sum;
        this.isIdentical = isIdentical;
    }

    public boolean equals(RollResult other){
        return this.sum == other.sum && this.isIdentical == other.isIdentical;
    }
}
