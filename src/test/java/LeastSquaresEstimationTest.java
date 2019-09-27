import org.junit.jupiter.api.Test;
import pl.sats.Exceptions.MatrixDegenerateException;
import pl.sats.Exceptions.MatrixWrongSizeException;
import pl.sats.LSEstimations.LeastSquaresEstimation;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LeastSquaresEstimationTest {
    private double[][] A = {
            {1.0d, 0.0d, 0.0d},
            {-1.0d, 1.0d, 0.0d},
            {0.0d, 1.0d, 0.0d},
            {0.0d, 1.0d, -1.0d},
            {1.0d, 0.0d, -1.0d}
    };
    private double[][] L = {
            {-102.498},
            {-4.768},
            {-107.288},
            {-2.961},
            {1.774}
    };
    private double w = 1 / (Math.pow(0.02, 2));
    private double[][] P = {
            {w, 0.0d, 0.0d, 0.0d, 0.0d},
            {0.0d, w, 0.0d, 0.0d, 0.0d},
            {0.0d, 0.0d, w, 0.0d, 0.0d},
            {0.0d, 0.0d, 0.0d, w, 0.0d},
            {0.0d, 0.0d, 0.0d, 0.0d, w}
    };
    private LeastSquaresEstimation leastSquaresEstimation = new LeastSquaresEstimation(A, P, L);

    @Test
    void shouldReturnUnknownParameters() {
        try {
            leastSquaresEstimation.executeLeastSquaresEstimation();
            assertEquals(102.510, leastSquaresEstimation.getX()[0][0], 0.001);
            assertEquals(107.276, leastSquaresEstimation.getX()[1][0], 0.001);
            assertEquals(104.300, leastSquaresEstimation.getX()[2][0], 0.001);
        } catch (MatrixDegenerateException | MatrixWrongSizeException e) {
            e.printStackTrace();
        }

    }

    @Test
    void shouldReturnM0() {
        try {
            leastSquaresEstimation.executeLeastSquaresEstimation();
            assertEquals(0.98, leastSquaresEstimation.getM0(), 0.01);
        } catch (MatrixDegenerateException | MatrixWrongSizeException e) {
            e.printStackTrace();
        }
    }
}
