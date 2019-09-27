import org.junit.jupiter.api.Test;
import pl.sats.Exceptions.MatrixDegenerateException;
import pl.sats.Exceptions.MatrixWrongSizeException;
import pl.sats.FieldObservationsObjects.PointNEH;
import pl.sats.LineCalculations.PointToLineProjection;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class PointToLineTest {
    private PointToLineProjection pointToLineProjection = new PointToLineProjection();
    private PointNEH p1 = new PointNEH("1",101.11d, 99.23d, 55.23d);
    private PointNEH p2 = new PointNEH("2",2101.25d, 99.23d, 55.23d);
    private PointNEH p3 = new PointNEH("3",201.99d, 202.33d, 58.666d);

    @Test
    void shouldReturnCorrectLHD() throws MatrixDegenerateException, MatrixWrongSizeException {
        List<PointNEH> l = new ArrayList<>();
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

        assertArrayEquals(expectedLHD, calculatedLHD, 0.001);
    }
}
