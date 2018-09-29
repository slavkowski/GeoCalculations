package pl.sats.CurveCalculations;

import java.io.File;

public class Catenary {
    private Parabola parabola;
    private File file;
    private double A;
    private double L;
    private double H;

    public Catenary(File file) {
        this.file = file;
    }

    public void calculateCatenary(){
        parabola = new Parabola(file);
        parabola.getParabolaParameters();
    }
}
