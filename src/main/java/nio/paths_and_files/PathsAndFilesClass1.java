package nio.paths_and_files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsAndFilesClass1 {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("src\\main\\resources\\nio\\file_path.txt");
        Path folderPath = Paths.get("src\\main\\resources\\nio\\folder_path");

        System.out.println("filePath.getFileName() " + filePath.getFileName());
        System.out.println("folderPath.getFileName() " + folderPath.getFileName());
        System.out.println("------------------------------------");
        System.out.println("filePath.getParent() " + filePath.getParent());
        System.out.println("folderPath.getParent() " + folderPath.getParent());
        System.out.println("------------------------------------");
        System.out.println("filePath.getRoot() " + filePath.getRoot());
        System.out.println("folderPath.getRoot() " + folderPath.getRoot());
        System.out.println("------------------------------------");
        System.out.println("filePath.isAbsolute() " + filePath.isAbsolute());
        System.out.println("folderPath.isAbsolute() " + folderPath.isAbsolute());
        System.out.println("------------------------------------");

        System.out.println("filePath.toAbsolutePath() " + filePath.toAbsolutePath());
        System.out.println("folderPath.toAbsolutePath() " + folderPath.toAbsolutePath());
        System.out.println("------------------------------------");
        System.out.println("filePath.toAbsolutePath().getParent() " + filePath.toAbsolutePath().getParent());
        System.out.println("folderPath.toAbsolutePath().getRoot() " + folderPath.toAbsolutePath().getRoot());
        System.out.println("------------------------------------");
        System.out.println("filePath.toAbsolutePath().getParent().isAbsolute() " + filePath.toAbsolutePath().getParent().isAbsolute());
        System.out.println("folderPath.toAbsolutePath().getRoot().isAbsolute() " + folderPath.toAbsolutePath().getRoot().isAbsolute());
        System.out.println("------------------------------------");

        Path anotherPath = Paths.get("src\\main\\resources\\nio\\folder_path\\file_txt.txt");
        System.out.println("folderPath.relativize(anotherPath) " + folderPath.relativize(anotherPath));
        System.out.println("------------------------------------");

        if (!Files.exists(anotherPath)) {
            Files.createFile(anotherPath);
        }

        System.out.println("Files.isReadable(anotherPath) " + Files.isReadable(anotherPath));
        System.out.println("Files.isWritable(anotherPath) " + Files.isWritable(anotherPath));
        System.out.println("Files.isExecutable(anotherPath) " + Files.isExecutable(anotherPath));
        System.out.println("---------------------------------------");

        Path filePath2 = Paths.get("C:\\Users\\Bogdan\\IdeaProjects\\black_belt\\src\\main\\resources\\nio\\file_path.txt");
        System.out.println("Files.isSameFile(filePath, filePath2) " + Files.isSameFile(filePath, filePath2));

        System.out.println("---------------------------------------");
        System.out.println("Files.size(filePath2) " + Files.size(filePath2));

        System.out.println("---------------------------------------");
        System.out.println("Files.getAttribute(filePath2,\"creationTime\") " + Files.getAttribute(filePath2, "creationTime"));

        System.out.println("---------------------------------------");
        System.out.println("Files.readAttributes(filePath2,\"*\")");

        Files.readAttributes(filePath2, "*").forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
