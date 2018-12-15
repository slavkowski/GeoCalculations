import org.junit.Assert;
import org.junit.Test;
import pl.sats.Matrix;

public class MatrixTests {
    private Matrix matrix = new Matrix();
    private double[][] testMatrixA = {
            {1.1d, 2.2d, 3.3d},
            {4.4d, 5.5d, 6.6d}
    };
    private double[][] testMatrixB = {
            {7.7d, 8.8d},
            {9.9d, 1.1d},
            {2.2d, 3.3d}

    };
    private double[][] expectedMatrixProduct = {
            {37.51d, 22.99d},
            {102.85d, 66.55d}
    };


    @Test
    public void shouldReturnMatrixProduct() {
        double[][] matrixProduct = matrix.getMatrixProduct(testMatrixA, testMatrixB);
        Assert.assertArrayEquals(expectedMatrixProduct[0], matrixProduct[0],0.0000000000001d);
        Assert.assertArrayEquals(expectedMatrixProduct[1], matrixProduct[1],0.0000000000001d);

    }
}
