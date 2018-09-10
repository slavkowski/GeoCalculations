package pl.sats;

public class EllipsoidCalculatedParameters {
    private double a;
    private double f;

    private double b;
    private double e;
    private double n;
    private double R;
    private double a2;
    private double a4;
    private double a6;
    private double a8;

    public EllipsoidCalculatedParameters(double a, double f) {
        this.a = a;
        this.f = f;
    }
    public void calculateParameters(){
        b = a*(1.0-(1.0/f));
        e = Math.sqrt(1-(Math.pow(b,2)/Math.pow(a,2)));
        n = (a-b)/(a+b);
        R = (a/(1.0+n))*(1+(Math.pow(n,2)/4+Math.pow(n,4)/64.0+Math.pow(n,6)/256.0)+25*Math.pow(n,8)/16384.0);
        a2 = n/2.0-(2.0/3.0)*Math.pow(n,2)+(5.0/16.0)*Math.pow(n,3)+(41.0/180.0)*Math.pow(n,4);
        a4 = (13.0/48.0)*Math.pow(n,2)-(3.0/5.0)*Math.pow(n,3)+(557.0/1440.0)*Math.pow(n,4);
        a6 = (61.0/240.0)*Math.pow(n,3)-(103.0/140.0)*Math.pow(n,4);
        a8 = (49561.0/161280.0)*Math.pow(n,4);
    }

    public double getB() {
        return b;
    }


    public double getA() {
        return a;
    }

    public double getF() {
        return f;
    }

    public double getE() {
        return e;
    }

    public double getN() {
        return n;
    }

    public double getR() {
        return R;
    }

    public double getA2() {
        return a2;
    }

    public double getA4() {
        return a4;
    }

    public double getA6() {
        return a6;
    }

    public double getA8() {
        return a8;
    }
}
