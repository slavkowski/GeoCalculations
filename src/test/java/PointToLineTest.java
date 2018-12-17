import org.junit.Assert;
import org.junit.Test;
import pl.sats.FieldObservationsObjects.NEH;
import pl.sats.LineCalculations.PointToLineProjection;

import java.util.ArrayList;
import java.util.List;

public class PointToLineTest {
    private PointToLineProjection pointToLineProjection = new PointToLineProjection();
    private NEH p1 = new NEH(101.11, 99.23, 55.23);
    private NEH p2 = new NEH(2101.25, 99.23, 55.23);
    private NEH p3 = new NEH(201.99, 202.33, 58.666);

    @Test
    public void shouldReturnCorrectLHD(){
        List<NEH> l = new ArrayList<>();
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
