package io;

import java.io.*;

public class FIleInputStream_OutputStreamClass {
    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream("src\\main\\resources\\java_logo.png");

             FileOutputStream outputStream =
                     new FileOutputStream("src\\main\\resources\\java_logo_copy.png", true);) {
            int i;
            while ((i = inputStream.read()) != -1) {
                outputStream.write(i);
            }

            System.out.println("Done!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
