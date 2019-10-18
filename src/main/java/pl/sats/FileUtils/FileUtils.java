package pl.sats.FileUtils;

import pl.sats.FieldObservationsObjects.DeltaHeight;
import pl.sats.FieldObservationsObjects.PointCoordinates.*;

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

    private List<T> list;
    private T t;
    private PointType pointType;

    public FileUtils() {
    }

    public FileUtils(PointType pointType, T t) {
        this.pointType = pointType;
        this.list = new ArrayList<>();
        this.t = t;
    }

    public List<T> readFile(File file) throws IOException {
        List<T> returnList = new ArrayList<>();
        String line;
        FileReader fr = new FileReader(file);
        int elementsInOneLine = identifyNumberOfElements(pointType);
        if (t instanceof LHD){
            try (BufferedReader f = new BufferedReader(fr)) {
                while ((line = f.readLine()) != null) {
                    String[] splitLine = line.split(",");
                    if (splitLine.length == elementsInOneLine) {
                        returnList.add((T) new LHD(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), 0.0));
                    }
                }
            }
        }


        return returnList;
    }

    private int identifyNumberOfElements(PointType pointType) {

        int returnLength = 0;

        if (pointType == PointType.H) {
            returnLength = 2;
        } else if (pointType == PointType.NE || pointType == PointType.HE || pointType == PointType.LH) {
            returnLength = 3;
        } else if (pointType == PointType.XYZ || pointType == PointType.BLH || pointType == PointType.NEH || pointType == PointType.LHD) {
            returnLength = 4;
        } else if (pointType == PointType.NEE || pointType == PointType.LHE) {
            returnLength = 5;
        } else if (pointType == PointType.XYZE || pointType == PointType.BLHE || pointType == PointType.NEHE || pointType == PointType.LHDE) {
            returnLength = 7;
        }

        return returnLength;
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

    public List<DeltaHeight> readLevelingObservationsWithNumberOfSetups(File file, double errorPerSetup) throws IOException {
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
                    deltaHeight.setHeightDifferenceStdMeanError(errorPerSetup * Math.sqrt(Double.valueOf(splitLine[3])));
                    setOfDeltaHeightObservations.add(deltaHeight);
                }
            }
        }
        return setOfDeltaHeightObservations;
    }

    public List<DeltaHeight> readLevelingObservationsWithLengthOfSection(File file) throws IOException {
        return null;
    }

    public List<T> getListT() {
        return list;
    }

    public T getT() {
        return t;
    }
}
