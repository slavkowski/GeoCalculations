package pl.sats.FieldObservationsObjects;

public class XYH {
    private double X;
    private double Y;
    private double H;

    public XYH() {
    }

    public XYH(double x, double y, double h) {
        X = x;
        Y = y;
        H = h;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getH() {
        return H;
    }

    public void setH(double h) {
        H = h;
    }
}
