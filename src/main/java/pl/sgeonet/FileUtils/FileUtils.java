package pl.sgeonet.FileUtils;

import pl.sgeonet.FieldObservationsObjects.FieldObservation.DeltaHeight;
import pl.sgeonet.FieldObservationsObjects.PointCoordinates.*;

import java.io.*;
import java.util.*;

import static pl.sgeonet.FieldObservationsObjects.PointCoordinates.PointType.LHE;

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
    private ReadFileResponse readFileResponse;

    public FileUtils() {
    }

    public FileUtils(PointType pointType, T t) {
        this.pointType = pointType;
        this.list = new ArrayList<>();
        this.t = t;
    }

    public List<T> readFile(File file) throws IOException {
        readFileResponse = new ReadFileResponse();
        List<T> returnList = new ArrayList<>();
        String line;
        FileReader fr = new FileReader(file);
        int elementsInOneLine = identifyNumberOfElements(pointType);

        int numberOfLineInFile = 0;
        int correctNumberOfLinesInFile = 0;
        Map<Integer, String> wrongNumberOfArgumentsInLine = new HashMap<>();
        Set<Integer> numberFormatException = new HashSet<>();

        try (BufferedReader f = new BufferedReader(fr)) {
            while ((line = f.readLine()) != null) {
                numberOfLineInFile++;
                String[] splitLine = line.split(",");
                if (splitLine.length == elementsInOneLine) {
                    try {
                        correctNumberOfLinesInFile++;
                        if (t instanceof LHD) {
                            switch (pointType) {
                                case LH:
                                    LHD lhd_1 = new LHD(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), 0.0);
                                    returnList.add((T) lhd_1);
                                    break;
                                case LHD:
                                    LHD lhd_2 = new LHD(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]));
                                    returnList.add((T) lhd_2);
                                    break;
                                case LHDE:
                                    LHD lhd_3 = new LHD(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]), Double.parseDouble(splitLine[4]), Double.parseDouble(splitLine[5]), Double.parseDouble(splitLine[6]));
                                    returnList.add((T) lhd_3);
                                    break;
                                case LHE:
                                    LHD lhd_4 = new LHD(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]), Double.parseDouble(splitLine[4]), 0.0, 0.0);
                                    returnList.add((T) lhd_4);
                                    break;
                            }
                        } else if (t instanceof XYZ) {
                            switch (pointType) {
                                case XYZ:
                                    XYZ xyz_1 = new XYZ(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]));
                                    returnList.add((T) xyz_1);
                                    break;
                                case XYZE:
                                    XYZ xyz_2 = new XYZ(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]), Double.parseDouble(splitLine[4]), Double.parseDouble(splitLine[5]), Double.parseDouble(splitLine[6]));
                                    returnList.add((T) xyz_2);
                                    break;
                            }
                        } else if (t instanceof BLH) {
                            switch (pointType) {
                                case BLH:
                                    BLH blh_1 = new BLH(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]));
                                    returnList.add((T) blh_1);
                                    break;
                                case BLHE:
                                    BLH blh_2 = new BLH(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]), Double.parseDouble(splitLine[4]), Double.parseDouble(splitLine[5]), Double.parseDouble(splitLine[6]));
                                    returnList.add((T) blh_2);
                                    break;
                            }
                        } else if (t instanceof NEH) {
                            switch (pointType) {
                                case NE:
                                    NEH neh_1 = new NEH(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]));
                                    returnList.add((T) neh_1);
                                    break;
                                case NEE:
                                    NEH neh_2 = new NEH(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[3]));
                                    neh_2.setmN(Double.parseDouble(splitLine[2]));
                                    neh_2.setmE(Double.parseDouble(splitLine[4]));
                                    returnList.add((T) neh_2);
                                    break;
                                case NEH:
                                    NEH neh_3 = new NEH(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[2]), Double.parseDouble(splitLine[3]));
                                    returnList.add((T) neh_3);
                                    break;
                                case NEHE:
                                    NEH neh_4 = new NEH(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]), Double.parseDouble(splitLine[3]), Double.parseDouble(splitLine[5]));
                                    neh_4.setmN(Double.parseDouble(splitLine[2]));
                                    neh_4.setmE(Double.parseDouble(splitLine[4]));
                                    neh_4.setmH(Double.parseDouble(splitLine[6]));
                                    returnList.add((T) neh_4);
                                    break;
                                case H:
                                    NEH neh_5 = new NEH(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]));
                                    returnList.add((T) neh_5);
                                    break;
                                case HE:
                                    NEH neh_6 = new NEH(String.valueOf(splitLine[0]), Double.parseDouble(splitLine[1]));
                                    neh_6.setmH(Double.parseDouble(splitLine[2]));
                                    returnList.add((T) neh_6);
                                    break;
                            }
                        }
                    } catch (NumberFormatException e) {
                        numberFormatException.add(numberOfLineInFile);
                    }
                } else {
                    wrongNumberOfArgumentsInLine.put(numberOfLineInFile, splitLine.length + ";" + elementsInOneLine);
                }

            }
        }
        readFileResponse.setTotalNumberOfLinesInFile(numberOfLineInFile);
        readFileResponse.setCorrectNumberOfLinesInFile(correctNumberOfLinesInFile - numberFormatException.size());
        readFileResponse.setWrongNumberOfArgumentsInLine(wrongNumberOfArgumentsInLine);
        readFileResponse.setNumberFormatExceptionSet(numberFormatException);
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
        } else if (pointType == PointType.NEE || pointType == LHE) {
            returnLength = 5;
        } else if (pointType == PointType.XYZE || pointType == PointType.BLHE || pointType == PointType.NEHE || pointType == PointType.LHDE) {
            returnLength = 7;
        }

        return returnLength;
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

    public ReadFileResponse getReadFileResponse() {
        return readFileResponse;
    }
}
