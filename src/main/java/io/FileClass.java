package io;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileClass {
    public static void main(String[] args) throws IOException {
        File file = new File("src\\main\\resources\\file.txt");
        File folder = new File("src\\main\\resources\\file_folder");
        File file1 = new File("src\\main\\resources\\file1.txt");
        File folder1 = new File("src\\main\\resources\\file_folder1");

        System.out.println("file.createNewFile() " + file.createNewFile());
        System.out.println("folder.mkdir() " + folder.mkdir());//creates new directory
        System.out.println("--------------------------------");
        System.out.println("file.getAbsolutePath() " + file.getAbsolutePath());
        System.out.println("folder.getAbsolutePath() " + folder.getAbsolutePath());
        System.out.println("--------------------------------");
        System.out.println("file.isAbsolute() " + file.isAbsolute());
        System.out.println("folder.isAbsolute() " + folder.isAbsolute());
        System.out.println("--------------------------------");
        System.out.println("file.isDirectory() " + file.isDirectory());
        System.out.println("folder.isDirectory() " + folder.isDirectory());
        System.out.println("--------------------------------");
        System.out.println("file.exists() " + file.exists());
        System.out.println("folder.exists() " + folder.exists());
        System.out.println("--------------------------------");
        System.out.println("file.createNewFile() " + file1.createNewFile());
        System.out.println("folder.mkdir() " + folder1.mkdir());
        System.out.println("file.delete() " + file1.delete());
        System.out.println("folder.delete() " + folder1.delete());
        System.out.println("--------------------------------");
        System.out.println("file.isHidden() " + file.isHidden());
        System.out.println("folder.canRead() " + folder.canRead());
        System.out.println("folder.canWrite() " + folder.canWrite());
        System.out.println("folder.canExecute() " + folder.canExecute());
        System.out.println("--------------------------------");
        File[] files = folder.listFiles();
        System.out.println("Array of files:");
        Arrays.stream(files).forEach(System.out::println);

    }
}
