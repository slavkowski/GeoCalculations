package pl.sats.FileUtils;

import pl.sats.FieldObservationsObjects.DeltaHeight;
import pl.sats.FieldObservationsObjects.PointCoordinates.LHD;
import pl.sats.FieldObservationsObjects.PointCoordinates.NEH;
import pl.sats.FieldObservationsObjects.PointCoordinates.Point;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods responsible for reading files
 * and converting them to different Field Observation Objects
 *
 * @author Slawomir Szwed
 * @version 1.0
 * @since 2018-12-15
 */
public class FileUtils<T extends Point> {

    private T t;

    public FileUtils() {
    }

    public FileUtils(T t) {
        this.t = t;
    }





    public List<T> readFile(File file) throws FileNotFoundException {
        List<T> returnList = new ArrayList<>();
        String line;
        FileReader fr = new FileReader(file);
        int elementsInOneLine = identifyNumberOfElements(t);



        return returnList;
    }

    private int identifyNumberOfElements(T t) {
        int returnLength = 0;
        if(t instanceof LHD ){
            returnLength = 3;
        }

        return returnLength;
    }

    /**
     * This method is responsible for reading txt file and converting it to LHD Object.
     *
     * @param file which contains data in format (String(name of the point), Double(L), Double(H)
     * @return collection of LHD objects
     * @throws IOException IO Exception
     */
    public List<LHD> readLdhFile(File file) throws IOException {
        String line;
        FileReader fr = new FileReader(file);
        List<LHD> setOfLHObservations = new ArrayList<>();

        try (BufferedReader f = new BufferedReader(fr)) {
            while ((line = f.readLine()) != null) {
                LHD LHD = new LHD();
                String[] splitLine = line.split(",");
                if (splitLine.length == 3) {
                    LHD.setId(String.valueOf(splitLine[0]));
                    LHD.setL(Double.valueOf(splitLine[1]));
                    LHD.setH(Double.valueOf(splitLine[2]));
                    setOfLHObservations.add(LHD);
                }
            }
        }
        return setOfLHObservations;
    }

    /**
     * This method is responsible for reading txt file  and converting it to NEH Object.
     *
     * @param file which contains data in format (String(name of the point), Double(H)
     * @return collection of NEH objects
     * @throws IOException IO Exception
     */
    public List<NEH> readNehFile(File file) throws IOException {
        String line;
        FileReader fr = new FileReader(file);
        List<NEH> setOfNehObservations = new ArrayList<>();

        try (BufferedReader f = new BufferedReader(fr)) {
            while ((line = f.readLine()) != null) {
                NEH neh = new NEH();
                String[] splitLine = line.split(",");
                if (splitLine.length == 2) {
                    neh.setId(String.valueOf(splitLine[0]));
                    neh.setH(Double.valueOf(splitLine[1]));
                    setOfNehObservations.add(neh);
                }
            }
        }
        return setOfNehObservations;
    }

    /**
     * This method is responsible for reading txt file  and converting it to DeltaHeight Object.
     *
     * @param file which contains data in format (String(name of the pointFrom), String(name of the pointTo),
     *             Double(height difference between point From and To)
     * @return collection of DeltaHeight objects
     * @throws IOException
     */
    public List<DeltaHeight> readLevelingObservationsWithStdErrors(File file) throws IOException {
        String line;
        FileReader fr = new FileReader(file);
        List<DeltaHeight> setOfDeltaHeightObservations = new ArrayList<>();
        try (BufferedReader f = new BufferedReader(fr)) {
            while ((line = f.readLine()) != null) {
                DeltaHeight deltaHeight = new DeltaHeight();
                String[] splitLine = line.split(",");
                if (splitLine.length == 4) {
                    deltaHeight.setPointFrom(String.valueOf(splitLine[0]));
                    deltaHeight.setPointTo(String.valueOf(splitLine[1]));
                    deltaHeight.setHeightDifferenceValue(Double.valueOf(splitLine[2]));
                    deltaHeight.setHeightDifferenceStdMeanError(Double.valueOf(splitLine[3]));
                    setOfDeltaHeightObservations.add(deltaHeight);
                }
            }
        }
        return setOfDeltaHeightObservations;
    }
    public List<DeltaHeight> readLevelingObservationsWithNumberOfSetups(File file, double errorPerSetup) throws IOException{
        String line;
        FileReader fr = new FileReader(file);
        List<DeltaHeight> setOfDeltaHeightObservations = new ArrayList<>();
        try (BufferedReader f = new BufferedReader(fr)) {
            while ((line = f.readLine()) != null) {
                DeltaHeight deltaHeight = new DeltaHeight();
                String[] splitLine = line.split(",");
                if (splitLine.length == 4) {
                    deltaHeight.setPointFrom(String.valueOf(splitLine[0]));
                    deltaHeight.setPointTo(String.valueOf(splitLine[1]));
                    deltaHeight.setHeightDifferenceValue(Double.valueOf(splitLine[2]));
                    deltaHeight.setHeightDifferenceStdMeanError(errorPerSetup*Math.sqrt(Double.valueOf(splitLine[3])));
                    setOfDeltaHeightObservations.add(deltaHeight);
                }
            }
        }
        return setOfDeltaHeightObservations;
    }
    public List<DeltaHeight> readLevelingObservationsWithLengthOfSection(File file) throws IOException{
        return null;
    }
    public T getT() {
        return t;
    }


}
