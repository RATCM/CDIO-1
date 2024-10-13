package Tests;
import Models.DieModel;
import java.time.Instant;

// Java is complaining that this
// isn't used which is annoying.
@SuppressWarnings("unused")
public class DiceTest {
    public static void diceSixTest() {

        var Die = new DieModel();
        int rollCount = 1000;
        double avgDis = rollCount/6.0;
    
        int[] distribution = new int[6];

        //Rolls and stores in array
        for(int i = 0; i < rollCount ; i++){ 
            Die.roll();
            var currentDieValue = Die.getValue();

            distribution[currentDieValue-1] ++ ;

        }
        
        double mean = meanArray(distribution, rollCount);
        double standardDeviation = s_v_Array(distribution, mean, rollCount);

        System.out.println("Test Result of Die");
        System.out.println("Mean: " + mean + "   Expected: " + 3.5);
        System.out.printf(" 1's: %d     Expected: %.2f   Deviation: %.2f%% \n", distribution[0], avgDis, (distribution[0]-avgDis)*100.0/avgDis);
        System.out.printf(" 2's: %d     Expected: %.2f   Deviation: %.2f%% \n", distribution[1], avgDis, (distribution[1]-avgDis)*100.0/avgDis);
        System.out.printf(" 3's: %d     Expected: %.2f   Deviation: %.2f%% \n", distribution[2], avgDis, (distribution[2]-avgDis)*100.0/avgDis);
        System.out.printf(" 4's: %d     Expected: %.2f   Deviation: %.2f%% \n", distribution[3], avgDis, (distribution[3]-avgDis)*100.0/avgDis);
        System.out.printf(" 5's: %d     Expected: %.2f   Deviation: %.2f%% \n", distribution[4], avgDis, (distribution[4]-avgDis)*100.0/avgDis);
        System.out.printf(" 6's: %d     Expected: %.2f   Deviation: %.2f%% \n", distribution[5], avgDis, (distribution[5]-avgDis)*100.0/avgDis);
        System.out.printf("Standard Deviation: %.2f ", standardDeviation);
    }
    
    //Test to check the speed of a roll in ms (averaged over 20 rolls)
    public static void diceTimeTest() {
        var Die = new DieModel();
        
        long timeSum = 0;

        //Rolls and shows the result; stores time elapsed in ms
        for(var i = 0; i < 20 ; i++){
            Instant timeStart = Instant.now();
            Die.roll();
            var currentDieVal = Die.getValue();
            System.out.println("Roll " + i + ": " + currentDieVal);
            Instant timeEnd = Instant.now();
            timeSum = timeSum + (timeEnd.toEpochMilli() - timeStart.toEpochMilli());
        }
        
        //Finds mean computation time
        var meanCompTime = timeSum/20;
        if (meanCompTime < 333) {
            System.out.println("Time test success!");
        }
        else {
            System.out.println("Fail!");
        }
    }

    // Java complains that the variable s_v_ is redundant.
    // But I think it makes sense to do that in this instance
    // for clarity reasons.
    @SuppressWarnings("All")
    static double s_v_Array(int[] values, double mean, int rollCount) {
        double sumVar = 0;
        for (int i = 0 ; i < values.length ; i++) {
            sumVar += Math.pow( (i + 1)  - mean , 2)*values[i];
        }
        double s_v_ = Math.sqrt( sumVar / rollCount ) ;
        return s_v_ ;
    }

    // Java complains that the variable mean is redundant.
    // But I think it makes sense to do that in this instance
    // for clarity reasons.
    @SuppressWarnings("All")
    static double meanArray(int[] values, int rollCount) {
        double sum = 0;
        for (int i = 0 ; i < values.length ; i++) {
            sum += values[i]*(i+1);
        }
        double mean = sum / rollCount ;
        return mean;
    }
}
