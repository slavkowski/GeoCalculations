package pl.sats;


public class GaussKrugerProjection {



    EllipsoidInfo ellipsoidInfo = EllipsoidInfo.e;
    private double fi;
    private double lambda;
    double e = ellipsoidInfo.getWGS84();



    public void LagrangeProjection(double B, double L) {
        lambda = L;
        fi = 2*(Math.atan(calculateK(B)*Math.tan(B/2+Math.PI/4))-Math.PI/4);
        System.out.println(calculateK(B));
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
