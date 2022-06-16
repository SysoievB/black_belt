package io;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterClass {
    public static void main(String[] args) {
        String text = "So perhaps, you've generated some fancy text, \n" +
                "and you're content that you can now copy and paste \n" +
                "your fancy text in the comments section of funny cat \n" +
                "videos, but perhaps you're wondering how it's even \n" +
                "possible to change the font of your text? Is it some \n" +
                "sort of hack? Are you copying and pasting an actual font?\n";

        FileWriter writer = null;
        try {
            writer = new FileWriter("src\\main\\resources\\writer_file.txt", true);//true -for appending symbols to file and to not rewrite file
            for (int i = 0; i < text.length(); i++) {//first case of writing chars
                writer.write(text.charAt(i));
            }
            writer.write(text);//second case of writing chars
            System.out.println("DONE!!!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
