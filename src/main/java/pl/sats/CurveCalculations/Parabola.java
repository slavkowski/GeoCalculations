package pl.sats.CurveCalculations;

import pl.sats.FieldObservationsObjects.Chain_LH;
import pl.sats.FileUtils;
import pl.sats.Matrix;
import pl.sats.RMSEstimations.DX;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parabola {
    private double p;
    private double q;
    private double h;
    private double bCatenary;
    private double aCatenary;
    private double lCatenary;
    private double hCatenary;
    private int numberOfObservations;
    private File file;
    private double L[][];
    private double A[][];
    private double P[][];

    public double[][] getL() {
        return L;
    }

    public double[][] getA() {
        return A;
    }

    public double[][] getP() {
        return P;
    }

    public int getNumberOfObservations() {
        return numberOfObservations;
    }

    public double getaCatenary() {
        return aCatenary;
    }

    public double getlCatenary() {
        return lCatenary;
    }

    public double gethCatenary() {
        return hCatenary;
    }

    private List<Chain_LH> observations = new ArrayList<>();

    public Parabola(File file) {
        this.file = file;
    }

    void getParabolaParameters() {
        observations = getDataFromFile(file);
        numberOfObservations = observations.size();
        A = new double[numberOfObservations][3];
        P = new double[numberOfObservations][numberOfObservations];
        for (int i = 0; i < numberOfObservations; i++) {
            for (int j = 0; j < numberOfObservations; j++) {
                if(i!=j){
                    P[i][j]=0.0d;
                }else
                    P[i][j]=1.0d;
            }
        }
        L = new double[numberOfObservations][1];

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
        double a = results[0][0];
        double b = results[1][0];
        double c = results[2][0];
        double delta = Math.pow(b, 2) - 4.0 * a * c;
        p = -b /(2.0* a);
        q = -delta /(4.0* a);
        if (L[0][0] >= L[L.length-1][0]) {
            h = L[0][0] - q;
            bCatenary = Math.abs(p-A[0][1]);
        }else{
            h = L[L.length-1][0] - q;
            bCatenary = Math.abs(p-A[A.length-1][1]);
        }
        aCatenary = Math.pow(bCatenary,2)/(2.0*h);
        lCatenary = -p;
        hCatenary = aCatenary - q;
        System.out.println(aCatenary);
        System.out.println(lCatenary);
        System.out.println(hCatenary);
        System.out.println(dx.getM0(results));
    }

    private List getDataFromFile(File file) {
        FileUtils f = new FileUtils();
        try {
            observations = f.readFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return observations;
    }

}
