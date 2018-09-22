package pl.sats;

import java.io.*;

public class FileUtils {

    private File file;

    public void readFile() throws IOException {

        file = new File("D:/Test.txt");
        String line;
        FileReader fr = new FileReader(file);
        BufferedReader f = new BufferedReader(fr);
        while ((line = f.readLine()) != null) {
            System.out.println(line);

        }

    }
}
