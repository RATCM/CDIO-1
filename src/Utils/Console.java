package Utils;

public class Console {
    // Info on this method:
    // https://stackoverflow.com/questions/1001335/move-console-cursor-to-specified-position

    /**
     * Sets the cursor position in the terminal to some new location
     * @param location
     */
    public static void setCursorPosition(Point location){
        char escCode = 0x1B;
        System.out.print(String.format("%c[%d;%df", escCode, location.y, location.x));
    }

    // Code from:
    // https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java
    /**
     * Clears the console
     */
    public static void clear(){
        // Note: This might not work on all terminals
        System.out.print("\033[H\033[2J");
    }

    // Info on this method:
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    /**
     * Sets the foreground/text color in the terminal to some new color
     * @param color
     */
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
    /**
     * Sets the background color of the text, in the terminal to some new color
     * @param color
     */
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
    /**
     * Resets the colors
     */
    public static void resetColor(){
        System.out.print("\u001B[0m");
    }

    /**
     * Clears the range between {@link point1} and {@link point2}
     * 
     * @param point1
     * @param point2
     */
    public static void clearRange(Point point1, Point point2){
        // Make sure point1 is in top left and point2 is in the bottom right
        Point topLeft = new Point(Math.min(point1.x, point2.x), Math.min(point1.y, point2.y));
        Point bottomRight = new Point(Math.max(point1.x, point2.x), Math.max(point1.y, point2.y));
        
        // Calculate the output
        int width = bottomRight.x - topLeft.x;
        String outputLine = " ".repeat(width);

        // Output to console
        for(int y = topLeft.y; y <= bottomRight.y; y++){
            int x = topLeft.x;
            setCursorPosition(new Point(x,y));
            System.out.println(outputLine);
        }
    }
}
