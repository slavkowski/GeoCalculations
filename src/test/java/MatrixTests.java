import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sgeonet.Exceptions.MatrixDegenerateException;
import pl.sgeonet.Exceptions.MatrixWrongSizeException;
import pl.sgeonet.MathExtraCalculations.Matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MatrixTests {
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
    private double[][] expectedMatrixTranspose = {
            {1.1d, 4.4d},
            {2.2d, 5.5d},
            {3.3d, 6.6d}
    };
    private double[][] testMatrixC = {
            {20.0d, 1.0d},
            {11.0d, 79.5d}
    };
    private double[][] testMatrixD = {
            {0.0d, 0.0d},
            {0.0d, 1.0d}
    };
    private double[][] expectedMatrixInverse = {
            {0.0503483217226092, -0.000633312222925902},
            {-0.00696643445218493, 0.012666244458518}
    };


    @Test
    void shouldReturnMatrixProduct() throws MatrixWrongSizeException {
        double[][] matrixProduct = matrix.getMatrixProduct(testMatrixA, testMatrixB);
        assertArrayEquals(expectedMatrixProduct[0], matrixProduct[0], 0.0000000000001d);
        assertArrayEquals(expectedMatrixProduct[1], matrixProduct[1], 0.0000000000001d);
    }
    @Test
    void shouldReturnException() {
        Assertions.assertThrows(MatrixWrongSizeException.class, () -> matrix.getMatrixProduct(testMatrixA, testMatrixA));

    }
    @Test
    void shouldReturnMatrixTranspose() {
        double[][] matrixTranspose = matrix.getMatrixTranspose(testMatrixA);
        assertArrayEquals(expectedMatrixTranspose[0], matrixTranspose[0], 0.00000000001);
    }
    @Test
    void shouldReturnInverseOfMatrix() throws MatrixDegenerateException, MatrixWrongSizeException {
        double[][] matrixInverse = matrix.getMatrixInverse(testMatrixC);
        assertArrayEquals(expectedMatrixInverse[0], matrixInverse[0], 0.0000000000001d);
        assertArrayEquals(expectedMatrixInverse[1], matrixInverse[1], 0.0000000000001d);
    }
    @Test
    void shouldReturnWrongSizeExceptionOnInverseClass(){
        Assertions.assertThrows(MatrixWrongSizeException.class, () -> {
            double[][] matrixInverse = matrix.getMatrixInverse(testMatrixA);
        });
    }
    @Test
    void shouldReturnDegenerateExceptionOnInverseClass(){
        Assertions.assertThrows(MatrixDegenerateException.class, () -> {
            double[][] matrixInverse = matrix.getMatrixInverse(testMatrixD);
        });
    }
}
