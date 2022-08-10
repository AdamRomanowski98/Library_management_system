package librarySystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerClass {
    private static Scanner scanner = new Scanner(System.in);

    public static String nextLine(){
        return scanner.nextLine();
    }

    public static String next(){
        return scanner.next();
    }

    public static int nextInt(){
        boolean isTrue = false;
        int number = 0;
        do {
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                return number;
            } else {
                scanner.next();
                System.out.println("Enter a valid Integer value");
            }

        }while (!isTrue) ;

        return -1;
    }
}
