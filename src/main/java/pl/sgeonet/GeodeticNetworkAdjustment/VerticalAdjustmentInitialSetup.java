package pl.sgeonet.GeodeticNetworkAdjustment;

import pl.sgeonet.RaportConfiguration.Unit;

public class VerticalAdjustmentInitialSetup {
    private VerticalAdjustmentMethod verticalAdjustmentMethod;
    private double aPrioriStandardDeviation = 1.0;
    private Unit unitOfHeightOfFixedPoints;
    private Unit unitOfHeightDifferences;
    private Unit unitOfHeightDifferencesStdMeanErrors;

    public VerticalAdjustmentInitialSetup(VerticalAdjustmentMethod verticalAdjustmentMethod, Unit unitOfHeightOfFixedPoints, Unit unitOfHeightDifferences, Unit unitOfHeightDifferencesStdMeanErrors) {
        this.verticalAdjustmentMethod = verticalAdjustmentMethod;
        calculateAdjustmentParameters();
    }

    private void calculateAdjustmentParameters() {
        switch (verticalAdjustmentMethod) {
            case STANDARD:
                if (unitOfHeightDifferences == Unit.M && unitOfHeightDifferencesStdMeanErrors == Unit.CM) {
                    aPrioriStandardDeviation = 0.01;
                } else if (unitOfHeightDifferences == Unit.M && unitOfHeightDifferencesStdMeanErrors == Unit.MM) {
                    aPrioriStandardDeviation = 0.001;
                }
                break;
            case WITH_LENGTH_OF_SECTION:
                break;
            case WITH_NUMBER_OF_STATIONS_IN_SECTION:
                break;

        }
    }


    public VerticalAdjustmentMethod getVerticalAdjustmentMethod() {
        return verticalAdjustmentMethod;
    }

    public double getaPrioriStandardDeviation() {
        return aPrioriStandardDeviation;
    }
}
