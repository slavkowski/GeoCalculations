package pl.sgeonet.LSEstimations;


import pl.sgeonet.FieldObservationsObjects.FieldObservation.DeltaHeight;

import java.util.ArrayList;
import java.util.List;

public class ResultsOfLse {
    private double weightedSquareSumOfResiduals;
    private int numberOfUnknownParameters;
    private int numberOfFieldObservations;
    private double aPrioriStdDeviation;
    private double aPosterioriEstimatedStdDeviation;
    private double ratio;
    private List<String> listIdsOfUnknownParameters;
    private double[][] adjustedParameters;
    private double[][] adjustedObservations;
    private List<DeltaHeight> listOfDeltaHeightFieldObservations;

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
        adjustedObservations = new double[numberOfFieldObservations][2];
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

    public void setAdjustedParameters(double[][] adjustedParameters) {
        this.adjustedParameters = adjustedParameters;
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

    void setListIdsOfUnknownParameters(List<String> listIdsOfUnknownParameters) {
        this.listIdsOfUnknownParameters = listIdsOfUnknownParameters;
    }

    public void setAdjustedObservations(double[][] adjustedObservations) {
        this.adjustedObservations = adjustedObservations;
    }

    public List<DeltaHeight> getListOfDeltaHeightFieldObservations() {
        return listOfDeltaHeightFieldObservations;
    }

    public void setListOfDeltaHeightFieldObservations(List<DeltaHeight> listOfDeltaHeightFieldObservations) {
        this.listOfDeltaHeightFieldObservations = listOfDeltaHeightFieldObservations;
    }

    @Override
    public String toString() {
        String separator = "______________________________________________________________________\n";
        StringBuilder sB = new StringBuilder();
        sB.append("***       Supplementary information         ***\n");
        sB.append("***     Least square adjustment results     ***\n");
        sB.append(separator);
        sB.append("Number of field observations                 : ").append(numberOfFieldObservations).append("\n");
        sB.append("Number of unknown parameters                 : ").append(numberOfUnknownParameters).append("\n");
        sB.append(separator);
        sB.append("Weighted square sum of residuals Ω[-]        : ").append(weightedSquareSumOfResiduals).append("\n");
        sB.append("(a priori) standard deviation                : ").append(aPrioriStdDeviation).append("\n");
        sB.append("(a posteriori) estimated standard deviation  : ").append(aPosterioriEstimatedStdDeviation).append("\n");
        sB.append("Ratio                                        : ").append(ratio).append("\n");
        sB.append(separator);
        sB.append("Adjusted parameters\n");
        for (int i = 0; i < numberOfUnknownParameters; i++) {
            if (listIdsOfUnknownParameters != null) {
                sB.append(listIdsOfUnknownParameters.get(i));
            } else {
                sB.append("N/A");
            }
            sB.append(" : ").append(String.format("%.4f ± %.4f",
                    adjustedParameters[i][0],
                    adjustedParameters[i][1])).append("\n");
        }
        sB.append(separator);
        sB.append("Fields observations\n");
        if (listOfDeltaHeightFieldObservations != null) {
            for (int i = 0; i < numberOfFieldObservations; i++) {
                sB.append(listOfDeltaHeightFieldObservations.get(i).getPointFrom()).append(" -> ")
                        .append(listOfDeltaHeightFieldObservations.get(i).getPointTo()).append(" : ")
                        .append(listOfDeltaHeightFieldObservations.get(i).getHeightDifferenceValue())
                        .append(" ± ").append(listOfDeltaHeightFieldObservations.get(i).getHeightDifferenceStdMeanError())
                        .append(String.format(" %.4f", adjustedObservations[i][0])).append("\n");
            }
        }
        return sB.toString();
    }
}
