import java.io.File;
import java.io.IOException;
import java.util.*;

public class RequirementSearcher {
    private final List<String> filesToConcatenate;
    private final Set<String> checkedFiles;
    private final File rootDirectory;
    private boolean isWorking;

    public RequirementSearcher(String rootDirectory) {
        this.rootDirectory = new File(rootDirectory);
        filesToConcatenate = new ArrayList<>();
        checkedFiles = new HashSet<>();
        isWorking = true;
    }

    public List<String> getFilesToConcatenate() {
        return filesToConcatenate;
    }

    public boolean getCondition() {
        return isWorking;
    }

    public void search() {
        search(rootDirectory);
    }

    private void search(File initialDirectory) {
        if (!initialDirectory.exists()) {
            System.out.println("File/directory does not exist!");
            return;
        }
        if (!initialDirectory.isDirectory()) {
            System.out.println("Passed object is not a directory!");
            return;
        }
        File[] directoryContents = initialDirectory.listFiles();
        if (directoryContents == null) {
            System.out.println("Directory contents is null");
            return;
        }
        for (var file : directoryContents) {
            if (file.isFile()) {
                findRequirements(file);
            }
        }
        for (var file : directoryContents) {
            if (file.isDirectory()) {
                search(file);
            }
        }
    }

    private void findRequirements(File file) {
        if (!checkedFiles.contains(file.getAbsolutePath())) {
            if (!file.exists()) {
                return;
            }
            checkedFiles.add(file.getAbsolutePath());
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    if (nextLine.startsWith("require")) {
                        findRequirements(new File(rootDirectory.getAbsolutePath()
                                + "\\" + nextLine.substring(8)));
                    }
                }
                scanner.close();
            } catch (IOException e) {
                System.out.println("IO exception caught");
            }
            filesToConcatenate.add(file.getAbsolutePath());
        }
        if (checkedFiles.contains(file.getAbsolutePath()) && !filesToConcatenate.contains(file.getAbsolutePath())) {
            System.out.println("Found cycle dependency!");
            isWorking = false;
        }
    }
}
