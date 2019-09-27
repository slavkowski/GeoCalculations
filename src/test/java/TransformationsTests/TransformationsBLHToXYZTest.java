package TransformationsTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sats.BasicGeoCalculations.AngleConverter;
import pl.sats.FieldObservationsObjects.BLH;
import pl.sats.GeodeticProjectionCalculations.BLHToXYZ;
import pl.sats.GeodeticProjectionCalculations.EllipsoidDetails;


class TransformationsBLHToXYZTest {
    private BLHToXYZ blhToXYZ = new BLHToXYZ(EllipsoidDetails.GRS80);
    private AngleConverter angleConverter = new AngleConverter();

    private BLH blh = new BLH(angleConverter.degToRad(16.976225), angleConverter.degToRad(51.1365638888889), 153.080);


    @Test
    void transformationTest() {
        blhToXYZ.transformCoordinates(blh);
        Assertions.assertEquals(3835563.202, blhToXYZ.transformCoordinates(blh).getX(),0.001);
        Assertions.assertEquals(1170909.231, blhToXYZ.transformCoordinates(blh).getY(),0.001);
        Assertions.assertEquals(4943210.796, blhToXYZ.transformCoordinates(blh).getZ(),0.001);

    }

}
