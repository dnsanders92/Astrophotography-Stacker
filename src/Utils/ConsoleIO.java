package Utils;

import java.util.Scanner;

public class ConsoleIO {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void print(String input) {
        System.out.print(input);
    }

    public static void println(String input) {
        System.out.println(input);
    }

    public static <T> void printArray(T[] array) {
        for (int i = 0; i < array.length; i++) {
            println(String.valueOf(array[i]));
        }
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String readLine() {
        return SCANNER.nextLine();
    }

}
