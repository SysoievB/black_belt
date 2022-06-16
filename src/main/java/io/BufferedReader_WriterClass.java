package io;

import java.io.*;

public class BufferedReader_WriterClass {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader("src\\main\\resources\\writer_file.txt"));

             BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter("src\\main\\resources\\buffered.txt", true));) {
            int character;
            while ((character = bufferedReader.read()) != -1) {//case of writing chars
                bufferedWriter.write(character);
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {//case of writing strings
                bufferedWriter.write(line);
                bufferedWriter.write("\n");
            }
            System.out.println("Done!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}