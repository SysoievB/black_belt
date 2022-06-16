package io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileClass {
    public static void main(String[] args) {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("src\\main\\resources\\raf.txt", "rw")) {

            int a = randomAccessFile.read();
            System.out.println((char) a);//reads one char

            String line = randomAccessFile.readLine();
            System.out.println(line);//reads line

            randomAccessFile.seek(100);
            String line1 = randomAccessFile.readLine();
            System.out.println(line1);//reads line from the 100 char

            long position = randomAccessFile.getFilePointer();
            System.out.println(position);//returns the pointer position in file

            //write new word to the end of file
            randomAccessFile.seek(randomAccessFile.length() - 1);
            randomAccessFile.writeBytes("\n\t\t\t\t\t\t\tUnknown author");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
