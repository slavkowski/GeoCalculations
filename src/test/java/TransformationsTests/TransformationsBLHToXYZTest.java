package TransformationsTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sats.BasicGeoCalculations.AngleConverter;
import pl.sats.FieldObservationsObjects.PointCoordinates.BLH;
import pl.sats.FieldObservationsObjects.PointCoordinates.XYZ;
import pl.sats.GeodeticProjectionCalculations.BLHToXYZ;
import pl.sats.GeodeticProjectionCalculations.ReferenceEllipsoid;


class TransformationsBLHToXYZTest {
    private BLHToXYZ blhToXYZ = new BLHToXYZ(ReferenceEllipsoid.GRS80);
    private AngleConverter angleConverter = new AngleConverter();
    private BLH blh = new BLH(angleConverter.degToRad(51.1365638888889), angleConverter.degToRad(16.976225), 153.080);

    @Test
    void transformationTest() {
        XYZ xyz = blhToXYZ.transformCoordinates(blh);
        Assertions.assertEquals(3835563.202, xyz.getX(), 0.001);
        Assertions.assertEquals(1170909.231, xyz.getY(), 0.001);
        Assertions.assertEquals(4943210.796, xyz.getZ(), 0.001);
    }

}
