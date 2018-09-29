package pl.sats.CurveCalculations;

public class Catenary {
    private double hApprox;
    private double lApprox;
    private double aApprox;

    public void calculateApproxValues(){

        hApprox = 1.0d;
        lApprox = 1.0d;
        aApprox = Math.pow(150.0,2)/(2.0*10.0);

    }

}
