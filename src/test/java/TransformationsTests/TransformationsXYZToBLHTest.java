package TransformationsTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sats.BasicGeoCalculations.AngleConverter;
import pl.sats.FieldObservationsObjects.BLH;
import pl.sats.FieldObservationsObjects.XYZ;
import pl.sats.GeodeticProjectionCalculations.EllipsoidDetails;
import pl.sats.GeodeticProjectionCalculations.XYZToBLH;

class TransformationsXYZToBLHTest {
    private XYZToBLH xyzToBLH = new XYZToBLH(EllipsoidDetails.GRS80);
    private AngleConverter angleConverter = new AngleConverter();
    private XYZ xyz = new XYZ(3835563.202, 1170909.231, 4943210.796);

    @Test
    void transformationTest() {
        BLH blh = xyzToBLH.transformCoordinates(xyz);
        Assertions.assertEquals(angleConverter.degToRad(16.976225), blh.getLongitude(), 0.00000000001);
        Assertions.assertEquals(angleConverter.degToRad(51.1365638888889), blh.getLatitude(), 0.00000000001);
        Assertions.assertEquals(153.080, blh.getEllipsoidHeight(), 0.001);
    }
}
