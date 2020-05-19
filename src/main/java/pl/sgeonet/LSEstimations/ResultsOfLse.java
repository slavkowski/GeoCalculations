package pl.sgeonet.LSEstimations;

public class ResultsOfLse {
    private double weightedSquareSumOfResiduals;
    private final int numberOfUnknownParameters;
    private final int numberOfFieldObservations;
    private double aPosteriorEstimatedStdDeviation;
    private double ratio;
    private final double[][] adjustedParameters;
    private final double[][] fieldObservationAdjustmentSummary;


    public ResultsOfLse(int numberOfUnknownParameters, int numberOfFieldObservations) {
        this.numberOfUnknownParameters = numberOfUnknownParameters;
        this.numberOfFieldObservations = numberOfFieldObservations;
        this.fieldObservationAdjustmentSummary = new double[numberOfFieldObservations][6];
        adjustedParameters = new double[numberOfUnknownParameters][3];
    }

    void setWeightedSquareSumOfResiduals(double weightedSquareSumOfResiduals) {
        this.weightedSquareSumOfResiduals = weightedSquareSumOfResiduals;
    }

    public double getWeightedSquareSumOfResiduals() {
        return weightedSquareSumOfResiduals;
    }

    void setaPosteriorEstimatedStdDeviation(double aPosteriorEstimatedStdDeviation) {
        this.aPosteriorEstimatedStdDeviation = aPosteriorEstimatedStdDeviation;
    }

    public double getaPosteriorEstimatedStdDeviation() {
        return aPosteriorEstimatedStdDeviation;
    }

    void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }

    void setAdjustedParameters(double[][] adjustedParameters, int j) {
        for (int i = 0; i < numberOfUnknownParameters; i++) {
            this.adjustedParameters[i][j] = adjustedParameters[i][0];
        }
    }

    public double[][] getAdjustedParameters() {
        return adjustedParameters;
    }

    public double[][] getFieldObservationAdjustmentSummary() {
        return fieldObservationAdjustmentSummary;
    }

    void setAdjustedParameters(double[] adjustedParameters, int j) {
        for (int i = 0; i < numberOfUnknownParameters; i++) {
            this.adjustedParameters[i][j] = adjustedParameters[i];
        }
    }

    public void setFieldObservationAdjustmentSummary(double[][] fieldObservationAdjustmentSummary) {
        for (int i = 0; i < numberOfFieldObservations; i++) {
            this.fieldObservationAdjustmentSummary[i][2] = fieldObservationAdjustmentSummary[i][0];
            this.fieldObservationAdjustmentSummary[i][4] = this.fieldObservationAdjustmentSummary[i][0] + fieldObservationAdjustmentSummary[i][0];
        }
    }

    public void setStdErrorsOfAdjustedObservations(double[] cao) {
        for (int i = 0; i < numberOfFieldObservations; i++) {
            this.fieldObservationAdjustmentSummary[i][5] = cao[i];
        }
    }

    public void setStdErrorsOfResiduals(double[] cv) {
        for (int i = 0; i < numberOfFieldObservations; i++) {
            this.fieldObservationAdjustmentSummary[i][3] = cv[i];
        }
    }

}
