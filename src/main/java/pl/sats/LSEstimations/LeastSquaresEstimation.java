package pl.sats.LSEstimations;

import pl.sats.Exceptions.MatrixDegenerateException;
import pl.sats.Exceptions.MatrixWrongSizeException;
import pl.sats.Matrix;

/**
 * Having following formula: AX = L + V
 * we can estimate x parameters by calculating following equation x = (A<sup>t</sup>PA)<sup>-1</sup>A<sup>t</sup>PL
 * <p>
 * where:
 * <ul>
 * <li>A [n * m] coefficient matrix where n is the number of observations and m is the number of parameters</li>
 * <li>P [n * n] weight matrix of observations</li>
 * <li>L [n * 1] matrix of measured values</li>
 * <li>V [n * 1] matrix of random residuals errors</li>
 * <li>X [m * 1] matrix of unknown parameters</li>
 * </ul>
 *
 * @author Slawomir Szwed
 * @version 1.0
 * @since 2018-12-15
 */
public class LeastSquaresEstimation {
    private double[][] A;
    private double[][] P;
    private double[][] L;

    public LeastSquaresEstimation(double[][] a, double[][] p, double[][] l) {
        A = a;
        P = p;
        L = l;
    }

    public LeastSquaresEstimation(double[][] a, double[][] l) {
        A = a;
        L = l;
        int sizeOfWeightMatrix = a.length;
        P = new double[sizeOfWeightMatrix][sizeOfWeightMatrix];
        for (int i = 0; i < sizeOfWeightMatrix; i++) {
            for (int j = 0; j < sizeOfWeightMatrix; j++) {
                P[i][j] = (i == j) ? 1.0d : 0.0d;
            }
        }
    }

    public LeastSquaresEstimation() {
    }

    private Matrix matrix = new Matrix();

    /**
     * This class is responsible for calculating unknown parameters
     *
     * @return n-by-1 matrix with unknown parameters
     */
    public double[][] getX() {

        double[][] X = new double[0][];
        try {
            X = matrix.getMatrixProduct(matrix.getMatrixProduct(matrix.getMatrixProduct(matrix.getMatrixInverse(matrix.getMatrixProduct(matrix.getMatrixProduct(matrix.getMatrixTranspose(A), P), A)), matrix.getMatrixTranspose(A)), P), L);
        } catch (MatrixWrongSizeException | MatrixDegenerateException e) {
            e.printStackTrace();
        }
        return X;
    }

    public double getM0(double[][] dx) {
        double[][] v = new double[0][];
        try {
            v = matrix.getMatrixProduct(A, dx);
        } catch (MatrixWrongSizeException e) {
            e.printStackTrace();
        }

        double sigma = 0.0d;
        for (int i = 0; i < v.length; i++) {
            v[i][0] -= L[i][0];
            sigma += Math.pow(v[i][0], 2);
        }
        return Math.sqrt(sigma / (A.length - dx.length));
    }

    public void setA(double[][] a) {
        A = a;
    }

    public void setP(double[][] p) {
        P = p;
    }

    public void setL(double[][] l) {
        L = l;
    }
}
