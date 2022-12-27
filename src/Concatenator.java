import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Concatenator {
    public Concatenator() {

    }

    public void concatenateFiles(List<String> paths) {
        File resultFile = new File("output.txt");
        for (var path : paths) {
            if (Files.exists(Path.of(path))) {
                File file = new File(path);
                try (PrintWriter printWriter = new PrintWriter(resultFile)) {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        String nextLine = scanner.nextLine();
                        printWriter.println(nextLine);
                    }
                    scanner.close();
                } catch (FileNotFoundException exception) {
                    System.out.println("File not found");
                }
            }
        }
    }
}
