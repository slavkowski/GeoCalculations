package pl.sats.FieldObservationsObjects;

/**
 * This class represents geodetic coordinates
 * <ul>
 * <li>B - Geodetic Latitude - φ</li>
 * <li>L - Geodetic Longitude - λ</li>
 * <li>H - Ellipsoid Height - H</li>
 * </ul>
 */
public class BLH {
    private double longitude;
    private double latitude;
    private double ellipsoidHeight;

    public BLH(double longitude, double latitude, double ellipsoidHeight) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.ellipsoidHeight = ellipsoidHeight;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getEllipsoidHeight() {
        return ellipsoidHeight;
    }
}
