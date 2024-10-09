package Tests;
import Models.DieModel;

public class DiceTest {

    public static String diceSixTest() {

        var Die = new DieModel();
        int rollCount = 1000;
    
        int[] distribution = new int[6];

        for(int i = 0; i < rollCount ; i++){ //Rolls and stores in array
        Die.roll();
        var currentDieVal = Die.getValue();

        distribution[currentDieVal-1] ++ ;

        }

        var avgDis = rollCount/6;

        String testResult = "Test Result of Die \n" +
                            "Amount of 1's: " + distribution[0] + "  Expected amount: " + avgDis + "   Deviation: " + (distribution[0]-avgDis)*100.0/avgDis + "%" +
                          "\nAmount of 2's: " + distribution[1] + "  Expected amount: " + avgDis + "   Deviation: " + (distribution[1]-avgDis)*100.0/avgDis + "%" +
                          "\nAmount of 3's: " + distribution[2] + "  Expected amount: " + avgDis + "   Deviation: " + (distribution[2]-avgDis)*100.0/avgDis + "%" +
                          "\nAmount of 4's: " + distribution[3] + "  Expected amount: " + avgDis + "   Deviation: " + (distribution[3]-avgDis)*100.0/avgDis + "%" +
                          "\nAmount of 5's: " + distribution[4] + "  Expected amount: " + avgDis + "   Deviation: " + (distribution[4]-avgDis)*100.0/avgDis + "%" +
                          "\nAmount of 6's: " + distribution[5] + "  Expected amount: " + avgDis + "   Deviation: " + (distribution[5]-avgDis)*100.0/avgDis + "%" ;
        return testResult;
    }

}
