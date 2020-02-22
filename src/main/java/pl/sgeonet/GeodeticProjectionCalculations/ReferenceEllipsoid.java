package pl.sgeonet.GeodeticProjectionCalculations;

/**
 * List of various reference ellipsoids with major parameters
 */
public enum ReferenceEllipsoid {
    WGS84(6378137.0, 298.257223563),
    GRS80(6378137.0, 298.257222101),
    KRASSOVSKY(6378245.0, 298.3);

    private final double a;
    private final double f;

    ReferenceEllipsoid(double a, double f) {
        this.a = a;
        this.f = f;
    }

    public double getA() {
        return a;
    }

    public double getF() {
        return f;
    }
}
