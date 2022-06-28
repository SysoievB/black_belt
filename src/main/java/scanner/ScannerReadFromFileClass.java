package scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ScannerReadFromFileClass {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src\\main\\resources\\scanner_file.txt"));
        Set<String> set = new TreeSet<>();
        String str;
        while (scanner.hasNext()) {
            str = scanner.next();
            if (str.startsWith("p")) {
                set.add(str.replaceAll("[,.]", ""));
            }
        }
        set.forEach(System.out::println);
    }
}
