package pl.sats;

import java.lang.*;

public class Matrix {
    //*********************************************************************************************************************
//odwracanie macierezy - metoda z osiwaniem i permutacjami wierszy i kolumn w macierzy
    public double[][] MatrixI(double A[][]){

        int s = A.length;
        int q = 0;
        int p = 0;
        int[] C = new int[s];
        boolean[] O = new boolean[s];

        //osiowanie macierzy
        for (int i = 0; i < s ; i++) {

            double max=0;
            for (int j = 0; j < s; j++) {
                if (!O[j]) {
                    if (max < Math.abs(A[i][j])) {
                        max = A[i][j];
                        q = j;
                        p = i;
                    }
                }
            }
            if (max==0){
                System.out.println("Wiersz " + i + " jest zerowy");
                System.out.println("Macierz jest osobliwa");
                break;
            }
            C[i] = q;
            O[q] = true;

            for (int w1 = 0; w1 < s ; w1++) {
                for (int k1 = 0; k1 < s; k1++) {
                    if (w1 != p && k1 != q) {
                        A[w1][k1] = A[w1][k1] - A[p][k1] * A[w1][q] / A[p][q];
                    }
                }
            }
            for (int w1 = 0; w1 <s ; w1++) {
                if (w1!=p){
                    A[w1][q] = -A[w1][q]/A[p][q];
                }

            }
            for (int k1 = 0; k1 < s; k1++) {
                if (k1!=q){
                    A[p][k1] = A[p][k1]/A[p][q];
                }
            }
            A[p][q] = 1/A[p][q];
        }

        //permutacje wierszy i kolumn wykorzystując macierz pomocniczą
        double[][] Apom = new double[s][s];

        for (int w1 = 0; w1 < s ; w1++) {
            for (int k1 = 0; k1 < s; k1++) {
                Apom[w1][k1] = A[w1][k1];
            }
        }
        for (int w2=0;w2<s;w2++) {
            for (int k2=0; k2<s;k2++) {
                A[C[w2]][k2] = Apom[w2][k2];
            }
        }
        for (int w1 = 0; w1 < s ; w1++) {
            for (int k1 = 0; k1 < s; k1++) {
                Apom[w1][k1] = A[w1][k1];
            }
        }
        for (int w2=0;w2<s;w2++) {
            for (int k2=0; k2<s;k2++) {
                A[k2][w2] = Apom[k2][C[w2]];
            }
        }
        return A;
    }

    //*********************************************************************************************************************
//transpozycja macierzy
    public double [][] MatrixT(double A[][]) {
        double[][] B = new double[A[0].length][A.length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                B[j][i] = A[i][j];
            }
        }
        return B;
    }
    //********************************************************************************************************************
    //tworze metode MatrixM(mnozenie macierzy)
    //metoda zwraca tablice double[][] a jej parametry/argumenty to tablice wejsciowe A i B, ktore bede podawal w main
    public double[][] MatrixM(double A[][], double B[][]) {


        //ten wiersz podpowiedział mi program
        //domyslam sie ze chodzi tutaj ze metoda MatrixM musi zwracac double [][]
        //a w ciele metody jest tylko instrukcja warunkowa IF
        double[][] AB = new double[0][];

        //warunek sprawdzajacy rozmiar macierzy
        if (A[0].length == B.length) {


            //utowrzenie pustej macierzy wynikowej AB
            AB = new double[A.length][B[0].length];

            //mnozenie
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    double suma = 0;
                    for (int k = 0; k < A[0].length; k++) {
                        suma += A[i][k] * B[k][j];
                    }
                    AB[i][j] = suma;
                }
            }
        }
        else
        {//warunek złych rozmiarów. Na razie jest w takiej roboczej formie
            System.out.println("Bad size");
        }
        return AB;
    }
//*********************************************************************************************************************
}



