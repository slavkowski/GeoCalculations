package pl.sats;


public class GaussKrugerProjection {
    double a;
    double f;
    private double fi;
    private double lambda;
    private double nMerk;
    private double eMerk;
    private double nGK;
    private double eGK;
    private double e;
    private double R;
    private double a2;
    private double a4;
    private double a6;
    private double a8;

    public GaussKrugerProjection() {
        EllipsoidInfo ellipsoidInfoA = EllipsoidInfo.a;
        EllipsoidInfo ellipsoidInfoF = EllipsoidInfo.f;
        a = ellipsoidInfoA.getWGS84();
        f = ellipsoidInfoF.getWGS84();
        EllipsoidCalculatedParameters ellipsoidCalculatedParameters = new EllipsoidCalculatedParameters(a, f);
        ellipsoidCalculatedParameters.calculateParameters();
        e = ellipsoidCalculatedParameters.getE();
        R = ellipsoidCalculatedParameters.getR();
        a2 = ellipsoidCalculatedParameters.getA2();
        a4 = ellipsoidCalculatedParameters.getA4();
        a6 = ellipsoidCalculatedParameters.getA6();
        a8 = ellipsoidCalculatedParameters.getA8();
    }
    AngleConverter angleConverter = new AngleConverter();

    public void getUTM(double B, double L) {
        lagrangeProjection(B, L);
        mercatorPlane();
        gaussKrugerPlane();
    }


    private void lagrangeProjection(double B, double L) {
        lambda = L;
        fi = 2.0 * (Math.atan(calculateK(B) * Math.tan(B / 2.0 + Math.PI / 4.0)) - Math.PI / 4.0);
    }

    private void mercatorPlane() {
        double deltaLambda = lambda - angleConverter.degToRad(21.0);
        nMerk = R * Math.atan(Math.tan(fi) / Math.cos(deltaLambda));
        eMerk = (R / 2.0) * Math.log((1 + Math.cos(fi) * Math.sin(deltaLambda)) / (1 - Math.cos(fi) * Math.sin(deltaLambda)));
    }

    private void gaussKrugerPlane() {
        double alfa = nMerk / R;
        double beta = eMerk / R;
        nGK = nMerk + R*(a2*Math.sin(2.0*alfa)*Math.cosh(2.0*beta)+a4*Math.sin(4.0*alfa)*Math.cosh(4.0*beta)
                +a6*Math.sin(6.0*alfa)*Math.cosh(6.0*beta)+a8*Math.sin(8*alfa)*Math.cosh(8*beta));
        eGK = eMerk + R*(a2*Math.cos(2.0*alfa)*Math.sinh(2.0*beta)+a4*Math.cos(4.0*alfa)*Math.sinh(4.0*beta)
                +a6*Math.cos(6.0*alfa)*Math.sinh(6.0*beta)+a8*Math.cos(8*alfa)*Math.sinh(8*beta));
    }


    private double calculateK(double B) {
        return Math.pow((1.0 - e * Math.sin(B)) / (1.0 + e * Math.sin(B)), e / 2.0);
    }


}
