package pl.sats.CurveCalculations;

import pl.sats.FieldObservationsObjects.Chain_LH;
import pl.sats.FileUtils;
import pl.sats.Matrix;
import pl.sats.RMSEstimations.DX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parabola {
    private double a;
    private double b;
    private double c;
    private double delta;
    private double p;
    private double q;
    private List<Chain_LH> observations = new ArrayList<>();

    public Parabola() {
    }

    public void getParabolaParameters() {
        observations = getDataFromFile();
        int numberOfObservations = observations.size();
        double A[][] = new double[numberOfObservations][3];
        double P[][] = new double[numberOfObservations][numberOfObservations];
        for (int i = 0; i < numberOfObservations; i++) {
            for (int j = 0; j < numberOfObservations; j++) {
                if(i!=j){
                    P[i][j]=0.0d;
                }else
                    P[i][j]=1.0d;
            }

        }
        double L[][] = new double[numberOfObservations][1];

        int i = 0;
        for (Chain_LH list : observations) {
            A[i][0] = Math.pow(list.getL(), 2);
            A[i][1] = list.getL();
            A[i][2] = 1.0d;
            L[i][0] = list.getH();
            i++;
        }
        DX dx = new DX(A, P, L);
        double[][] results = dx.getDX();
        a = results[0][0];
        b = results[1][0];
        c = results[2][0];

        delta = Math.pow(b,2)-4.0*a*c;
        p = -b/(2.0*a);
        q = -delta/(4.0*a);


        System.out.println(results[0][0]);
        System.out.println(results[1][0]);
        System.out.println(results[2][0]);
        System.out.println(delta);
        System.out.println(p);
        System.out.println(q);
        System.out.println(dx.getM0(results));


    }

    private List getDataFromFile() {
        FileUtils f = new FileUtils();
        try {
            observations = f.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return observations;
    }

}
