import Components.RawTextComponent;
import Utils.Console;
import Utils.Color;
import Utils.Point;
import Utils.Size;
import Views.PlayerView;

public class App {
    public static void main(String[] args){
        
    }

    private static void sampleUI(){
        Console.clear();
        
        // Ascii art made using:
        // https://patorjk.com/software/taag/
        var diceGameText = 
            " ________  ___  ________  _______\n" +
            "|\\   ___ \\|\\  \\|\\   ____\\|\\  ___ \\\n" +
            "\\ \\  \\_|\\ \\ \\  \\ \\  \\___|\\ \\   __/|\n" +
            " \\ \\  \\ \\\\ \\ \\  \\ \\  \\    \\ \\  \\_|/__\n" +
            "  \\ \\  \\_\\\\ \\ \\  \\ \\  \\____\\ \\  \\_|\\ \\\n" +
            "   \\ \\_______\\ \\__\\ \\_______\\ \\_______\\\n" +
            "    \\|_______|\\|__|\\|_______|\\|_______|\n" +
            " ________  ________  _____ ______   _______\n" +
            "|\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\\n" +
            "\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|\n" +
            " \\ \\  \\  __\\ \\   __  \\ \\  \\\\|__| \\  \\ \\  \\_|/__\n" +
            "  \\ \\  \\|\\  \\ \\  \\ \\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\\n" +
            "   \\ \\_______\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\_______\\\n" +
            "    \\|_______|\\|__|\\|__|\\|__|     \\|__|\\|_______|\n";


        var diceGameView = new RawTextComponent(new Point(7,1), new Size(60,15), diceGameText, Color.Black, Color.Blue);
        var view1 = new PlayerView(new Point(5,20));
        var view2 = new PlayerView(new Point(45,20));

        view1.setPoints(5, true);
        view2.setPoints(10, true);
        
        view1.setName("Player1", true);
        view2.setName("Player2", true);
        
        diceGameView.update();
        view1.update();
        view2.update();

        Console.setCursorPosition(new Point(1, 50));
    }
}
