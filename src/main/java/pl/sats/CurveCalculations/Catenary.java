package pl.sats.CurveCalculations;

import pl.sats.Matrix;
import pl.sats.RMSEstimations.DX;

import java.io.File;
import java.sql.SQLOutput;

public class Catenary {
    private Parabola parabola;
    private File file;
    private double a;
    private double l;
    private double h;

    public Catenary(File file) {
        this.file = file;
    }

    public void calculateCatenary() {
        parabola = new Parabola(file);
        parabola.getParabolaParameters();

        double a0 = parabola.getaCatenary();
        double l0 = parabola.getlCatenary();
        double h0 = parabola.gethCatenary();

        double AParabola[][] = parabola.getA();
        double LParabola[][] = parabola.getL();
        double PParabola[][] = parabola.getP();
        int numberOfObservations = parabola.getNumberOfObservations();
        double A[][] = new double[numberOfObservations][3];
        double L[][] = new double[numberOfObservations][1];
        double X[][];
        int k = 0;

        while (k < 5) {

            for (int i = 0; i < numberOfObservations; i++) {
                double alfa = (AParabola[i][1] + l0) / a0;
                A[i][0] = Math.sinh(alfa);
                A[i][1] = -1.0d;
                A[i][2] = Math.cosh(alfa) - alfa * Math.sinh(alfa);
                L[i][0] = a0 * Math.cosh(alfa) - h0 - LParabola[i][0];
            }
            DX dx = new DX(A, PParabola, L);
            X = dx.getDX();
            a0 = a0 - X[2][0];
            l0 = l0 - X[0][0];
            h0 = h0 - X[1][0];
            k++;
        }
        System.out.println(a0);
        System.out.println(l0);
        System.out.println(h0);

    }
}
