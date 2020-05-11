package pl.sgeonet.GeodeticNetworkAdjustment;

import pl.sgeonet.FieldObservationsObjects.FieldObservation.DeltaHeight;
import pl.sgeonet.FileUtils.ResponseReadFile;
import pl.sgeonet.LSEstimations.ResultsOfLse;
import pl.sgeonet.RaportConfiguration.PrintSettings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class VerticalAdjustmentSummary {

    private ResponseReadFile responseOfFixedPointsFile;
    private ResponseReadFile responseOfLevellingObservationsFile;
    private ResultsOfLse resultsOfLse;

    private int numberOfUnknownParameters;
    private int numberOfFieldObservations;


    private List<String> listIdsOfUnknownParameters;
    private List<DeltaHeight> listOfDeltaHeightFieldObservations;
    private PrintSettings printSettings;
    private double aPrioriStdDeviation;
    private double[][] adjustedParameters ;
    private double[][] fieldObservationAdjustmentSummary;

    public VerticalAdjustmentSummary() {
    }

    public void initializeParameters(int numberOfUnknownParameters, int numberOfFieldObservations) {
        this.numberOfUnknownParameters = numberOfUnknownParameters;
        this.numberOfFieldObservations = numberOfFieldObservations;
        fieldObservationAdjustmentSummary = new double[numberOfFieldObservations][6];
        adjustedParameters = new double[numberOfUnknownParameters][3];
    }

    public ResponseReadFile getResponseOfFixedPointsFile() {
        return responseOfFixedPointsFile;
    }

    public void setResponseOfFixedPointsFile(ResponseReadFile responseOfFixedPointsFile) {
        this.responseOfFixedPointsFile = responseOfFixedPointsFile;
    }

    public ResponseReadFile getResponseOfLevellingObservationsFile() {
        return responseOfLevellingObservationsFile;
    }

    public void setResponseOfLevellingObservationsFile(ResponseReadFile responseOfLevellingObservationsFile) {
        this.responseOfLevellingObservationsFile = responseOfLevellingObservationsFile;
    }

    public ResultsOfLse getResultsOfLse() {
        return resultsOfLse;
    }

    public void setResultsOfLse(ResultsOfLse resultsOfLse) {
        this.resultsOfLse = resultsOfLse;
    }

    public void setListIdsOfUnknownParameters(List<String> listIdsOfUnknownParameters) {
        this.listIdsOfUnknownParameters = listIdsOfUnknownParameters;
    }

    public void setPrintSettings(PrintSettings printSettings) {
        this.printSettings = printSettings;
    }

    public void setaPrioriStdDeviation(double aPrioriStdDeviation) {
        this.aPrioriStdDeviation = aPrioriStdDeviation;
    }

    public double getaPrioriStdDeviation() {
        return aPrioriStdDeviation;
    }

    public void setAdjustedParameters(double[][] adjustedParameters) {
        this.adjustedParameters = adjustedParameters;
    }

    public void setFieldObservationAdjustmentSummary(double[][] fieldObservationAdjustmentSummary) {
        this.fieldObservationAdjustmentSummary = fieldObservationAdjustmentSummary;
    }

    public void setListOfDeltaHeightFieldObservations(List<DeltaHeight> listOfDeltaHeightFieldObservations) {
        this.listOfDeltaHeightFieldObservations = listOfDeltaHeightFieldObservations;
        for (int i = 0; i < numberOfFieldObservations; i++) {
            this.fieldObservationAdjustmentSummary[i][0] = listOfDeltaHeightFieldObservations.get(i).getHeightDifferenceValue();
            this.fieldObservationAdjustmentSummary[i][1] = listOfDeltaHeightFieldObservations.get(i).getHeightDifferenceStdMeanError();
        }
    }


    @Override
    public String toString() {
        String separator = "______________________________________________________________________\r\n";
        StringBuilder sB = new StringBuilder();
        sB.append("***       Supplementary information         ***\r\n");
        sB.append("***     Least square adjustment results     ***\r\n");
        sB.append(separator);
        sB.append("Number of field observations                 : ").append(numberOfFieldObservations).append("\r\n");
        sB.append("Number of unknown parameters                 : ").append(numberOfUnknownParameters).append("\r\n");
        sB.append(separator);
        sB.append("Weighted square sum of residuals Ω[-]        : ").append(resultsOfLse.getWeightedSquareSumOfResiduals()).append("\r\n");
        sB.append("(a priori) standard deviation                : ").append(aPrioriStdDeviation).append("\r\n");
        sB.append("(a posteriori) estimated standard deviation  : ").append(resultsOfLse.getaPosterioriEstimatedStdDeviation()).append("\r\n");
        sB.append("Ratio                                        : ").append(resultsOfLse.getRatio()).append("\r\n");
        sB.append(separator);
        sB.append("Calculated parameters\r\n");
        for (int i = 0; i < numberOfUnknownParameters; i++) {
            if (listIdsOfUnknownParameters != null) {
                sB.append(listIdsOfUnknownParameters.get(i));
            } else {
                sB.append("N/A");
            }
            sB.append(" : ").append(String.format("%." + printSettings.getNumberOfDecimalDigitsOfCalculatedParameters().getPrintValue()
                            + "f%s ± %." + printSettings.getNumberOfDecimalDigitsOfStdErrorOfCalculatedParameters().getPrintValue() + "f%s",
                    adjustedParameters[i][0] * printSettings.getRatioCalculatedParameters(),
                    printSettings.getUnitOfCalculatedParameters().getPrintValue(),
                    adjustedParameters[i][1] * printSettings.getRatioStdErrorOfCalculatedParameters(),
                    printSettings.getUnitOfCalculatedStdErrorOfCalculatedParameters().getPrintValue())).append("\r\n");
        }
        sB.append(separator);
        sB.append("Fields observations\r\n");
        if (listOfDeltaHeightFieldObservations != null) {
            sB.append(String.format("|%17s|", "SECTION     "))
                    .append(String.format("%21s|", "dH       mdH"))
                    .append(String.format("%22s|", "V[residuals]       mV"))
                    .append(String.format("%21s|", "dhA       mdHA"))
                    .append(System.lineSeparator());
            for (int i = 0; i < numberOfFieldObservations; i++) {
                sB.append(String.format("|%5s", listOfDeltaHeightFieldObservations.get(i).getPointFrom())).append(" -> ")
                        .append(String.format("%5s", listOfDeltaHeightFieldObservations.get(i).getPointTo()))
                        .append(" : ")
                        .append(String.format("|%10." + printSettings.getNumberOfDecimalDigitsOfHeightObservations().getPrintValue() + "f",
                                fieldObservationAdjustmentSummary[i][0] * printSettings.getRatioHeightObservations())).append(printSettings.getUnitOfHeightObservations().getPrintValue())
                        .append(" ± ")
                        .append(String.format("%5." + printSettings.getNumberOfDecimalDigitsOfStdErrorHeightObservations().getPrintValue() + "f",
                                fieldObservationAdjustmentSummary[i][1] * printSettings.getRatioStdErrorHeightObservations())).append(printSettings.getUnitStdErrorOfHeightObservations().getPrintValue())
                        .append(String.format("|%10." + printSettings.getNumberOfDecimalDigitsOfResiduals().getPrintValue() + "f",
                                fieldObservationAdjustmentSummary[i][2] * printSettings.getRatioResiduals())).append(printSettings.getUnitOfResiduals().getPrintValue())
                        .append(" ± ")
                        .append(String.format("%5." + printSettings.getNumberOfDecimalDigitsOfStdErrorResiduals().getPrintValue() + "f",
                                fieldObservationAdjustmentSummary[i][3] * printSettings.getRatioStdErrorOfResiduals())).append(printSettings.getUnitStdErrorOfResiduals().getPrintValue())
                        .append(String.format("|%10." + printSettings.getNumberOfDecimalDigitsOfAdjustedHeightObservations().getPrintValue() + "f",
                                fieldObservationAdjustmentSummary[i][4] * printSettings.getRatioAdjustedHeightObservations())).append(printSettings.getUnitOfAdjustedHeightObservations().getPrintValue())
                        .append(" ± ")
                        .append(String.format("%5." + printSettings.getNumberOfDecimalDigitsOfStdErrorAdjustedHeightObservations().getPrintValue() + "f",
                                fieldObservationAdjustmentSummary[i][5] * printSettings.getRatioStdErrorAdjustedHeightObservations())).append(printSettings.getUnitStdErrorOfAdjustedHeightObservations().getPrintValue()).append("|");
                sB.append("\r\n");
            }
        }
//        BufferedWriter writer = null;
//        try {
//            writer = new BufferedWriter(new FileWriter("d:/samplefile1.txt"));
//            writer.write(sB.toString().replace(",", "."));
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return sB.toString().replace(",", ".");
    }


}
