package nio.paths_and_files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsAndFilesClass2 {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("src\\main\\resources\\nio\\file_path.txt");
        Path folderPath = Paths.get("src\\main\\resources\\nio\\folder_path");

        Files.copy(filePath, folderPath.resolve(filePath));

        Files.move(filePath, folderPath.resolve("file_path.txt"));

        Files.delete(filePath);
    }
}
