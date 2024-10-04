package Utils;

public class Console {
    // Info on this method:
    // https://stackoverflow.com/questions/1001335/move-console-cursor-to-specified-position
    public static void setCursorPosition(Point location){
        char escCode = 0x1B;
        System.out.print(String.format("%c[%d;%df", escCode, location.y, location.x));
    }

    // Code from:
    // https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java
    public static void clear(){
        // Note: This might not work on all terminals
        System.out.print("\033[H\033[2J");
    }
}
