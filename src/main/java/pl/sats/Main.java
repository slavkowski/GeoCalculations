package pl.sats;

import pl.sats.GeodeticProjectionCalculations.BLHToXYZ;
import pl.sats.GeodeticProjectionCalculations.EllipsoidDetails;

public class Main {
    public static void main(String[] args) {
        BLHToXYZ blhToXYZ = new BLHToXYZ(EllipsoidDetails.WGS84);

    }
}
