package pl.sgeonet.LSEstimations;


import pl.sgeonet.FieldObservationsObjects.FieldObservation.DeltaHeight;
import pl.sgeonet.RaportConfiguration.PrintSettings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
    private double[][] fieldObservationAdjustmentSummary;
    private List<DeltaHeight> listOfDeltaHeightFieldObservations;
    private PrintSettings printSettings;

    public ResultsOfLse(int numberOfUnknownParameters, int numberOfFieldObservations) {
        this.numberOfUnknownParameters = numberOfUnknownParameters;
        this.numberOfFieldObservations = numberOfFieldObservations;
        this.fieldObservationAdjustmentSummary = new double[numberOfFieldObservations][7];
        adjustedParameters = new double[numberOfUnknownParameters][3];
    }

    void setWeightedSquareSumOfResiduals(double weightedSquareSumOfResiduals) {
        this.weightedSquareSumOfResiduals = weightedSquareSumOfResiduals;
    }

    void setaPrioriStdDeviation(double aPrioriStdDeviation) {
        this.aPrioriStdDeviation = aPrioriStdDeviation;
    }

    void setaPosterioriEstimatedStdDeviation(double aPosterioriEstimatedStdDeviation) {
        this.aPosterioriEstimatedStdDeviation = aPosterioriEstimatedStdDeviation;
    }

    void setRatio(double ratio) {
        this.ratio = ratio;
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

    public void setListOfDeltaHeightFieldObservations(List<DeltaHeight> listOfDeltaHeightFieldObservations) {
        this.listOfDeltaHeightFieldObservations = listOfDeltaHeightFieldObservations;
        for (int i = 0; i < numberOfFieldObservations; i++) {
            this.fieldObservationAdjustmentSummary[i][0] = listOfDeltaHeightFieldObservations.get(i).getHeightDifferenceValue();
            this.fieldObservationAdjustmentSummary[i][1] = listOfDeltaHeightFieldObservations.get(i).getHeightDifferenceStdMeanError();
        }
    }

    public void setFieldObservationAdjustmentSummary(double[][] fieldObservationAdjustmentSummary) {
        for (int i = 0; i < numberOfFieldObservations; i++) {
            this.fieldObservationAdjustmentSummary[i][2] = fieldObservationAdjustmentSummary[i][0];
            this.fieldObservationAdjustmentSummary[i][4] = this.fieldObservationAdjustmentSummary[i][0] + fieldObservationAdjustmentSummary[i][0];
        }
    }

    @Override
    public String toString() {
        printSettings = new PrintSettings();
        String separator = "______________________________________________________________________\r\n";
        StringBuilder sB = new StringBuilder();
        sB.append("***       Supplementary information         ***\r\n");
        sB.append("***     Least square adjustment results     ***\r\n");
        sB.append(separator);
        sB.append("Number of field observations                 : ").append(numberOfFieldObservations).append("\r\n");
        sB.append("Number of unknown parameters                 : ").append(numberOfUnknownParameters).append("\r\n");
        sB.append(separator);
        sB.append("Weighted square sum of residuals Ω[-]        : ").append(weightedSquareSumOfResiduals).append("\r\n");
        sB.append("(a priori) standard deviation                : ").append(aPrioriStdDeviation).append("\r\n");
        sB.append("(a posteriori) estimated standard deviation  : ").append(aPosterioriEstimatedStdDeviation).append("\r\n");
        sB.append("Ratio                                        : ").append(ratio).append("\r\n");
        sB.append(separator);
        sB.append("Calculated parameters\r\n");
        for (int i = 0; i < numberOfUnknownParameters; i++) {
            if (listIdsOfUnknownParameters != null) {
                sB.append(listIdsOfUnknownParameters.get(i));
            } else {
                sB.append("N/A");
            }
            sB.append(" : ").append(String.format("%." + printSettings.getNumberOfDecimalDigitsOfCalculatedHeight().getPrintValue()
                            + "f%s ± %." + printSettings.getNumberOfDecimalDigitsOfStdErrorOfCalculatedHeight().getPrintValue() + "f%s",
                    adjustedParameters[i][0],
                    printSettings.getUnitOfCalculatedHeight().getPrintValue(),
                    adjustedParameters[i][1] * printSettings.getCalculatedHeightVsStdErrorRatio(),
                    printSettings.getUnitOfCalculatedStdErrorOfCalculatedHeight().getPrintValue())).append("\r\n");
        }
        sB.append(separator);
        sB.append("Fields observations\r\n");
        if (listOfDeltaHeightFieldObservations != null) {
            sB.append(String.format("|%17s|","SECTION")).append("\r\n");
            for (int i = 0; i < numberOfFieldObservations; i++) {
                sB.append(String.format("|%5s",listOfDeltaHeightFieldObservations.get(i).getPointFrom())).append(" -> ")
                        .append(String.format("%5s",listOfDeltaHeightFieldObservations.get(i).getPointTo())).append(" : ")
                        .append(String.format("|%10.3f", fieldObservationAdjustmentSummary[i][0]))
                        .append(" ±").append(String.format("%5.1f|", fieldObservationAdjustmentSummary[i][1]))
                        .append(String.format("%10.4f", fieldObservationAdjustmentSummary[i][2]))
                        .append(String.format("%5.1f", fieldObservationAdjustmentSummary[i][3]))
                        .append(String.format("%10.3f", fieldObservationAdjustmentSummary[i][4]));
                sB.append("\r\n");
            }
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("d:/samplefile1.txt"));
            writer.write(sB.toString().replace(",", "."));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sB.toString().replace(",", ".");
    }
}
