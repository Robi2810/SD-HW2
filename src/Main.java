import java.io.File;

public class Main {
    public static void main(String[] args) {
        RequirementSearcher requirementSearcher = new RequirementSearcher(new File("").getAbsolutePath() + "\\files");
        requirementSearcher.search();
        requirementSearcher.outputFilesToConcatenate();
        Concatenator concatenator = new Concatenator();
        if (requirementSearcher.getCondition()) {
            concatenator.concatenateFiles(requirementSearcher.getFilesToConcatenate());
        }
    }
}