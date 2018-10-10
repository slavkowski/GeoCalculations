package pl.sats.RMSEstimations;

import pl.sats.Matrix;

public class DX {
    private double A[][];
    private double P[][];
    private double L[][];
    private double V[][];

    public DX(double[][] a, double[][] p, double[][] l) {
        A = a;
        P = p;
        L = l;
    }

    public DX() {
    }

    private Matrix matrix = new Matrix();

    public double[][] getDX() {

        return matrix.MatrixM(matrix.MatrixM(matrix.MatrixM(matrix.MatrixI(matrix.MatrixM(matrix.MatrixM(matrix.MatrixT(A), P), A)), matrix.MatrixT(A)), P), L);
    }

    public double getM0(double[][] dx) {
        V = matrix.MatrixM(A, dx);

        double sigma = 0.0d;
        for (int i = 0; i < V.length; i++) {
            V[i][0] -= L[i][0];
            sigma += Math.pow(V[i][0],2);
        }
        return Math.sqrt(sigma/(A.length-dx.length));
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
