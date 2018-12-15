package pl.sats;

import java.lang.*;

/**
 * This class contains basic matrix calculations.
 *
 * @author Slawomir Szwed
 * @version 1.0
 * @since 2018-12-15
 */
public class Matrix {

    public Matrix() {
    }

    //*********************************************************************************************************************
//odwracanie macierezy - metoda z osiwaniem i permutacjami wierszy i kolumn w macierzy
    public double[][] MatrixI(double[][] A) {

        int s = A.length;
        int q = 0;
        int p = 0;
        int[] C = new int[s];
        boolean[] O = new boolean[s];

        //osiowanie macierzy
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
                System.out.println("Wiersz " + i + " jest zerowy");
                System.out.println("Macierz jest osobliwa");
                break;
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

        //permutacje wierszy i kolumn wykorzystując macierz pomocniczą
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
     * This method is responsible for matrix transpose
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
     * This method is responsible for multiplication two matrix
     * All values in A and B matrix must be double
     *
     * @param A first matrix with [n * m] size, where in n - number of rows and m - number of columns
     * @param B second matrix with [m * p] size, where in m - number of rows and p - number of columns
     * @return double  - matrix with size [n * p]
     * @throws
     */
    public double[][] getMatrixProduct(double[][] A, double[][] B) {

        double[][] AB = new double[0][];

        if (A[0].length == B.length) {
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
        } else {
            System.out.println("Bad size");
        }
        return AB;
    }
}



