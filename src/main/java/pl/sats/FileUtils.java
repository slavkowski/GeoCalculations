package pl.sats;

import pl.sats.FieldObservationsObjects.LDH;

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
     * This method is responsible for reading txt file and converting it to LDH Object.
     * @param file which contains data in format (String(name of the point), Double(L), Double(H)
     * @return collection of LDH objects
     * @throws IOException IO Exception
     */
    public List<LDH> readLdhFile(File file) throws IOException {
        String line;
        FileReader fr = new FileReader(file);
        BufferedReader f = new BufferedReader(fr);
        List<LDH> setOfLHObservations = new ArrayList<>();

        while ((line = f.readLine()) != null) {
            LDH pointLdh = new LDH();
            String[] splitLine = line.split(",");
            if (splitLine.length == 3) {
                pointLdh.setName(String.valueOf(splitLine[0]));
                pointLdh.setL(Double.valueOf(splitLine[1]));
                pointLdh.setH(Double.valueOf(splitLine[2]));
                setOfLHObservations.add(pointLdh);
            }
        }
        return setOfLHObservations;
    }
}
