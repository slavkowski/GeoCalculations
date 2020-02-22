package TransformationsTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sgeonet.BasicGeoCalculations.AngleConverter;
import pl.sgeonet.FieldObservationsObjects.PointCoordinates.BLH;
import pl.sgeonet.FieldObservationsObjects.PointCoordinates.XYZ;
import pl.sgeonet.GeodeticProjectionCalculations.ReferenceEllipsoid;
import pl.sgeonet.GeodeticProjectionCalculations.XYZToBLH;

class TransformationsXYZToBLHTest {
    private XYZToBLH xyzToBLH = new XYZToBLH(ReferenceEllipsoid.GRS80);
    private AngleConverter angleConverter = new AngleConverter();
    private XYZ xyz = new XYZ("1", 3835563.202, 1170909.231, 4943210.796);

    @Test
    void transformationTest() {
        BLH blh = xyzToBLH.transformCoordinates(xyz);
        Assertions.assertEquals(angleConverter.degToRad(16.976225), blh.getLongitude(), 0.00000000001);
        Assertions.assertEquals(angleConverter.degToRad(51.1365638888889), blh.getLatitude(), 0.00000000001);
        Assertions.assertEquals(153.080, blh.getEllipsoidHeight(), 0.001);
    }
}
