package pl.sats.FieldObservationsObjects.PointCoordinates;

/**
 * This class represents geodetic coordinates
 * <ul>
 * <li>B - Geodetic Latitude - φ</li>
 * <li>L - Geodetic Longitude - λ</li>
 * <li>H - Ellipsoid Height - H</li>
 * </ul>
 */
public class BLH extends Point {
    private double latitude;
    private double mLatitude;
    private double longitude;
    private double mLongitude;
    private double ellipsoidHeight;
    private double mEllipsoidHeight;

    public BLH(double latitude, double longitude, double ellipsoidHeight) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.ellipsoidHeight = ellipsoidHeight;
    }

    public BLH(String id, double latitude, double longitude, double ellipsoidHeight) {
        super(id);
        this.latitude = latitude;
        this.longitude = longitude;
        this.ellipsoidHeight = ellipsoidHeight;
    }

    public BLH(double longitude, double mLongitude, double latitude, double mLatitude, double ellipsoidHeight, double mEllipsoidHeight) {
        this.longitude = longitude;
        this.mLongitude = mLongitude;
        this.latitude = latitude;
        this.mLatitude = mLatitude;
        this.ellipsoidHeight = ellipsoidHeight;
        this.mEllipsoidHeight = mEllipsoidHeight;
    }

    public BLH(String id, double latitude, double mLatitude, double longitude, double mLongitude, double ellipsoidHeight, double mEllipsoidHeight) {
        super(id);
        this.latitude = latitude;
        this.mLatitude = mLatitude;
        this.longitude = longitude;
        this.mLongitude = mLongitude;
        this.ellipsoidHeight = ellipsoidHeight;
        this.mEllipsoidHeight = mEllipsoidHeight;
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

    public double getmLongitude() {
        return mLongitude;
    }

    public double getmLatitude() {
        return mLatitude;
    }

    public double getmEllipsoidHeight() {
        return mEllipsoidHeight;
    }
}

