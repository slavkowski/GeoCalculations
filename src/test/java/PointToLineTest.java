import org.junit.jupiter.api.Test;
import pl.sgeonet.Exceptions.MatrixDegenerateException;
import pl.sgeonet.Exceptions.MatrixWrongSizeException;
import pl.sgeonet.FieldObservationsObjects.PointCoordinates.NEH;
import pl.sgeonet.LineCalculations.PointToLineProjection;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class PointToLineTest {
    private PointToLineProjection pointToLineProjection = new PointToLineProjection();
    private NEH p1 = new NEH("1",101.11d, 99.23d, 55.23d);
    private NEH p2 = new NEH("2",2101.25d, 99.23d, 55.23d);
    private NEH p3 = new NEH("3",201.99d, 202.33d, 58.666d);

    @Test
    void shouldReturnCorrectLHD() throws MatrixDegenerateException, MatrixWrongSizeException {
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

        assertArrayEquals(expectedLHD, calculatedLHD, 0.001);
    }
}
