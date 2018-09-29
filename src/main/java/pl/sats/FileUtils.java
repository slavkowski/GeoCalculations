package pl.sats;

import pl.sats.FieldObservationsObjects.Chain_LH;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public List readFile() throws IOException {

        File file = new File("C:/Test/Chain1.txt");
        String line;
        FileReader fr = new FileReader(file);
        BufferedReader f = new BufferedReader(fr);
        List<Chain_LH> setOfLHObservations = new ArrayList<>();

        while ((line = f.readLine()) != null) {
            Chain_LH chain_lh = new Chain_LH();
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
