package pl.sgeonet.GeodeticNetworkAdjustment.Vertical;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sgeonet.Exceptions.DuplicatedFixedPoints;
import pl.sgeonet.Exceptions.MatrixDegenerateException;
import pl.sgeonet.Exceptions.MatrixWrongSizeException;
import pl.sgeonet.FieldObservationsObjects.FieldObservation.DeltaHeight;
import pl.sgeonet.FieldObservationsObjects.PointCoordinates.NEH;
import pl.sgeonet.FieldObservationsObjects.PointCoordinates.PointType;
import pl.sgeonet.FileUtils.FileUtils;
import pl.sgeonet.GeodeticNetworkAdjustment.Adjustment;
import pl.sgeonet.LSEstimations.LeastSquaresEstimation;
import pl.sgeonet.LSEstimations.ResultsOfLse;
import pl.sgeonet.RaportConfiguration.PrintSettings;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Vertical adjustment using LSM
 */
public class VerticalAdjustment extends Adjustment {

    private final Logger LOGGER = LoggerFactory.getLogger(VerticalAdjustment.class);

    private final VerticalAdjustmentSummary verticalAdjustmentSummary;

    private final File fixedPointFile;
    private final File observationFile;
    private final PrintSettings printSettings;

    /* Initial setup for adjustment */
    private final VerticalAdjustmentInitialSetup verticalAdjustmentInitialSetup;
    /* List of all fixed points provided in txt file*/
    private List<NEH> listOfFixedPoints;
    /* List of height differences provided in txt file*/
    private List<DeltaHeight> listOfHeightDifferences;
    /*A priori standard deviation provided in constructor */
    private final double aPrioriStdDeviation;
    /* Set of all unique points in txt file with height differences */
    private Set<String> setOfAllPoints;
    /* Map of fixed point needed for adjustment. Less number of fixed points than txt file is possible */
    private Map<String, Double> mapOfFixedPoints;
    /* Set of all unknown points*/
    private Set<String> setOfUnknownPoints;
    /* List of unknown points. This list is required in order to create matrix A*/
    private List<String> listIdsOfUnknownParameters;
    /* Variables for adjustment*/
    private double[][] A;
    private double[][] P;
    private double[][] L;


    public VerticalAdjustment(File fixedPointFile, File observationFile, VerticalAdjustmentInitialSetup verticalAdjustmentInitialSetup, PrintSettings printSettings) {
        this.fixedPointFile = fixedPointFile;
        this.observationFile = observationFile;
        this.aPrioriStdDeviation = verticalAdjustmentInitialSetup.getaPrioriStandardDeviation();
        this.verticalAdjustmentInitialSetup = verticalAdjustmentInitialSetup;
        this.printSettings = printSettings;
        verticalAdjustmentSummary = new VerticalAdjustmentSummary();
    }

    public void proceedAdjustment() throws DuplicatedFixedPoints {
        try {
            retrieveDataFromTxtFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adjustDataUsingUnitRatio();
        checkDataCorrectness();
        createVariablesForAdjustment();
        LeastSquaresEstimation lms = new LeastSquaresEstimation(A, P, L, aPrioriStdDeviation);


        try {
            ResultsOfLse resultsOfLse = lms.executeLeastSquaresEstimation();

            verticalAdjustmentSummary.setResultsOfLse(resultsOfLse);
            verticalAdjustmentSummary.setaPrioriStdDeviation(aPrioriStdDeviation);
            verticalAdjustmentSummary.setPrintSettings(printSettings);
            verticalAdjustmentSummary.setListIdsOfUnknownParameters(listIdsOfUnknownParameters);
            verticalAdjustmentSummary.setAdjustedParameters(resultsOfLse.getAdjustedParameters());
            verticalAdjustmentSummary.setFieldObservationAdjustmentSummary(resultsOfLse.getFieldObservationAdjustmentSummary());
            verticalAdjustmentSummary.setListOfDeltaHeightFieldObservations(listOfHeightDifferences);

            System.out.println(verticalAdjustmentSummary.toString());

        } catch (MatrixDegenerateException | MatrixWrongSizeException e) {
            LOGGER.warn("Matrix degenerate or matrix wrong size exception -> {}", e.toString());
        }


    }

    private void adjustDataUsingUnitRatio() {
        listOfFixedPoints.forEach(point -> point.setH(verticalAdjustmentInitialSetup.getRatioFixedPoints() * point.getH()));
        listOfHeightDifferences.forEach(heightData -> heightData.setHeightDifferenceValue(verticalAdjustmentInitialSetup.getRatioHeightDifferences() * heightData.getHeightDifferenceValue()));
    }

    private void retrieveDataFromTxtFiles() throws IOException {

        FileUtils<NEH> fileUtils = new FileUtils<>(PointType.H, new NEH());

        listOfFixedPoints = fileUtils.readFile(fixedPointFile, "FIXED POINTS");
        verticalAdjustmentSummary.setResponseOfFixedPointsFile(fileUtils.getResponseReadFile());

        listOfHeightDifferences = fileUtils.readLevelingObservations(observationFile, "LEVELLING OBSERVATIONS", verticalAdjustmentInitialSetup.getVerticalAdjustmentMethod());
        verticalAdjustmentSummary.setResponseOfLevellingObservationsFile(fileUtils.getReadLevellingFileResponse());

    }

    protected void createVariablesForAdjustment() {
        String nameOfPointFrom;
        String nameOfPointTo;
        double heightDifference;
        double weightOfObservation;

        for (int i = 0; i < listOfHeightDifferences.size(); i++) {
            nameOfPointFrom = listOfHeightDifferences.get(i).getPointFrom();
            nameOfPointTo = listOfHeightDifferences.get(i).getPointTo();
            heightDifference = listOfHeightDifferences.get(i).getHeightDifferenceValue();

            double fixedHeightFrom = 0.0d;
            double fixedHeightTo = 0.0d;

            if (mapOfFixedPoints.containsKey(nameOfPointFrom)) {
                fixedHeightFrom = mapOfFixedPoints.get(nameOfPointFrom);
            } else {
                A[i][listIdsOfUnknownParameters.indexOf(nameOfPointFrom)] = -1.0d;
            }

            if (mapOfFixedPoints.containsKey(nameOfPointTo)) {
                fixedHeightTo = mapOfFixedPoints.get(nameOfPointTo);
            } else {
                A[i][listIdsOfUnknownParameters.indexOf(nameOfPointTo)] = 1.0d;
            }
            L[i][0] = fixedHeightTo - fixedHeightFrom - heightDifference;
            switch (verticalAdjustmentInitialSetup.getVerticalAdjustmentMethod()) {
                case STANDARD:
                    weightOfObservation = verticalAdjustmentInitialSetup.getRatioStdMeanErrors() * listOfHeightDifferences.get(i).getHeightDifferenceStdMeanError();
                    listOfHeightDifferences.get(i).setHeightDifferenceStdMeanError(listOfHeightDifferences.get(i).getHeightDifferenceStdMeanError() * verticalAdjustmentInitialSetup.getRatioStdMeanErrors());
                    P[i][i] = 1.0d / Math.pow(weightOfObservation, 2);
                    break;
                case WITH_LENGTH_OF_SECTION:
                    weightOfObservation = listOfHeightDifferences.get(i).getLengthOfSection();
                    P[i][i] = 1.0d / weightOfObservation;
                    listOfHeightDifferences.get(i).setHeightDifferenceStdMeanError(verticalAdjustmentInitialSetup.getRatioStdMeanErrors() * verticalAdjustmentInitialSetup.getaPrioriStdDeviation() * Math.sqrt(weightOfObservation));
                    break;
                case WITH_NUMBER_OF_SETUPS_IN_SECTION:
                    weightOfObservation = listOfHeightDifferences.get(i).getNumberOfSetupsInSection();
                    P[i][i] = 1.0d / weightOfObservation;
                    listOfHeightDifferences.get(i).setHeightDifferenceStdMeanError(verticalAdjustmentInitialSetup.getRatioStdMeanErrors() * verticalAdjustmentInitialSetup.getaPrioriStdDeviation() * Math.sqrt(weightOfObservation));
                    break;
            }
        }
    }

    /**
     * Method responsible for checking correctness of data
     *
     * @throws DuplicatedFixedPoints when multiple fixed points
     */
    protected void checkDataCorrectness() throws DuplicatedFixedPoints {
        setOfAllPoints = new HashSet<>();
        mapOfFixedPoints = new HashMap<>();
        setOfUnknownPoints = new HashSet<>();
        /* create set of all points */
        listOfHeightDifferences.stream().map(DeltaHeight::getPointFrom).forEach(x -> setOfAllPoints.add(x));
        listOfHeightDifferences.stream().map(DeltaHeight::getPointTo).forEach(x -> setOfAllPoints.add(x));
        /* create map of fixed points excluding points not used in height differences field observations  */
        for (NEH NEH : listOfFixedPoints) {
            if (setOfAllPoints.contains(NEH.getId())) {
                if (mapOfFixedPoints.containsKey(NEH.getId())) {
                    throw new DuplicatedFixedPoints("At least one fixed point is duplicated in set of fixed points. Point: " + NEH.getId());
                } else {
                    mapOfFixedPoints.put(NEH.getId(), NEH.getH());
                }
            }
        }
        /* create set of unknowns points */
        setOfAllPoints.stream().filter(x -> !mapOfFixedPoints.containsKey(x)).forEach(x -> setOfUnknownPoints.add(x));
        /* create list of unknowns points. The order of points is important in order to create matrix A */
        listIdsOfUnknownParameters = setOfUnknownPoints.stream().sorted().collect(Collectors.toList());

        int unknownParameters = setOfUnknownPoints.size();
        int observedLevelledHeightDifferences = listOfHeightDifferences.size();

        verticalAdjustmentSummary.initializeParameters(unknownParameters, observedLevelledHeightDifferences);

        A = new double[observedLevelledHeightDifferences][unknownParameters];
        P = new double[observedLevelledHeightDifferences][observedLevelledHeightDifferences];
        L = new double[observedLevelledHeightDifferences][1];
    }
}
