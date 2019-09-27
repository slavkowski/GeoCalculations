package pl.sats.GeodeticProjectionCalculations;

public enum  EllipsoidDetails {
    WGS84(6378137.0, 298.257223563),
    GRS80(6378137.0, 298.257222101);

    private final double a;
    private final double f;

    EllipsoidDetails(double a, double f) {
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
