package Tests;
import Models.DieModel;
import java.time.Instant;

public class DiceTest {

    

    public static String diceSixTest() {

        var Die = new DieModel();
        int rollCount = 1000;
        double avgDis = rollCount/6;        
    
        int[] distribution = new int[6];

        for(int i = 0; i < rollCount ; i++){ //Rolls and stores in array
            Die.roll();
            var currentDieValue = Die.getValue();

            distribution[currentDieValue-1] ++ ;

        }
        
        double mean = meanArray(distribution, rollCount);
        double standardDeviation = s_v_Array(distribution, mean, rollCount);

        String testResult = "Test Result of Die \n" +
                            "Amount of 1's: " + distribution[0] + "  Expected amount: " + avgDis + "   Deviation: " + (distribution[0]-avgDis)*100.0/avgDis + "%" +
                          "\nAmount of 2's: " + distribution[1] + "  Expected amount: " + avgDis + "   Deviation: " + (distribution[1]-avgDis)*100.0/avgDis + "%" +
                          "\nAmount of 3's: " + distribution[2] + "  Expected amount: " + avgDis + "   Deviation: " + (distribution[2]-avgDis)*100.0/avgDis + "%" +
                          "\nAmount of 4's: " + distribution[3] + "  Expected amount: " + avgDis + "   Deviation: " + (distribution[3]-avgDis)*100.0/avgDis + "%" +
                          "\nAmount of 5's: " + distribution[4] + "  Expected amount: " + avgDis + "   Deviation: " + (distribution[4]-avgDis)*100.0/avgDis + "%" +
                          "\nAmount of 6's: " + distribution[5] + "  Expected amount: " + avgDis + "   Deviation: " + (distribution[5]-avgDis)*100.0/avgDis + "%" +
                          "\nStandard Deviation: " + standardDeviation + 
                          "\nMean: " + mean ;
        return testResult;
    }
    
    //Test to check the speed of a roll in ms (averaged over 20 rolls)
    public static void diceTimeTest() {
        var Die = new DieModel();
        
        long timeSum = 0;
        Instant timeStart = Instant.now();
        Instant timeEnd = Instant.now();
        
        //Rolls and shows the result; stores time elapsed in ms
        for(var i = 0; i < 20 ; i++){ 
            timeStart = Instant.now();
            Die.roll();
            var currentDieVal = Die.getValue();
            System.out.println("Roll " + i + ": " + currentDieVal);
            timeEnd = Instant.now();
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

    static double s_v_Array(int values[], double mean, int rollCount) {
        double sumVar = 0;
        for (int i = 0 ; i < values.length ; i++) {
            sumVar += Math.pow( (i + 1)  - mean , 2)*values[i];
        }
        double s_v_ = Math.sqrt( sumVar / rollCount ) ;
        return s_v_ ;
    }

    static double meanArray(int[] values, int rollCount) {
        double sum = 0;
        for (int i = 0 ; i < values.length ; i++) {
            sum += values[i]*(i+1);
        }
        double mean = sum / rollCount ;
        return mean;
    }
}
