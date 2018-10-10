package pl.sats;

import pl.sats.CurveCalculations.Catenary;
import pl.sats.CurveCalculations.Parabola;
import pl.sats.FieldObservationsObjects.XYH;
import pl.sats.LineCalculations.PointToLineProjection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        AngleConverter angleConverter = new AngleConverter();
//
//        double B = angleConverter.degToRad(54.0 + 50.0 / 60.0);
//        double L = angleConverter.degToRad(18.0 + 30.0 / 60.0);
//
//        GaussKrugerProjection gaussKrugerProjection = new GaussKrugerProjection();
//        gaussKrugerProjection.getUTM(B, L);

//        ClassLoader loader = Main.class.getClassLoader();
//        File file = new File(loader.getResource("TxtFiles/Chain2.txt").getFile());
//
//        Catenary catenary = new Catenary(file);
//        catenary.calculateCatenary();

        PointToLineProjection pointToLineProjection = new PointToLineProjection();
//        XYH p1 = new XYH(101.11, 99.23, 55.23);
//        XYH p2 = new XYH(101.25, 125.33, 50.30);
//        XYH p3 = new XYH(201.99, 202.33, 58.666);

//        XYH p1 = new XYH(101.11, 99.23, 55.23);
//        XYH p2 = new XYH(101.11, 99.23, 55.23);
//        XYH p3 = new XYH(201.99, 202.33, 58.666);

        XYH p1 = new XYH(101.11, 99.23, 55.23);
        XYH p2 = new XYH(1101.25, 99.23, 55.23);
        XYH p3 = new XYH(201.99, 202.33, 58.666);

        List<XYH> l = new ArrayList<>();
        l.add(p1);
        l.add(p2);
        l.add(p3);

        pointToLineProjection.getLHD(l);


    }
}
