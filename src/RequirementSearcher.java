import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RequirementSearcher {
    private final List<String> filesToConcatenate;

    public RequirementSearcher() {
        filesToConcatenate = new ArrayList<>();
    }

    public List<String> getFilesToConcatenate() {
        return filesToConcatenate;
    }

    public void findRequirements(File file) {
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    if (nextLine.startsWith("require")) {
                        findRequirements(new File(new File("").getAbsolutePath()
                                + nextLine.substring(8)));
                    }
                }
                scanner.close();
            } catch (IOException e) {
                System.out.println("IO exception caught");
            }
            filesToConcatenate.add(file.getAbsolutePath());
        }
    }
}
