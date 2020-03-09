package pl.sgeonet.LSEstimations;

import pl.sgeonet.Exceptions.MatrixDegenerateException;
import pl.sgeonet.Exceptions.MatrixWrongSizeException;
import pl.sgeonet.FieldObservationsObjects.FieldObservation.DeltaHeight;
import pl.sgeonet.MathExtraCalculations.Matrix;
import pl.sgeonet.RaportConfiguration.PrintSettings;

import java.util.List;

/**
 * Having following formula: AX = L + V
 * we can estimate x parameters by calculating following equation X = -(A<sup>t</sup>PA)<sup>-1</sup>A<sup>t</sup>PL
 * <p>
 * where:
 * <ul>
 * <li>A [n * m] coefficient matrix where n is the number of observations and m is the number of parameters</li>
 * <li>P [n * n] weight matrix of observations</li>
 * <li>L [n * 1] matrix of measured values</li>
 * <li>V [n * 1] matrix of random residuals errors</li>
 * <li>X [m * 1] matrix of unknown parameters</li>
 * </ul>
 * Other:
 * <ul>
 * <li>N denotes (A<sup>t</sup>PA)<sup>-1</sup></li>
 * <li></li>
 * <li></li>
 * <li></li>
 * <li></li>
 * </ul>
 *
 * @author Slawomir Szwed
 * @version 1.0
 * @since 2018-12-15
 */
public class LeastSquaresEstimation {
    private double[][] A;
    private double[][] P;
    private double[][] L;
    private double[][] X;
    private double[][] N;
    private double[][] M;
    private double[][] VtPV;
    private double m0;
    private double m0Sqr;
    private double aPrioriStdDeviation;
    private ResultsOfLse resultsOfLse;
    private Matrix matrix;
    private List<String> listIdsOfUnknownParameters;
    private List<DeltaHeight> listOfDeltaHeightFieldObservations;
    private int numberOfOvernumberObservations;
    private PrintSettings printSettings;
    private int numberOfFieldObservations;
    private int numberOfUnknownParameters;
    double[] CAO;

    /**
     * @param a - coefficient matrix
     * @param p - weight matrix of observations
     * @param l - matrix of measured values
     */
    public LeastSquaresEstimation(double[][] a, double[][] p, double[][] l, List<String> listIdsOfUnknownParameters) {
        A = a;
        P = p;
        L = l;
        matrix = new Matrix();
        this.listIdsOfUnknownParameters = listIdsOfUnknownParameters;
    }

    /**
     * @param a - coefficient matrix
     * @param p - weight matrix of observations
     * @param l - matrix of measured values
     */
    public LeastSquaresEstimation(double[][] a, double[][] p, double[][] l) {
        A = a;
        P = p;
        L = l;
        matrix = new Matrix();
    }

    /**
     * @param a - coefficient matrix
     * @param l - matrix of measured values
     */
    public LeastSquaresEstimation(double[][] a, double[][] l, List<String> listIdsOfUnknownParameters) {
        A = a;
        L = l;
        int sizeOfWeightMatrix = a.length;
        P = new double[sizeOfWeightMatrix][sizeOfWeightMatrix];
        for (int i = 0; i < sizeOfWeightMatrix; i++) {
            for (int j = 0; j < sizeOfWeightMatrix; j++) {
                P[i][j] = (i == j) ? 1.0d : 0.0d;
            }
        }
        matrix = new Matrix();
        this.listIdsOfUnknownParameters = listIdsOfUnknownParameters;
    }

    /**
     * @param a - coefficient matrix
     * @param l - matrix of measured values
     */
    public LeastSquaresEstimation(double[][] a, double[][] l) {
        A = a;
        L = l;
        int sizeOfWeightMatrix = a.length;
        P = new double[sizeOfWeightMatrix][sizeOfWeightMatrix];
        for (int i = 0; i < sizeOfWeightMatrix; i++) {
            for (int j = 0; j < sizeOfWeightMatrix; j++) {
                P[i][j] = (i == j) ? 1.0d : 0.0d;
            }
        }
        matrix = new Matrix();
    }

    public void executeLeastSquaresEstimation() throws MatrixDegenerateException, MatrixWrongSizeException {
        numberOfFieldObservations = A.length;
        numberOfUnknownParameters = A[0].length;
        numberOfOvernumberObservations = numberOfFieldObservations - numberOfUnknownParameters;
        boolean ifMoreObservationsThanParameters = (numberOfOvernumberObservations) > 0;
        resultsOfLse = new ResultsOfLse(numberOfUnknownParameters, numberOfFieldObservations, printSettings);
        resultsOfLse.setaPrioriStdDeviation(aPrioriStdDeviation);
        resultsOfLse.setListOfDeltaHeightFieldObservations(listOfDeltaHeightFieldObservations);
        resultsOfLse.setListIdsOfUnknownParameters(listIdsOfUnknownParameters);

        calculateX();
        calculateV();

        if (ifMoreObservationsThanParameters) {
            calculateM0();
            calculateCX();
            calculateCAO();
            calculateCV();
        }
        System.out.println(resultsOfLse.toString());
    }

    /**
     * This method is responsible for calculating vector of unknown parameters
     *
     * @throws MatrixWrongSizeException
     * @throws MatrixDegenerateException
     */
    private void calculateX() throws MatrixWrongSizeException, MatrixDegenerateException {
        N = matrix.getMatrixInverse(matrix.getMatrixProduct(matrix.getMatrixProduct(matrix.getMatrixTranspose(A), P), A));
        X = matrix.getMatrixProduct(matrix.getMatrixProduct(matrix.getMatrixProduct(N, matrix.getMatrixTranspose(A)), P), L);
        for (int i = 0; i < X.length; i++) {
            X[i][0] *= -1.0;
        }
        resultsOfLse.setAdjustedParameters(X, 0);

    }

    private void calculateV() throws MatrixWrongSizeException {
        double[][] v = matrix.getMatrixProduct(A, X);
        for (int i = 0; i < v.length; i++) {
            v[i][0] += L[i][0];
        }
        VtPV = matrix.getMatrixProduct(matrix.getMatrixProduct(matrix.getMatrixTranspose(v), P), v);
        resultsOfLse.setWeightedSquareSumOfResiduals(VtPV[0][0]);
        resultsOfLse.setFieldObservationAdjustmentSummary(v);
    }

    private void calculateM0() {
        m0Sqr = VtPV[0][0] / (numberOfOvernumberObservations);
        m0 = Math.sqrt(m0Sqr);
        double ratio = m0 / aPrioriStdDeviation;
        resultsOfLse.setaPosterioriEstimatedStdDeviation(m0);
        resultsOfLse.setRatio(ratio);
    }

    private void calculateCX() {
        double[] mX = new double[numberOfUnknownParameters];
        for (int i = 0; i < X.length; i++) {
            mX[i] = Math.sqrt(m0Sqr * N[i][i]);
        }
        resultsOfLse.setAdjustedParameters(mX, 1);
    }

    /**
     *
     */
    private void calculateCAO() throws MatrixWrongSizeException {
        M = matrix.getMatrixProduct(matrix.getMatrixProduct(A, N), matrix.getMatrixTranspose(A));
        CAO = new double[numberOfFieldObservations];
        for (int i = 0; i < numberOfFieldObservations; i++) {
            CAO[i] = m0 * Math.sqrt(M[i][i]);
        }
        resultsOfLse.setStdErrorsOfAdjustedObservations(CAO);
    }

    private void calculateCV() {
        double[] CV = new double[numberOfFieldObservations];
        for (int i = 0; i < numberOfFieldObservations; i++) {
            CV[i] = aPrioriStdDeviation * Math.sqrt(1.0 / P[i][i] - M[i][i]);
        }
        resultsOfLse.setStdErrorsOfResiduals(CV);
    }

    public ResultsOfLse getResultsOfLse() {
        return resultsOfLse;
    }

    public void setaPrioriStdDeviation(double aPrioriStdDeviation) {
        this.aPrioriStdDeviation = aPrioriStdDeviation;
    }

    public double[][] getX() {
        return X;
    }

    public double getM0() {
        return m0;
    }

    public void setListOfDeltaHeightFieldObservations(List<DeltaHeight> listOfDeltaHeightFieldObservations) {
        this.listOfDeltaHeightFieldObservations = listOfDeltaHeightFieldObservations;
    }

    public void setPrintSettings(PrintSettings printSettings) {
        this.printSettings = printSettings;
    }
}
