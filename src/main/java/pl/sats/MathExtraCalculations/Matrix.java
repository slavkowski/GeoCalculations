package pl.sats.MathExtraCalculations;

import pl.sats.Exceptions.MatrixDegenerateException;
import pl.sats.Exceptions.MatrixWrongSizeException;

import java.lang.*;

/**
 * This class contains basic matrix calculations.
 *
 * <ul>
 * <li>Matrix Inverse</li>
 * <li>Matrix Transpose</li>
 * <li>Matrix Multiplication</li>
 * </ul>
 *
 * @author Slawomir Szwed
 * @version 1.0
 * @since 2018-12-15
 */
public class Matrix {

    public Matrix() {
    }

    /**
     * Algorithm by Donald E Knuth described in "The Art of Computer Programming"
     *
     * @param A n-by-n square matrix
     * @return n-by-n inverse of matrix A
     * @throws MatrixWrongSizeException if A is not square
     * @throws MatrixDegenerateException if A is invertible
     */
    public double[][] getMatrixInverse(double[][] A) throws MatrixWrongSizeException, MatrixDegenerateException {
        if (A[0].length != A.length) {
            throw new MatrixWrongSizeException();
        }

        int s = A.length;
        int q = 0;
        int p = 0;
        int[] C = new int[s];
        boolean[] O = new boolean[s];

        for (int i = 0; i < s; i++) {

            double max = 0;
            for (int j = 0; j < s; j++) {
                if (!O[j]) {
                    if (max < Math.abs(A[i][j])) {
                        max = A[i][j];
                        q = j;
                        p = i;
                    }
                }
            }
            if (max == 0) {
                throw new MatrixDegenerateException();
            }
            C[i] = q;
            O[q] = true;

            for (int w1 = 0; w1 < s; w1++) {
                for (int k1 = 0; k1 < s; k1++) {
                    if (w1 != p && k1 != q) {
                        A[w1][k1] = A[w1][k1] - A[p][k1] * A[w1][q] / A[p][q];
                    }
                }
            }
            for (int w1 = 0; w1 < s; w1++) {
                if (w1 != p) {
                    A[w1][q] = -A[w1][q] / A[p][q];
                }

            }
            for (int k1 = 0; k1 < s; k1++) {
                if (k1 != q) {
                    A[p][k1] = A[p][k1] / A[p][q];
                }
            }
            A[p][q] = 1 / A[p][q];
        }

        double[][] Apom = new double[s][s];

        for (int w1 = 0; w1 < s; w1++) {
            for (int k1 = 0; k1 < s; k1++) {
                Apom[w1][k1] = A[w1][k1];
            }
        }
        for (int w2 = 0; w2 < s; w2++) {
            for (int k2 = 0; k2 < s; k2++) {
                A[C[w2]][k2] = Apom[w2][k2];
            }
        }
        for (int w1 = 0; w1 < s; w1++) {
            for (int k1 = 0; k1 < s; k1++) {
                Apom[w1][k1] = A[w1][k1];
            }
        }
        for (int w2 = 0; w2 < s; w2++) {
            for (int k2 = 0; k2 < s; k2++) {
                A[k2][w2] = Apom[k2][C[w2]];
            }
        }
        return A;
    }

    /**
     * This method is responsible for matrix transpose.
     *
     * @param A double matrix A in [n * m] size
     * @return transpose of matrix A in [m * n] size
     */
    public double[][] getMatrixTranspose(double[][] A) {
        double[][] B = new double[A[0].length][A.length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                B[j][i] = A[i][j];
            }
        }
        return B;
    }

    /**
     * This method is responsible for multiplication two matrix.
     * All values in A and B matrix must be double
     *
     * @param A first matrix with [n * m] size, where in n - number of rows and m - number of columns
     * @param B second matrix with [m * p] size, where in m - number of rows and p - number of columns
     * @return double  - matrix with size [n * p]
     * @throws MatrixWrongSizeException if number of columns first matrix - A is not equal to number
     *                                  of rows second matrix - B
     */
    public double[][] getMatrixProduct(double[][] A, double[][] B) throws MatrixWrongSizeException {

        double[][] AB;

        if (A[0].length != B.length) {
            throw new MatrixWrongSizeException();
        }
        AB = new double[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                double suma = 0;
                for (int k = 0; k < A[0].length; k++) {
                    suma += A[i][k] * B[k][j];
                }
                AB[i][j] = suma;
            }
        }
        return AB;
    }
}