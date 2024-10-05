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

    // Info on this method:
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static void setForegroundColor(Color color){
        String esc;
        switch(color){
            case Black:
                esc = "\u001B[30m";
                break;
            case Red:
                esc = "\u001B[31m";
                break;
            case Green:
                esc = "\u001B[32m";
                break;
            case Yellow:
                esc = "\u001B[33m";
                break;
            case Blue:
                esc = "\u001B[34m";
                break;
            case Purple:
                esc = "\u001B[35m";
                break;
            case Cyan:
                esc = "\u001B[36m";
                break;
            case White:
                esc = "\u001B[37m";
                break;
            default:
                esc = "";
                break;
        }

        System.out.print(esc);
    }

    // Info on this method:
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static void setBackgroundColor(Color color){
        String esc;
        switch(color){
            case Black:
                esc = "\u001B[40m";
                break;
            case Red:
                esc = "\u001B[41m";
                break;
            case Green:
                esc = "\u001B[42m";
                break;
            case Yellow:
                esc = "\u001B[43m";
                break;
            case Blue:
                esc = "\u001B[44m";
                break;
            case Purple:
                esc = "\u001B[45m";
                break;
            case Cyan:
                esc = "\u001B[46m";
                break;
            case White:
                esc = "\u001B[47m";
                break;
            default:
                esc = "";
                break;
        }

        System.out.print(esc);
    }

    // Info on this method:
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static void resetColor(){
        System.out.print("\u001B[0m");
    }
}
