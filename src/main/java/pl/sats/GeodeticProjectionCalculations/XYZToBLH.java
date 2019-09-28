package pl.sats.GeodeticProjectionCalculations;

import pl.sats.FieldObservationsObjects.BLH;
import pl.sats.FieldObservationsObjects.XYZ;

/**
 * This class provides method for converting between cartesian coordinates XYZ into Geodetic Latitude-Longitude-EllipsoidHeight.
 */
public class XYZToBLH {
    private EllipsoidCalculatedParameters ellipsoidCalculatedParameters;

    /**
     * @param ellipsoidDetails - Ellipsoid
     */
    public XYZToBLH(EllipsoidDetails ellipsoidDetails) {
        double a = ellipsoidDetails.getA();
        double f = ellipsoidDetails.getF();
        ellipsoidCalculatedParameters = new EllipsoidCalculatedParameters(a, f);
    }

    /**
     * This method is responsible for transforming XYZ into BLH
     *
     * @param xyz - cartesian coordinates
     * @return - geodetic coordinates - blh
     */
    public BLH transformCoordinates(XYZ xyz) {

        double e = ellipsoidCalculatedParameters.getFirstEccentricity();
        double eSecond = ellipsoidCalculatedParameters.getSecondEccentricity();
        double a = ellipsoidCalculatedParameters.getA();
        double b_ellipsoid = ellipsoidCalculatedParameters.getB();
        double rp = Math.sqrt(Math.pow(xyz.getX(), 2) + Math.pow(xyz.getY(), 2));

        //***** Calculating longitude *****
        double sigma = 0.0;
        double b = Math.atan2(xyz.getZ() + sigma, rp);
        double c;
        double epsilon = 1.0;

        while (epsilon > 0.00000000000001) {
            double bFromPreviousIteration = b;

            c = e * Math.sin(b);
            sigma = a * e * c / Math.sqrt(1 - Math.pow(c, 2));
            b = Math.atan2(xyz.getZ() + sigma, rp);

            epsilon = Math.abs(bFromPreviousIteration - b);
        }

        //***** Calculating longitude *****
//        double l = Math.acos(xyz.getX() / rp);
//        double l = Math.asin(xyz.getY() / rp);
        double l = Math.atan2(xyz.getY(), xyz.getX());

        //***** Calculating Ellipsoid Height *****
        ellipsoidCalculatedParameters.calculateParameters(b);
        double N = ellipsoidCalculatedParameters.getPrimeVerticalRadiusOfCurvature();


//        double deltaR = rp - (N * Math.sin(b));
//        double deltaZ = xyz.getZ() - N * Math.cos(b);
//        double h = Math.sqrt(Math.pow(deltaR, 2) + Math.pow(deltaZ, 2));

//        double h = (rp - C / (1.0 + Math.pow(eSecond, 2) + Math.pow(Math.tan(b), 2))) * Math.sqrt(1.0 + Math.pow(Math.tan(b), 2));

        double h = rp / Math.cos(b) - N;

        return new BLH(b, l, h);
    }
}
