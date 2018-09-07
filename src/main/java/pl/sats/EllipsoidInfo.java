package pl.sats;

public enum EllipsoidInfo {
    a(6378137.0,6378137.0),
    f(298.257223563, 298.257222101),
    e(0.0818191908426215, 0.0818191910428158);



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
