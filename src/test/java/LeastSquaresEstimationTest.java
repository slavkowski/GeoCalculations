import org.junit.Assert;
import org.junit.Test;
import pl.sats.Exceptions.MatrixDegenerateException;
import pl.sats.Exceptions.MatrixWrongSizeException;
import pl.sats.LSEstimations.LeastSquaresEstimation;

public class LeastSquaresEstimationTest {
    double A[][] = {
            {1.0d, 0.0d, 0.0d},
            {-1.0d, 1.0d, 0.0d},
            {0.0d, 1.0d, 0.0d},
            {0.0d, 1.0d, -1.0d},
            {1.0d, 0.0d, -1.0d}
    };
    double L[][] = {
            {-102.498},
            {-4.768},
            {-107.288},
            {-2.961},
            {1.774}
    };
    private double w = 1 / (Math.pow(0.02, 2));
    double P[][] = {
            {w, 0.0d, 0.0d, 0.0d, 0.0d},
            {0.0d, w, 0.0d, 0.0d, 0.0d},
            {0.0d, 0.0d, w, 0.0d, 0.0d},
            {0.0d, 0.0d, 0.0d, w, 0.0d},
            {0.0d, 0.0d, 0.0d, 0.0d, w}
    };
    private LeastSquaresEstimation leastSquaresEstimation = new LeastSquaresEstimation(A, P, L);

    @Test
    public void shouldReturnUnknownParameters() {

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
