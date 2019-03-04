package pl.sats.LSEstimations;


public class ResultsOfLse {
    private double weightedSquareSumOfResiduals;
    private int numberOfUnknownParameters;
    private int numberOfFieldObservations;
    private double aPrioriStdDeviation;
    private double aPosterioriEstimatedStdDeviation;
    private double ratio;
    private double[][] adjustedParameters;
    private double[][] adjustedObservations;

    public double getWeightedSquareSumOfResiduals() {
        return weightedSquareSumOfResiduals;
    }

    void setWeightedSquareSumOfResiduals(double weightedSquareSumOfResiduals) {
        this.weightedSquareSumOfResiduals = weightedSquareSumOfResiduals;
    }

    public int getNumberOfUnknownParameters() {
        return numberOfUnknownParameters;
    }

    void setNumberOfUnknownParameters(int numberOfUnknownParameters) {
        this.numberOfUnknownParameters = numberOfUnknownParameters;
        adjustedParameters = new double[numberOfUnknownParameters][3];
    }

    public int getNumberOfFieldObservations() {
        return numberOfFieldObservations;
    }

    void setNumberOfFieldObservations(int numberOfFieldObservations) {
        this.numberOfFieldObservations = numberOfFieldObservations;
    }

    public double getaPrioriStdDeviation() {
        return aPrioriStdDeviation;
    }

    void setaPrioriStdDeviation(double aPrioriStdDeviation) {
        this.aPrioriStdDeviation = aPrioriStdDeviation;
    }

    public double getaPosterioriEstimatedStdDeviation() {
        return aPosterioriEstimatedStdDeviation;
    }

    void setaPosterioriEstimatedStdDeviation(double aPosterioriEstimatedStdDeviation) {
        this.aPosterioriEstimatedStdDeviation = aPosterioriEstimatedStdDeviation;
    }

    public double getRatio() {
        return ratio;
    }

    void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double[][] getAdjustedParameters() {
        return adjustedParameters;
    }

    void setAdjustedParameters(double[][] adjustedParameters, int j) {
        for (int i = 0; i < numberOfUnknownParameters; i++) {
            this.adjustedParameters[i][j] = adjustedParameters[i][0];
        }
    }
    void setAdjustedParameters(double[] adjustedParameters, int j) {
        for (int i = 0; i < numberOfUnknownParameters; i++) {
            this.adjustedParameters[i][j] = adjustedParameters[i];
        }
    }

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        sB.append("***       Supplementary information         ***\n");
        sB.append("***     Least square adjustment results     ***\n");
        sB.append("______________________________________________________________________\n");
        sB.append("Number of field observations                 : ").append(numberOfFieldObservations).append("\n");
        sB.append("Number of unknown parameters                 : ").append(numberOfUnknownParameters).append("\n");
        sB.append("______________________________________________________________________\n");
        sB.append("Weighted square sum of residuals Ω[-]        : ").append(weightedSquareSumOfResiduals).append("\n");
        sB.append("(a priori) standard deviation                : ").append(aPrioriStdDeviation).append("\n");
        sB.append("(a posteriori) estimated standard deviation  : ").append(aPosterioriEstimatedStdDeviation).append("\n");
        sB.append("Ratio                                        : ").append(ratio).append("\n");
        sB.append("______________________________________________________________________\n");
        sB.append("Adjusted parameters\n");
        for (int i = 0; i < numberOfUnknownParameters; i++) {
            sB.append(String.format("%.4f ± %.1fmm", adjustedParameters[i][1], adjustedParameters[i][2]*1000)).append("\n");
        }
        return sB.toString();
    }
}
