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
    private double ratioFixedPoints = 1.0;
    private double ratioHeightDifferences = 1.0;
    private double ratioStdMeanErrors = 1.0;

    public VerticalAdjustmentInitialSetup(VerticalAdjustmentMethod verticalAdjustmentMethod, double stdMeanError, Unit unitOfHeightOfFixedPoints, Unit unitOfHeightDifferences, Unit unitOfStdMeanErrors) {
        this.verticalAdjustmentMethod = verticalAdjustmentMethod;
        this.unitOfHeightOfFixedPoints = unitOfHeightOfFixedPoints;
        this.unitOfHeightDifferences = unitOfHeightDifferences;
        this.unitOfStdMeanError = unitOfStdMeanErrors;
        this.stdMeanError = stdMeanError;
        calculateAdjustmentParameters();
    }

    public VerticalAdjustmentInitialSetup(VerticalAdjustmentMethod verticalAdjustmentMethod, Unit unitOfHeightOfFixedPoints, Unit unitOfHeightDifferences, Unit unitOfStdMeanError) {
        this.verticalAdjustmentMethod = verticalAdjustmentMethod;
        this.unitOfHeightOfFixedPoints = unitOfHeightOfFixedPoints;
        this.unitOfHeightDifferences = unitOfHeightDifferences;
        this.unitOfStdMeanError = unitOfStdMeanError;
        calculateAdjustmentParameters();
    }

    private void calculateAdjustmentParameters() {
        if (verticalAdjustmentMethod != VerticalAdjustmentMethod.STANDARD) {
            switch (unitOfStdMeanError) {
                case MM:
                    aPrioriStandardDeviation = stdMeanError * 0.001;
                    break;
                case CM:
                    aPrioriStandardDeviation = stdMeanError * 0.01;
                    break;
                case KM:
                    aPrioriStandardDeviation = stdMeanError * 1000.0;
                    break;
            }
        }
        switch (unitOfHeightOfFixedPoints) {
            case MM:
                ratioFixedPoints = 0.001;
                break;
            case CM:
                ratioFixedPoints = 0.01;
                break;
            case KM:
                ratioFixedPoints = 1000.0;
                break;
        }
        switch (unitOfHeightDifferences) {
            case MM:
                ratioHeightDifferences = 0.001;
                break;
            case CM:
                ratioHeightDifferences = 0.01;
                break;
            case KM:
                ratioHeightDifferences = 1000.0;
                break;
        }
        switch (unitOfStdMeanError) {
            case MM:
                ratioStdMeanErrors = 0.001;
                break;
            case CM:
                ratioStdMeanErrors = 0.01;
                break;
            case KM:
                ratioStdMeanErrors = 1000.0;
                break;
        }
    }

    public VerticalAdjustmentMethod getVerticalAdjustmentMethod() {
        return verticalAdjustmentMethod;
    }

    public double getaPrioriStandardDeviation() {
        return aPrioriStandardDeviation;
    }

    public double getStdMeanError() {
        return stdMeanError;
    }

    public double getRatioFixedPoints() {
        return ratioFixedPoints;
    }

    public double getRatioHeightDifferences() {
        return ratioHeightDifferences;
    }

    public double getRatioStdMeanErrors() {
        return ratioStdMeanErrors;
    }
}
