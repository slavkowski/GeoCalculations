package pl.sats;

import pl.sats.BasicGeoCalculations.AngleConverter;
import pl.sats.FieldObservationsObjects.BLH;
import pl.sats.FieldObservationsObjects.XYZ;
import pl.sats.GeodeticProjectionCalculations.BLHToXYZ;
import pl.sats.GeodeticProjectionCalculations.EllipsoidDetails;

public class Main {
    public static void main(String[] args) {
        BLHToXYZ blhToXYZ = new BLHToXYZ(EllipsoidDetails.GRS80);
        AngleConverter angleConverter = new AngleConverter();
        BLH blh = new BLH(angleConverter.degToRad(90.0), angleConverter.degToRad(0.0), 0.0);
        XYZ xyz = blhToXYZ.transformCoordinates(blh);
        System.out.println(xyz);

    }
}
