package pl.sats.CurveCalculations;


import pl.sats.Exceptions.MatrixDegenerateException;
import pl.sats.Exceptions.MatrixWrongSizeException;
import pl.sats.FieldObservationsObjects.PointLDH;
import pl.sats.LSEstimations.LeastSquaresEstimation;

import java.util.List;

/**
 *
 */
public class Catenary {
    private double a;
    private double l;
    private double h;
    private double minH;
    private double LminH;
    private double firstL;
    private double firstH;
    private double lastL;
    private double lastH;
    private double horizontalLength;
    private double minHeight;
    private double minHeightL;
    private double maxHeight;
    private String fieldObservationsString;
    private List<PointLDH> fieldObservations;

    public Catenary(List<PointLDH> fieldObservations) {
        this.fieldObservations = fieldObservations;
    }

    public void calculateCatenary() throws MatrixDegenerateException, MatrixWrongSizeException {
        Parabola parabola = new Parabola(fieldObservations);
        parabola.getParabolaParameters();

        double a0 = parabola.getaCatenary();
        double l0 = parabola.getlCatenary();
        double h0 = parabola.gethCatenary();

        double[][] AParabola = parabola.getA();
        double[][] LParabola = parabola.getL();
        double[][] PParabola = parabola.getP();
        int numberOfObservations = parabola.getNumberOfObservations();
        double[][] A = new double[numberOfObservations][3];
        double[][] L = new double[numberOfObservations][1];
        double[][] X;
        int k = 0;

        while (k < 10) {

            for (int i = 0; i < numberOfObservations; i++) {
                double alfa = (AParabola[i][1] + l0) / a0;
                A[i][0] = Math.sinh(alfa);
                A[i][1] = -1.0d;
                A[i][2] = Math.cosh(alfa) - alfa * Math.sinh(alfa);
                L[i][0] = -1.0 * (a0 * Math.cosh(alfa) - h0 - LParabola[i][0]);
            }
            LeastSquaresEstimation leastSquaresEstimation = new LeastSquaresEstimation(A, PParabola, L);
            leastSquaresEstimation.executeLeastSquaresEstimation();
            X = leastSquaresEstimation.getX();
            a0 = a0 - X[2][0];
            l0 = l0 - X[0][0];
            h0 = h0 - X[1][0];
            k++;
        }
        a = a0;
        l = l0;
        h = h0;

        firstL = fieldObservations.get(0).getL();
        firstH = fieldObservations.get(0).getH();
        lastL = fieldObservations.get(fieldObservations.size() - 1).getL();
        lastH = fieldObservations.get(fieldObservations.size() - 1).getH();
        horizontalLength = lastL - firstL;

        minH = a - h;
        LminH = -l;

        maxHeight = (firstH >= lastH) ? firstH : lastH;
        minHeight = (firstL < LminH && LminH < lastL) ? minH : Math.min(firstH, lastH);
        minHeightL = (firstL < LminH && LminH < lastL) ? LminH : (firstH < lastH) ? firstL : lastL;

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (PointLDH ldh : fieldObservations) {
            sb.append("[").append(ldh.getL()-firstL).append(",").append(ldh.getH()).append("],");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");

        fieldObservationsString = sb.toString();

    }

    public double getA() {
        return a;
    }

    public double getL() {
        return l;
    }

    public double getH() {
        return h;
    }

    public double getMinH() {
        return minH;
    }

    public double getLminH() {
        return LminH;
    }

    public double getFirstL() {
        return firstL;
    }

    public double getFirstH() {
        return firstH;
    }

    public double getLastL() {
        return lastL;
    }

    public double getLastH() {
        return lastH;
    }

    public double getHorizontalLength() {
        return horizontalLength;
    }

    public double getMinHeight() {
        return minHeight;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

    public List<PointLDH> getFieldObservations() {
        return fieldObservations;
    }

    public String getFieldObservationsString() {
        return fieldObservationsString;
    }

    public double getMinHeightL() {
        return minHeightL;
    }
}
