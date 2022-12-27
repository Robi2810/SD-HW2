import java.io.File;

public class Main {
    public static void main(String[] args) {
        RequirementSearcher requirementSearcher = new RequirementSearcher();
        requirementSearcher.findRequirements(
                new File(new File("").getAbsolutePath() + "\\files\\Folder1\\File1-1.txt")
        );
        Concatenator concatenator = new Concatenator();
        concatenator.concatenateFiles(requirementSearcher.getFilesToConcatenate());
    }
}