package pl.sats.LineCalculations;

import pl.sats.BasicGeoCalculations.AzimuthDistanceCalculation;
import pl.sats.Exceptions.MatrixDegenerateException;
import pl.sats.Exceptions.MatrixWrongSizeException;
import pl.sats.FieldObservationsObjects.PointLDH;
import pl.sats.FieldObservationsObjects.PointNEH;
import pl.sats.LSEstimations.LeastSquaresEstimation;

import java.util.ArrayList;
import java.util.List;

public class PointToLineProjection {

    public List<PointLDH> getLHD(List<PointNEH> xyhList) throws MatrixDegenerateException, MatrixWrongSizeException {
        List<PointLDH> lhList = new ArrayList<>();
        int lengthOfList = xyhList.size();
        PointNEH firstPoint = xyhList.get(0);
        PointNEH lastPoint = xyhList.get(lengthOfList - 1);
        double[][] L = new double[2][1];
        double[][] A = new double[2][2];
        double[][] P = new double[2][2];
        double[][] resultLD;

        AzimuthDistanceCalculation azimuthDistanceCalculation = new AzimuthDistanceCalculation();
        double Az = azimuthDistanceCalculation.calculateAzimuth(firstPoint.getN(), firstPoint.getE(), lastPoint.getN(), lastPoint.getE());

        A[0][0] = Math.sin(Az);
        A[1][0] = Math.cos(Az);
        A[0][1] = Math.cos(Az);
        A[1][1] = -Math.sin(Az);

        LeastSquaresEstimation leastSquaresEstimation;

        for (PointNEH aXyhList : xyhList) {
            L[0][0] = -(aXyhList.getE() - firstPoint.getE());
            L[1][0] = -(aXyhList.getN() - firstPoint.getN());
            leastSquaresEstimation = new LeastSquaresEstimation(A, L);
            leastSquaresEstimation.executeLeastSquaresEstimation();
            resultLD = leastSquaresEstimation.getX();
            PointLDH LDH = new PointLDH(resultLD[0][0], aXyhList.getH(), resultLD[1][0]);
            lhList.add(LDH);
        }
        return lhList;
    }
}
