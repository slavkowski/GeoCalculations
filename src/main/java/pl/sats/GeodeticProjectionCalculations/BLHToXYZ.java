package pl.sats.GeodeticProjectionCalculations;

import pl.sats.FieldObservationsObjects.PointCoordinates.BLH;
import pl.sats.FieldObservationsObjects.PointCoordinates.XYZ;

/**
 * This class provides method for converting between Geodetic Latitude-Longitude-EllipsoidHeight into cartesian coordinates XYZ.
 */
public class BLHToXYZ {
    private EllipsoidCalculatedParameters ellipsoidCalculatedParameters;

    /**
     * @param referenceEllipsoid - Ellipsoid
     */
    public BLHToXYZ(ReferenceEllipsoid referenceEllipsoid) {
        double a = referenceEllipsoid.getA();
        double f = referenceEllipsoid.getF();
        ellipsoidCalculatedParameters = new EllipsoidCalculatedParameters(a, f);
    }

    /**
     * This method is responsible for transforming BLH into XYZ
     * @param blh - geodetic coordinates
     * @return cartesian coordinates
     */
    public XYZ transformCoordinates(BLH blh) {

        ellipsoidCalculatedParameters.calculateParameters(blh.getLatitude());

        double N = ellipsoidCalculatedParameters.getPrimeVerticalRadiusOfCurvature();
        double e = ellipsoidCalculatedParameters.getFirstEccentricity();

        double x = (N + blh.getEllipsoidHeight()) * Math.cos(blh.getLatitude()) * Math.cos(blh.getLongitude());
        double y = (N + blh.getEllipsoidHeight()) * Math.cos(blh.getLatitude()) * Math.sin(blh.getLongitude());
        double z = (N + blh.getEllipsoidHeight()) * Math.sin(blh.getLatitude()) - (Math.pow(e, 2) * N * Math.sin(blh.getLatitude()));

        return new XYZ(x, y, z);
    }
}
