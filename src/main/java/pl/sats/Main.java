package pl.sats;

import pl.sats.CurveCalculations.Catenary;
import pl.sats.CurveCalculations.Parabola;

import java.io.File;
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

        ClassLoader loader = Main.class.getClassLoader();
        File file = new File(loader.getResource("TxtFiles/Chain1.txt").getFile());

        Catenary catenary = new Catenary(file);
        catenary.calculateCatenary();



    }
}
