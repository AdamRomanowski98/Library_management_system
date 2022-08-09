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
        boolean isTrue = true;

        while(isTrue){
            try{
                int number = scanner.nextInt();
                isTrue = false;
                return number;
            }catch (InputMismatchException e) {
                System.out.println("Please enter a number");
            }
        }

        return -1;
    }
}
