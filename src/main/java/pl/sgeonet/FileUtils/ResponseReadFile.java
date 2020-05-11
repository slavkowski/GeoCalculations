package pl.sgeonet.FileUtils;

import java.util.Map;
import java.util.Set;

/**
 * This class represents summary of reading data from txt file
 */
public class ResponseReadFile {
    private final String description;
    private int totalNumberOfLinesInFile;
    private int correctNumberOfLinesInFile;
    private Map<Integer, String> wrongNumberOfArgumentsInLine;
    private Set<Integer> numberFormatExceptionSet;

    public ResponseReadFile(String description) {
        this.description = description;
    }

    public int getTotalNumberOfLinesInFile() {
        return totalNumberOfLinesInFile;
    }

    void setTotalNumberOfLinesInFile(int totalNumberOfLinesInFile) {
        this.totalNumberOfLinesInFile = totalNumberOfLinesInFile;
    }

    public int getCorrectNumberOfLinesInFile() {
        return correctNumberOfLinesInFile;
    }

    void setCorrectNumberOfLinesInFile(int correctNumberOfLinesInFile) {
        this.correctNumberOfLinesInFile = correctNumberOfLinesInFile;
    }

    public Map<Integer, String> getWrongNumberOfArgumentsInLine() {
        return wrongNumberOfArgumentsInLine;
    }

    void setWrongNumberOfArgumentsInLine(Map<Integer, String> wrongNumberOfArgumentsInLine) {
        this.wrongNumberOfArgumentsInLine = wrongNumberOfArgumentsInLine;
    }

    public Set<Integer> getNumberFormatExceptionSet() {
        return numberFormatExceptionSet;
    }

    public void setNumberFormatExceptionSet(Set<Integer> numberFormatExceptionSet) {
        this.numberFormatExceptionSet = numberFormatExceptionSet;
    }

    @Override
    public String toString() {
        return "ReadFileResponse for: "+ description + "{" +
                "totalNumberOfLinesInFile=" + totalNumberOfLinesInFile +
                ", correctNumberOfLinesInFile=" + correctNumberOfLinesInFile +
                ", wrongNumberOfArgumentsInLine=" + wrongNumberOfArgumentsInLine +
                ", numberFormatExceptionSet=" + numberFormatExceptionSet +
                '}';
    }
}
