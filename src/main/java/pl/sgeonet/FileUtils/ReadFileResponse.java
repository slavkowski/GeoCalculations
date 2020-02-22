package pl.sgeonet.FileUtils;

import java.util.Map;
import java.util.Set;

public class ReadFileResponse {
    private int totalNumberOfLinesInFile;
    private int correctNumberOfLinesInFile;
    private Map<Integer, String> wrongNumberOfArgumentsInLine;
    private Set<Integer> numberFormatExceptionSet;

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
}
