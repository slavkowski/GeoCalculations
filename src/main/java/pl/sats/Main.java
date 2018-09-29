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

        Catenary catenary = new Catenary(new File("C:/Test/Chain1.txt"));
        catenary.calculateCatenary();



    }
}
