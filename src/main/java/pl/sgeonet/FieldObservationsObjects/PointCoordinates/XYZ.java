package pl.sgeonet.FieldObservationsObjects.PointCoordinates;

/**
 * This class contains XYZ coordinates which uniquely determines the position of the point in
 * ECEF Cartesian coordinate system.
 * <ul>
 * <li>X - intersects the sphere of the earth at 0° latitude (the equator) and 0° longitude (prime meridian in Greenwich)</li>
 * <li>mX - X mean error in m</li>
 * <li>Y - Y coordinate in m</li>
 * <li>mY - Y coordinate's mean error in m</li>
 * <li>Z - The z-axis extends through true north, which does not coincide with the instantaneous Earth rotational axis.</li>
 * <li>mZ - Z coordinate's mean error in m</li>
 *
 * @author Slawomir Szwed
 * @version 1.3.3
 * @since 2019-10-03
 */
public class XYZ extends Point {
    private double X;
    private double mX;
    private double Y;
    private double mY;
    private double Z;
    private double mZ;

    public XYZ() {
    }

    public XYZ(String id, double x, double y, double z) {
        super(id);
        X = x;
        Y = y;
        Z = z;
    }

    public XYZ(String id,double x, double mX, double y, double mY, double z, double mZ) {
        super(id);
        X = x;
        this.mX = mX;
        Y = y;
        this.mY = mY;
        Z = z;
        this.mZ = mZ;
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

    public double getmX() {
        return mX;
    }

    public double getmY() {
        return mY;
    }

    public double getmZ() {
        return mZ;
    }
}
