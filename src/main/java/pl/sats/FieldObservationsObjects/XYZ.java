package pl.sats.FieldObservationsObjects;

/**
 * This class represents Cartesian Coordinates (XYZ)
 */
public class XYZ {
    private double X;
    private double Y;
    private double Z;

    public XYZ(double x, double y, double z) {
        X = x;
        Y = y;
        Z = z;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public double getZ() {
        return Z;
    }
}
