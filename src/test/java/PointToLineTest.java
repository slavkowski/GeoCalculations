import org.junit.Assert;
import org.junit.Test;
import pl.sats.FieldObservationsObjects.XYH;
import pl.sats.LineCalculations.PointToLineProjection;

import java.util.ArrayList;
import java.util.List;

public class PointToLineTest {
    private PointToLineProjection pointToLineProjection = new PointToLineProjection();
    XYH p1 = new XYH(101.11, 99.23, 55.23);
    XYH p2 = new XYH(2101.25, 99.23, 55.23);
    XYH p3 = new XYH(201.99, 202.33, 58.666);



    @Test
    public void shouldReturnCorrectLHD(){
        List<XYH> l = new ArrayList<>();
        l.add(p1);
        l.add(p2);
        l.add(p3);
        double [] calculatedLHD = new double[3];
        double [] expectedLHD = new double[3];

        calculatedLHD[0] = pointToLineProjection.getLHD(l).get(1).getL();
        calculatedLHD[1] = pointToLineProjection.getLHD(l).get(1).getH();
        calculatedLHD[2] = pointToLineProjection.getLHD(l).get(1).getD();

        expectedLHD[0] = 1398.837;
        expectedLHD[1] = 55.23;
        expectedLHD[2] = -1429.620;


        Assert.assertArrayEquals(expectedLHD, calculatedLHD, 0.001);

    }
}
