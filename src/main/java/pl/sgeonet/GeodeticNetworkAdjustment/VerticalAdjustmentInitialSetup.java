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
    private double stdMeanError = 1.0;

    public VerticalAdjustmentInitialSetup(VerticalAdjustmentMethod verticalAdjustmentMethod, double stdMeanError, Unit unitOfHeightOfFixedPoints, Unit unitOfHeightDifferences, Unit unitOfStdMeanErrors) {
        this.verticalAdjustmentMethod = verticalAdjustmentMethod;
        this.unitOfHeightOfFixedPoints = unitOfHeightOfFixedPoints;
        this.unitOfHeightDifferences = unitOfHeightDifferences;
        this.unitOfStdMeanError = unitOfStdMeanErrors;
        this.stdMeanError = stdMeanError;
        calculateAdjustmentParameters();
    }

    public VerticalAdjustmentInitialSetup(VerticalAdjustmentMethod verticalAdjustmentMethod, Unit unitOfHeightOfFixedPoints, Unit unitOfHeightDifferences, Unit unitOfStdMeanErrors) {
        this.verticalAdjustmentMethod = verticalAdjustmentMethod;
        this.unitOfHeightOfFixedPoints = unitOfHeightOfFixedPoints;
        this.unitOfHeightDifferences = unitOfHeightDifferences;
        this.unitOfStdMeanError = unitOfStdMeanErrors;
        calculateAdjustmentParameters();
    }

    private void calculateAdjustmentParameters() {
        if (unitOfHeightDifferences == Unit.M && unitOfStdMeanError == Unit.CM) {
            aPrioriStandardDeviation = 0.01 * stdMeanError;
        } else if (unitOfHeightDifferences == Unit.M && unitOfStdMeanError == Unit.MM) {
            aPrioriStandardDeviation = 0.001 *stdMeanError;
        }
    }

    public VerticalAdjustmentMethod getVerticalAdjustmentMethod() {
        return verticalAdjustmentMethod;
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

    public void setStdMeanError(double stdMeanError) {
        this.stdMeanError = stdMeanError;
    }
}
