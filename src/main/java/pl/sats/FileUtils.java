package pl.sats;

import pl.sats.FieldObservationsObjects.DeltaHeight;
import pl.sats.FieldObservationsObjects.PointLDH;
import pl.sats.FieldObservationsObjects.PointNEH;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is class which contains methods responsible for reading files
 * and converting them to different Field Observation Objects
 *
 * @author Slawomir Szwed
 * @version 1.0
 * @since 2018-12-15
 */
public class FileUtils {
    /**
     * This method is responsible for reading txt file and converting it to PointLDH Object.
     *
     * @param file which contains data in format (String(name of the point), Double(L), Double(H)
     * @return collection of PointLDH objects
     * @throws IOException IO Exception
     */
    public List<PointLDH> readLdhFile(File file) throws IOException {
        String line;
        FileReader fr = new FileReader(file);
        List<PointLDH> setOfLHObservations = new ArrayList<>();

        try (BufferedReader f = new BufferedReader(fr)) {
            while ((line = f.readLine()) != null) {
                PointLDH pointLdh = new PointLDH();
                String[] splitLine = line.split(",");
                if (splitLine.length == 3) {
                    pointLdh.setName(String.valueOf(splitLine[0]));
                    pointLdh.setL(Double.valueOf(splitLine[1]));
                    pointLdh.setH(Double.valueOf(splitLine[2]));
                    setOfLHObservations.add(pointLdh);
                }
            }
        }
        return setOfLHObservations;
    }

    /**
     * This method is responsible for reading txt file  and converting it to PointNEH Object.
     *
     * @param file which contains data in format (String(name of the point), Double(H)
     * @return collection of PointNEH objects
     * @throws IOException IO Exception
     */
    public List<PointNEH> readNehFile(File file) throws IOException {
        String line;
        FileReader fr = new FileReader(file);
        List<PointNEH> setOfNehObservations = new ArrayList<>();

        try (BufferedReader f = new BufferedReader(fr)) {
            while ((line = f.readLine()) != null) {
                PointNEH pointNeh = new PointNEH();
                String[] splitLine = line.split(",");
                if (splitLine.length == 2) {
                    pointNeh.setName(String.valueOf(splitLine[0]));
                    pointNeh.setH(Double.valueOf(splitLine[1]));
                    setOfNehObservations.add(pointNeh);
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
    public List<DeltaHeight> readLevelingObservations(File file) throws IOException {
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

}
