package pl.sats.CurveCalculations;

import java.io.File;

public class Catenary {
    private Parabola parabola;
    private File file;
    private double a;
    private double l;
    private double h;

    public Catenary(File file) {
        this.file = file;
    }

    public void calculateCatenary(){
        parabola = new Parabola(file);
        parabola.getParabolaParameters();

        double a0 = parabola.getaCatenary();
        double l0 = parabola.getlCatenary();
        double h0 = parabola.gethCatenary();

        double AParabola[][] = parabola.getA();
        double LParabola[][] = parabola.getL();
        double A[][] = new double [AParabola.length][3];
        double L[][] = new double [LParabola.length][1];

        int i = 0;
        while (i<5){

            for (int j = 0; j < AParabola.length; j++) {
                A[j][0] = 1.0d;
                A[j][1] = -1.0d;
                A[j][2] = 1.0d;

                L[j][0] = -LParabola[j][0];

            }




            i++;
        }







    }
}
