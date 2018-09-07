package pl.sats;

public class Main {
    public static void main(String[] args) {
        AngleConverter angleConverter = new AngleConverter();

        double B = angleConverter.degToRad(54.0 + 50.0 / 60.0);
        double L = angleConverter.degToRad(18.0 + 30.0 / 60.0);

        GaussKrugerProjection gaussKrugerProjection = new GaussKrugerProjection();
        gaussKrugerProjection.LagrangeProjection(B,L);
        System.out.println(angleConverter.degToDeg(angleConverter.radToDeg(gaussKrugerProjection.getFi())));
        System.out.println(angleConverter.degToDeg(angleConverter.radToDeg(gaussKrugerProjection.getLambda())));


    }
}
