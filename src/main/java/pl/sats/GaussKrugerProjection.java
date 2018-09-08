package pl.sats;


public class GaussKrugerProjection {



    EllipsoidInfo ellipsoidInfoE = EllipsoidInfo.e;
    EllipsoidInfo ellipsoidInfoA = EllipsoidInfo.a;
    EllipsoidInfo ellipsoidInfoF = EllipsoidInfo.f;
    EllipsoidInfo ellipsoidInfoB = EllipsoidInfo.b;
    EllipsoidInfo ellipsoidInfoN = EllipsoidInfo.n;
    EllipsoidInfo ellipsoidInfoR = EllipsoidInfo.R;
    AngleConverter angleConverter = new AngleConverter();
    private double fi;
    private double lambda;
    private double NorthLambert;
    private double EastLambert;
    double e = ellipsoidInfoE.getWGS84();
    double a = ellipsoidInfoA.getWGS84();
    double f = ellipsoidInfoF.getWGS84();
    double b = ellipsoidInfoB.getWGS84();
    double n = ellipsoidInfoN.getWGS84();
    double R = ellipsoidInfoR.getWGS84();




    public void lagrangeProjection(double B, double L) {
        lambda = L;
        fi = 2.0*(Math.atan(calculateK(B)*Math.tan(B/2.0+Math.PI/4.0))-Math.PI/4.0);
//        System.out.println("b = " + a*(1.0-(1.0/f)));
//        System.out.println("e = " + Math.sqrt(1-(Math.pow(b,2)/Math.pow(a,2))));
//        System.out.println("n = " + (a-b)/(a+b));
//        System.out.println("R = " + (a/(1.0+n))*(1+(Math.pow(n,2)/4+Math.pow(n,4)/64+Math.pow(n,6)/256)+25*Math.pow(n,8)/16384));

    }
    public void mercatorProjection(double fi, double lambda){
        double deltaLambda = lambda - angleConverter.degToRad(21.0);
        NorthLambert = R*Math.atan(Math.tan(fi)/Math.cos(deltaLambda));
        EastLambert = (R/2.0)*Math.log((1+Math.cos(fi)*Math.sin(deltaLambda))/(1-Math.cos(fi)*Math.sin(deltaLambda)));
        System.out.println(NorthLambert);
        System.out.println(EastLambert);

    }







    private double calculateK(double B) {
        return Math.pow((1 - e * Math.sin(B)) / (1 + e * Math.sin(B)), e / 2);

    }

    public double getFi() {
        return fi;
    }

    public double getLambda() {
        return lambda;
    }
    @Override
    public String toString() {
        return "GaussKrugerProjection{" +
                "fi=" + fi +
                ", lambda=" + lambda +
                '}';
    }
}
