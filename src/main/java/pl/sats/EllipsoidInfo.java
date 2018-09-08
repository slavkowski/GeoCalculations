package pl.sats;

public enum EllipsoidInfo {
    a(6378137.0,6378137.0),
    f(298.257223563, 298.257222101),
//    ***calculated***
    b(6356752.314245179, 1),
    e(0.08181919084262157, 1),
    n(0.0016792203863837205,1),
    R(6367449.145823414,1);






    private double WGS84;
    private double GRS80;

    EllipsoidInfo(double WGS84, double GRS80) {
        this.WGS84 = WGS84;
        this.GRS80 = GRS80;
    }

    public double getWGS84() {
        return WGS84;
    }

    public double getGRS80() {
        return GRS80;
    }
}
