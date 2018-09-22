package pl.sats;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        AngleConverter angleConverter = new AngleConverter();
//
//        double B = angleConverter.degToRad(54.0 + 50.0 / 60.0);
//        double L = angleConverter.degToRad(18.0 + 30.0 / 60.0);
//
//        GaussKrugerProjection gaussKrugerProjection = new GaussKrugerProjection();
//        gaussKrugerProjection.getUTM(B, L);
        FileUtils f = new FileUtils();
        try {
            f.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
