package pl.sgeonet.GeodeticNetworkAdjustment;

import pl.sgeonet.RaportConfiguration.Unit;

/**
 *
 */
public class VerticalAdjustmentInitialSetup {
    private VerticalAdjustmentMethod verticalAdjustmentMethod;
    private double aPrioriStandardDeviation = 1.0;
    private Unit unitOfHeightOfFixedPoints;
    private Unit unitOfHeightDifferences;
    private Unit unitOfStdMeanError;
    private double stdMeanError;

    public VerticalAdjustmentInitialSetup(VerticalAdjustmentMethod verticalAdjustmentMethod, Unit unitOfHeightOfFixedPoints, Unit unitOfHeightDifferences, Unit unitOfStdMeanErrors) {
        this.verticalAdjustmentMethod = verticalAdjustmentMethod;
        this.unitOfHeightOfFixedPoints = unitOfHeightOfFixedPoints;
        this.unitOfHeightDifferences = unitOfHeightDifferences;
        this.unitOfStdMeanError = unitOfStdMeanErrors;
        calculateAdjustmentParameters();
    }

    private void calculateAdjustmentParameters() {
        switch (verticalAdjustmentMethod) {
            case STANDARD:
                if (unitOfHeightDifferences == Unit.M && unitOfStdMeanError == Unit.CM) {
                    aPrioriStandardDeviation = 0.01;
                } else if (unitOfHeightDifferences == Unit.M && unitOfStdMeanError == Unit.MM) {
                    aPrioriStandardDeviation = 0.001;
                }
                break;
            case WITH_LENGTH_OF_SECTION:
                break;
            case WITH_NUMBER_OF_SETUPS_IN_SECTION:
                break;

        }
    }

    public double getaPrioriStandardDeviation() {
        return aPrioriStandardDeviation;
    }

    public Unit getUnitOfHeightOfFixedPoints() {
        return unitOfHeightOfFixedPoints;
    }

    public Unit getUnitOfHeightDifferences() {
        return unitOfHeightDifferences;
    }

    public Unit getUnitOfStdMeanError() {
        return unitOfStdMeanError;
    }

    public double getStdMeanError() {
        return stdMeanError;
    }
}
