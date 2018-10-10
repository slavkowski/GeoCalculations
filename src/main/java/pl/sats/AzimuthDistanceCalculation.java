package pl.sats;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class AzimuthDistanceCalculation {
    public double calculateDistance(double N1, double E1, double N2, double E2) {
        return sqrt(pow((N1 - N2), 2) + pow((E1 - E2), 2));
    }
    public double calculateAzimuth(double N1, double E1, double N2, double E2){
        double deltaN = N2 - N1;
        double deltaE = E2 - E1;

        if (deltaN >0 && deltaE >0){
            return  Math.atan(deltaE/deltaN);
        }
        else if (deltaN >0 && deltaE <0){
            return Math.atan(deltaE/deltaN)+2*Math.PI;
        }
        else if (deltaN <0 && deltaE != 0){
            return  Math.atan(deltaE/deltaN)+Math.PI;
        }
        else if (deltaN >0 && deltaE ==0){
            return 0.0;
        }
        else if (deltaN ==0 && deltaE >0){
            return  Math.PI/2;
        }
        else if (deltaN <0 && deltaE ==0){
            return Math.PI;
        }
        else if (deltaN ==0 && deltaE <0){
            return  1.5*Math.PI;
        }
        else {
            return  0.0;
        }
    }
}
