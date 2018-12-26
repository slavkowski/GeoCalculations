import org.junit.Assert;
import org.junit.Test;
import pl.sats.Exceptions.MatrixDegenerateException;
import pl.sats.Exceptions.MatrixWrongSizeException;
import pl.sats.LSEstimations.LeastSquaresEstimation;

public class LeastSquaresEstimationTest {
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
    public void shouldReturnUnknownParameters() {
        try {
            leastSquaresEstimation.executeLeastSquaresEstimation();
            Assert.assertEquals(102.510, leastSquaresEstimation.getX()[0][0], 0.001);
            Assert.assertEquals(107.276, leastSquaresEstimation.getX()[1][0], 0.001);
            Assert.assertEquals(104.300, leastSquaresEstimation.getX()[2][0], 0.001);
        } catch (MatrixDegenerateException e) {
            e.printStackTrace();
        } catch (MatrixWrongSizeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void shouldReturnM0() {
        try {
            leastSquaresEstimation.executeLeastSquaresEstimation();
            Assert.assertEquals(0.98, leastSquaresEstimation.getM0(), 0.01);
        } catch (MatrixDegenerateException e) {
            e.printStackTrace();
        } catch (MatrixWrongSizeException e) {
            e.printStackTrace();
        }
    }
}
