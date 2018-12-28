package pl.sats.GeodeticNetworkAdjustment;

import pl.sats.FieldObservationsObjects.DeltaHeight;
import pl.sats.FieldObservationsObjects.PointNEH;

import java.util.List;

/**
 *
 */
public class VerticalAdjustment {
    private List<PointNEH> listOfFixedPoints;
    private List<DeltaHeight> listOfHeightDifferences;
    private double m0;
    private String[][] results;

    public VerticalAdjustment(List<PointNEH> listOfFixedPoints, List<DeltaHeight> listOfHeightDifferences) {
        this.listOfFixedPoints = listOfFixedPoints;
        this.listOfHeightDifferences = listOfHeightDifferences;
    }

    public void proceedAdjustment() {
        checkDataCorrectness();

    }

    private void checkDataCorrectness() {
    }

    public double getM0() {
        return m0;
    }

    public String[][] getResults() {
        return results;
    }
}
