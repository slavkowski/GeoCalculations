import org.junit.jupiter.api.Test;
import pl.sgeonet.Exceptions.MatrixDegenerateException;
import pl.sgeonet.Exceptions.MatrixWrongSizeException;
import pl.sgeonet.FieldObservationsObjects.FieldObservation.DeltaHeight;
import pl.sgeonet.LSEstimations.LeastSquaresEstimation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDifferenceMethod {
    private double[][] A = {
            {1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
            {-1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d},
            {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, -1.0d, 0.0d},
            {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, -1.0d, 0.0d, 0.0d},
            {0.0d, 0.0d, 0.0d, 0.0d, 1.0d, -1.0d, 0.0d, 0.0d, 0.0d},
            {0.0d, 0.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 1.0d},
            {0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, -1.0d},
            {0.0d, 0.0d, 1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
            {0.0d, 1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
    };
    private double[][] L = {
            {-0.60},
            {2.15},
            {-1.11},
            {-0.80},
            {-7.08},
            {4.21},
            {-6.86},
            {9.14},
            {0.81}
    };
    private LeastSquaresEstimation leastSquaresEstimation = new LeastSquaresEstimation(A, L);
    @Test
    void shouldReturnUnknownParameters() {
        List<DeltaHeight> deltaHeights = new ArrayList<>();
        DeltaHeight deltaHeight = new DeltaHeight();
        deltaHeight.setPointFrom("A");
        deltaHeight.setPointTo("B");
        deltaHeight.setHeightDifferenceValue(1.11);
        deltaHeight.setHeightDifferenceStdMeanError(7.22);

        deltaHeights.add(deltaHeight);
        deltaHeights.add(deltaHeight);
        deltaHeights.add(deltaHeight);
        deltaHeights.add(deltaHeight);
        deltaHeights.add(deltaHeight);
        deltaHeights.add(deltaHeight);
        deltaHeights.add(deltaHeight);
        deltaHeights.add(deltaHeight);
        deltaHeights.add(deltaHeight);


        leastSquaresEstimation.setListOfDeltaHeightFieldObservations(deltaHeights);
        try {
            leastSquaresEstimation.executeLeastSquaresEstimation();
            assertEquals(10.810, leastSquaresEstimation.getX()[0][0], 0.001);
            assertEquals(107.276, leastSquaresEstimation.getX()[1][0], 0.001);
            assertEquals(104.300, leastSquaresEstimation.getX()[2][0], 0.001);
        } catch (MatrixDegenerateException | MatrixWrongSizeException e) {
            e.printStackTrace();
        }

    }
}
