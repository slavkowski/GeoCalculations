package pl.sats.CurveCalculations;

import pl.sats.FieldObservationsObjects.LHD;
import pl.sats.LSEstimations.LeastSquaresEstimation;

import java.util.List;

/**
 *
 */
class Parabola {
    private double p;
    private double q;
    private double h;
    private double bCatenary;
    private double aCatenary;
    private double lCatenary;
    private double hCatenary;
    private int numberOfObservations;
    private double L[][];
    private double A[][];
    private double P[][];
    private List<LHD> fieldObservations;

    Parabola(List<LHD> fieldObservations) {
        this.fieldObservations = fieldObservations;
    }

    /**
     *
     */
    void getParabolaParameters() {
        numberOfObservations = fieldObservations.size();
        A = new double[numberOfObservations][3];
        P = new double[numberOfObservations][numberOfObservations];
        for (int i = 0; i < numberOfObservations; i++) {
            for (int j = 0; j < numberOfObservations; j++) {
                if (i != j) {
                    P[i][j] = 0.0d;
                } else
                    P[i][j] = 1.0d;
            }
        }
        L = new double[numberOfObservations][1];

        int i = 0;
        for (LHD list : fieldObservations) {
            A[i][0] = Math.pow(list.getL(), 2);
            A[i][1] = list.getL();
            A[i][2] = 1.0d;
            L[i][0] = list.getH();
            i++;
        }
        LeastSquaresEstimation dx = new LeastSquaresEstimation(A, P, L);
        double[][] results = dx.getX();
        double a = results[0][0];
        double b = results[1][0];
        double c = results[2][0];
        double delta = Math.pow(b, 2) - 4.0 * a * c;
        p = -b / (2.0 * a);
        q = -delta / (4.0 * a);

        if (L[0][0] >= L[L.length - 1][0]) {
            h = L[0][0] - q;
            bCatenary = Math.abs(p - A[0][1]);
        } else {
            h = L[L.length - 1][0] - q;
            bCatenary = Math.abs(p - A[A.length - 1][1]);
        }
        aCatenary = Math.pow(bCatenary, 2) / (2.0 * h);
        lCatenary = -p;
        hCatenary = aCatenary - q;
    }

    double[][] getL() {
        return L;
    }

    double[][] getA() {
        return A;
    }

    double[][] getP() {
        return P;
    }

    int getNumberOfObservations() {
        return numberOfObservations;
    }

    double getaCatenary() {
        return aCatenary;
    }

    double getlCatenary() {
        return lCatenary;
    }

    double gethCatenary() {
        return hCatenary;
    }

    double getMinH() {
        return q;
    }

    double getLMinH() {
        return p;
    }


}
