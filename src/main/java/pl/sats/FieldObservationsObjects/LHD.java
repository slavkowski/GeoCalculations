package pl.sats.FieldObservationsObjects;

public class LHD {
    private double L;
    private double H;
    private double D;

    public LHD() {
    }

    public LHD(double l, double h, double d) {
        L = l;
        H = h;
        D = d;
    }

    public double getL() {
        return L;
    }

    public void setL(double l) {
        L = l;
    }

    public double getH() {
        return H;
    }

    public void setH(double h) {
        H = h;
    }

    public double getD() {
        return D;
    }

    public void setD(double d) {
        D = d;
    }
}
