import java.io.File;
import java.util.List;

public class Program {
    private static final String INITIAL_PATH = new File("").getAbsolutePath() + "\\files";

    private Program() {
    }

    public static void start() {
        RequirementSearcher requirementSearcher = new RequirementSearcher(INITIAL_PATH);
        Concatenator concatenator = new Concatenator();
        requirementSearcher.search();
        if (requirementSearcher.getCondition()) {
            outputFilesToConcatenate(requirementSearcher.getFilesToConcatenate());
            concatenator.concatenateFiles(requirementSearcher.getFilesToConcatenate());
        }
    }

    private static void outputFilesToConcatenate(List<String> filesToConcatenate) {
        System.out.println("Files:\n");
        for (var file : filesToConcatenate) {
            System.out.println(file);
        }
    }
}
