package pl.sgeonet.LineCalculations;

import pl.sgeonet.BasicGeoCalculations.AzimuthDistanceCalculation;
import pl.sgeonet.Exceptions.MatrixDegenerateException;
import pl.sgeonet.Exceptions.MatrixWrongSizeException;
import pl.sgeonet.FieldObservationsObjects.PointCoordinates.LHD;
import pl.sgeonet.FieldObservationsObjects.PointCoordinates.NEH;
import pl.sgeonet.LSEstimations.LeastSquaresEstimation;

import java.util.ArrayList;
import java.util.List;

public class PointToLineProjection {

    public List<LHD> getLHD(List<NEH> xyhList) throws MatrixDegenerateException, MatrixWrongSizeException {
        List<LHD> lhList = new ArrayList<>();
        int lengthOfList = xyhList.size();
        NEH firstPoint = xyhList.get(0);
        NEH lastPoint = xyhList.get(lengthOfList - 1);
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

        int id = 1;
        for (NEH aXyhList : xyhList) {
            L[0][0] = -(aXyhList.getE() - firstPoint.getE());
            L[1][0] = -(aXyhList.getN() - firstPoint.getN());
            leastSquaresEstimation = new LeastSquaresEstimation(A, L);
            leastSquaresEstimation.executeLeastSquaresEstimation();
            resultLD = leastSquaresEstimation.getX();
            LHD LHD = new LHD("lp" + id, resultLD[0][0], aXyhList.getH(), resultLD[1][0]);
            lhList.add(LHD);
            id++;
        }
        return lhList;
    }
}
