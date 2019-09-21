package pl.sats.GeodeticNetworkAdjustment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.sats.Exceptions.DuplicatedFixedPionts;
import pl.sats.Exceptions.MatrixDegenerateException;
import pl.sats.Exceptions.MatrixWrongSizeException;
import pl.sats.FieldObservationsObjects.DeltaHeight;
import pl.sats.FieldObservationsObjects.PointNEH;
import pl.sats.LSEstimations.LeastSquaresEstimation;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class VerticalAdjustment extends Adjustment {

    private final Logger log = LoggerFactory.getLogger(VerticalAdjustment.class);

    /* List of all fixed points provided in txt file*/
    private List<PointNEH> listOfFixedPoints;
    /* List of height differences provided in txt file*/
    private List<DeltaHeight> listOfHeightDifferences;
    /*A priori standard deviation provided in constructor */
    private double aPrioriStdDeviation;
    /* Set of all unique points in txt file with height differences */
    private Set<String> setOfAllPoints;
    /* Map of fixed point needed for adjustment. Less number of fixed points than txt file is possible */
    private Map<String, Double> mapOfFixedPoints;
    /* Set of all unknown points*/
    private Set<String> setOfUnknownPoints;
    /* List of unknown points. This list is required in order to create matrix A*/
    private List<String> listOfUnknownPoints;
    /* Variables for adjustment*/
    private double[][] A;
    private double[][] P;
    private double[][] L;



    public VerticalAdjustment(List<PointNEH> listOfFixedPoints, List<DeltaHeight> listOfHeightDifferences, double aPrioriStdDeviation) {
        this.listOfFixedPoints = listOfFixedPoints;
        this.listOfHeightDifferences = listOfHeightDifferences;
        this.aPrioriStdDeviation = aPrioriStdDeviation;
    }

    public void proceedAdjustment() throws DuplicatedFixedPionts {
        checkDataCorrectness();
        createVariablesForAdjustment();
        LeastSquaresEstimation lms = new LeastSquaresEstimation(A, P, L);
        lms.setaPrioriStdDeviation(aPrioriStdDeviation);
        lms.setListOfUnknownParameters(listOfUnknownPoints);
        lms.setListOfHeightDifferences(listOfHeightDifferences);

        try {
            lms.executeLeastSquaresEstimation();
            System.out.println(lms.getResultsOfLse().toString());
        } catch (MatrixDegenerateException | MatrixWrongSizeException e) {
            log.warn("Matrix degenerate or matrix wrong size exception -> {}", e.toString());
        }
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
            weightOfObservation = listOfHeightDifferences.get(i).getHeightDifferenceStdMeanError();

            double fixedHeightFrom = 0.0d;
            double fixedHeightTo = 0.0d;

            if (mapOfFixedPoints.containsKey(nameOfPointFrom)) {
                fixedHeightFrom = mapOfFixedPoints.get(nameOfPointFrom);
            } else {
                A[i][listOfUnknownPoints.indexOf(nameOfPointFrom)] = -1.0d;
            }

            if (mapOfFixedPoints.containsKey(nameOfPointTo)) {
                fixedHeightTo = mapOfFixedPoints.get(nameOfPointTo);
            } else {
                A[i][listOfUnknownPoints.indexOf(nameOfPointTo)] = 1.0d;
            }
            L[i][0] = fixedHeightTo - fixedHeightFrom - heightDifference;
            P[i][i] = 1.0d / Math.pow(weightOfObservation, 2);
        }
    }

    protected void checkDataCorrectness() throws DuplicatedFixedPionts {
        setOfAllPoints = new HashSet<>();
        mapOfFixedPoints = new HashMap<>();
        setOfUnknownPoints = new HashSet<>();
        /* create set of all points */
        listOfHeightDifferences.stream().map(DeltaHeight::getPointFrom).forEach(x -> setOfAllPoints.add(x));
        listOfHeightDifferences.stream().map(DeltaHeight::getPointTo).forEach(x -> setOfAllPoints.add(x));
        /* create map of fixed points excluding points not used in height differences field observations  */
        for (PointNEH pointNEH : listOfFixedPoints) {
            if (setOfAllPoints.contains(pointNEH.getName())) {
                if(mapOfFixedPoints.containsKey(pointNEH.getName())){
                    throw new DuplicatedFixedPionts("At least one fixed point is duplicated in set of fixed points. Point: " + pointNEH.getName());
                }else {
                    mapOfFixedPoints.put(pointNEH.getName(), pointNEH.getH());
                }
            }
        }
        /* create set of unknowns points */
        setOfAllPoints.stream().filter(x -> !mapOfFixedPoints.containsKey(x)).forEach(x -> setOfUnknownPoints.add(x));
        /* create list of unknowns points. The order of points is important in order to create matrix A */
        listOfUnknownPoints = setOfUnknownPoints.stream().sorted().collect(Collectors.toList());

        int unknownParameters = setOfUnknownPoints.size();
        int observedLevelledHeightDifferences = listOfHeightDifferences.size();

        A = new double[observedLevelledHeightDifferences][unknownParameters];
        P = new double[observedLevelledHeightDifferences][observedLevelledHeightDifferences];
        L = new double[observedLevelledHeightDifferences][1];
    }
}
