package pl.sats;

import pl.sats.FieldObservationsObjects.LHD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public List<LHD> readLhdFile(File file) throws IOException {
        String line;
        FileReader fr = new FileReader(file);
        BufferedReader f = new BufferedReader(fr);
        List<LHD> setOfLHObservations = new ArrayList<>();

        while ((line = f.readLine()) != null) {
            LHD chain_lh = new LHD();
            String[] splitLine = line.split(",");
            if (splitLine.length == 2) {
                chain_lh.setL(Double.valueOf(splitLine[0]));
                chain_lh.setH(Double.valueOf(splitLine[1]));
                setOfLHObservations.add(chain_lh);
            }
        }
        return setOfLHObservations;
    }
}
