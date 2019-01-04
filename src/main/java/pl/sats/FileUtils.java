package pl.sats;

import pl.sats.FieldObservationsObjects.DeltaHeight;
import pl.sats.FieldObservationsObjects.PointLDH;
import pl.sats.FieldObservationsObjects.PointNEH;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileUtils<T> {
    private T setOfObservations;
    private File file;

    public FileUtils(File file) {
        this.file = file;
    }

    public void readFile() throws IOException {
            String line;
            FileReader fr = new FileReader(file);
            BufferedReader f = new BufferedReader(fr);
            List<PointLDH> setOfLHObservations = new ArrayList<>();

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
            setOfObservations = (T) setOfLHObservations;
        }

        public T getSetOfObservations () {
            return setOfObservations;
        }
    }
