package pl.sats;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * <h1>Azimuth and distance calculations</h1>
 * This class allows to calculate distance and azimuth
 * between two points in cartesian coordinate system
 *
 * @author Slawomir Szwed
 * @version 1.0
 * @since 2018-12-15
 */
public class AzimuthDistanceCalculation {
    /**
     * This method is responsible for calculate distance between two points
     * @param N1 - north component of first point
     * @param E1 - east component of first point
     * @param N2 - north component of second point
     * @param E2 - east component of second point
     * @return double - calculated distance
     */
    public double calculateDistance(double N1, double E1, double N2, double E2) {
        return sqrt(pow((N1 - N2), 2) + pow((E1 - E2), 2));
    }

    /**
     * This method is responsible for calculate azimuth between two points
     * @param N1 - north component of first point
     * @param E1 - east component of first point
     * @param N2 - north component of second point
     * @param E2 - east component of second point
     * @return double - calculated azimuth in radians
     */
    public double calculateAzimuth(double N1, double E1, double N2, double E2) {
        double deltaN = N2 - N1;
        double deltaE = E2 - E1;

        if (deltaN > 0 && deltaE > 0) {
            return Math.atan(deltaE / deltaN);
        } else if (deltaN > 0 && deltaE < 0) {
            return Math.atan(deltaE / deltaN) + 2 * Math.PI;
        } else if (deltaN < 0 && deltaE != 0) {
            return Math.atan(deltaE / deltaN) + Math.PI;
        } else if (deltaN > 0 && deltaE == 0) {
            return 0.0;
        } else if (deltaN == 0 && deltaE > 0) {
            return Math.PI / 2;
        } else if (deltaN < 0 && deltaE == 0) {
            return Math.PI;
        } else if (deltaN == 0 && deltaE < 0) {
            return 1.5 * Math.PI;
        } else {
            return 0.0;
        }
    }
}
