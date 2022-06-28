package scanner;

import java.util.Scanner;

public class ScannerClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("Hello\nHow are you?\nAre you good?");

        while(scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
    }
}
