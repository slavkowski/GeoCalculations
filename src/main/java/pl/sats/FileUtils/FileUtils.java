package pl.sats.FileUtils;

import pl.sats.FieldObservationsObjects.DeltaHeight;
import pl.sats.FieldObservationsObjects.PointCoordinates.LDH;
import pl.sats.FieldObservationsObjects.PointCoordinates.NEH;

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
public class FileUtils {
    /**
     * This method is responsible for reading txt file and converting it to LDH Object.
     *
     * @param file which contains data in format (String(name of the point), Double(L), Double(H)
     * @return collection of LDH objects
     * @throws IOException IO Exception
     */
    public List<LDH> readLdhFile(File file) throws IOException {
        String line;
        FileReader fr = new FileReader(file);
        List<LDH> setOfLHObservations = new ArrayList<>();

        try (BufferedReader f = new BufferedReader(fr)) {
            while ((line = f.readLine()) != null) {
                LDH ldh = new LDH();
                String[] splitLine = line.split(",");
                if (splitLine.length == 3) {
                    ldh.setId(String.valueOf(splitLine[0]));
                    ldh.setL(Double.valueOf(splitLine[1]));
                    ldh.setH(Double.valueOf(splitLine[2]));
                    setOfLHObservations.add(ldh);
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

}
