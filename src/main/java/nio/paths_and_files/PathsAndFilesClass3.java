package nio.paths_and_files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PathsAndFilesClass3 {
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("src\\main\\resources\\nio\\read_write.txt");
        Files.createFile(filePath);

        String dialog = "Hello\nHello. How are you?\nI`m fine and you?\nMe too.";
        Files.write(filePath, dialog.getBytes());

        List<String> stringList = Files.readAllLines(filePath);
        stringList.forEach(System.out::println);
    }
}
