package pl.sgeonet.GeodeticNetworkAdjustment.Vertical;

import pl.sgeonet.RaportConfiguration.Unit;

/**
 * This is initial setup for vertical adjustment
 *
 * @author Sławomir Szwed
 * @version 1.3.6
 * @since 19.05.2020
 */
public class VerticalAdjustmentInitialSetup {
    private final VerticalAdjustmentMethod verticalAdjustmentMethod;
    private double aPrioriStandardDeviation = 1.0;
    private final Unit unitOfHeightOfFixedPoints;
    private final Unit unitOfHeightDifferences;
    private final Unit unitOfStdMeanError;
    private double aPrioriStdDeviation = 1.0;
    private double ratioFixedPoints = 1.0;
    private double ratioHeightDifferences = 1.0;
    private double ratioStdMeanErrors = 1.0;

    /**
     * Constructor for initial setup for vertical adjustment with a priori standard deviation
     *
     * @param verticalAdjustmentMethod - method of adjustment (STANDARD, WITH_NUMBER_OF_SETUPS_IN_SECTION, WITH_LENGTH_OF_SECTION)
     * @param aPrioriStdDeviation - standard mean error (a priori)
     * @param unitOfHeightOfFixedPoints - unit of height of fixed points (KM, M, CM, MM)
     * @param unitOfHeightDifferences - unit of height differences (KM, M, CM, MM)
     * @param unitOfStdMeanErrors - unit of errors (KM, M, CM, MM)
     */
    public VerticalAdjustmentInitialSetup(VerticalAdjustmentMethod verticalAdjustmentMethod, double aPrioriStdDeviation, Unit unitOfHeightOfFixedPoints, Unit unitOfHeightDifferences, Unit unitOfStdMeanErrors) {
        this.verticalAdjustmentMethod = verticalAdjustmentMethod;
        this.unitOfHeightOfFixedPoints = unitOfHeightOfFixedPoints;
        this.unitOfHeightDifferences = unitOfHeightDifferences;
        this.unitOfStdMeanError = unitOfStdMeanErrors;
        this.aPrioriStdDeviation = aPrioriStdDeviation;
        calculateAdjustmentParameters();
    }

    /**
     * Constructor for initial setup for vertical adjustment without a priori standard deviation
     *
     * @param verticalAdjustmentMethod - method of adjustment (STANDARD, WITH_NUMBER_OF_SETUPS_IN_SECTION, WITH_LENGTH_OF_SECTION)
     * @param unitOfHeightOfFixedPoints - unit of height of fixed points (KM, M, CM, MM)
     * @param unitOfHeightDifferences - unit of height differences (KM, M, CM, MM)
     * @param unitOfStdMeanError - unit of errors (KM, M, CM, MM)
     */
    public VerticalAdjustmentInitialSetup(VerticalAdjustmentMethod verticalAdjustmentMethod, Unit unitOfHeightOfFixedPoints, Unit unitOfHeightDifferences, Unit unitOfStdMeanError) {
        this.verticalAdjustmentMethod = verticalAdjustmentMethod;
        this.unitOfHeightOfFixedPoints = unitOfHeightOfFixedPoints;
        this.unitOfHeightDifferences = unitOfHeightDifferences;
        this.unitOfStdMeanError = unitOfStdMeanError;
        calculateAdjustmentParameters();
    }

    /**
     * Method responsible for recalculating all data to [M]
     */
    private void calculateAdjustmentParameters() {
        if (verticalAdjustmentMethod != VerticalAdjustmentMethod.STANDARD) {
            switch (unitOfStdMeanError) {
                case MM:
                    aPrioriStandardDeviation = aPrioriStdDeviation * 0.001;
                    break;
                case CM:
                    aPrioriStandardDeviation = aPrioriStdDeviation * 0.01;
                    break;
                case KM:
                    aPrioriStandardDeviation = aPrioriStdDeviation * 1000.0;
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

    public double getaPrioriStdDeviation() {
        return aPrioriStdDeviation;
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
