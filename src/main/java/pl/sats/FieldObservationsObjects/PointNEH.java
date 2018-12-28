package pl.sats.FieldObservationsObjects;

/**
 * This class contains coordinates which uniquely determines the position of the point in
 * 3-dimensional Cartesian coordinate system.
 * <p>
 * Horizontal components:
 * <ul>
 * <li>N - north coordinate</li>
 * <li>mN - north coordinate's mean error</li>
 * <li>E - east coordinate</li>
 * <li>mE - east coordinate's mean error</li>
 * </ul>
 * <p>
 * Vertical component:
 * <ul>
 * <li>H - height(vertical) coordinate</li>
 * <li>mH - height(vertical) coordinate's mean error</li>
 * </ul>
 *
 * @author Slawomir Szwed
 * @version 1.0
 * @since 2018-12-15
 */
public class PointNEH {
    private String name;
    private double N;
    private double mN;
    private double E;
    private double mE;
    private double H;
    private double mH;

    public PointNEH() {
    }

    public PointNEH(String name, double n, double e) {
        this.name = name;
        N = n;
        E = e;
    }

    public PointNEH(String name, double h) {
        this.name = name;
        H = h;
    }

    public PointNEH(String name, double n, double e, double h) {
        this.name = name;
        N = n;
        E = e;
        H = h;
    }

    @Override
    public String toString() {
        return "PointNEH{" +
                "name='" + name + '\'' +
                ", N=" + N +
                ", mN=" + mN +
                ", E=" + E +
                ", mE=" + mE +
                ", H=" + H +
                ", mH=" + mH +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getN() {
        return N;
    }

    public void setN(double n) {
        N = n;
    }

    public double getmN() {
        return mN;
    }

    public void setmN(double mN) {
        this.mN = mN;
    }

    public double getE() {
        return E;
    }

    public void setE(double e) {
        E = e;
    }

    public double getmE() {
        return mE;
    }

    public void setmE(double mE) {
        this.mE = mE;
    }

    public double getH() {
        return H;
    }

    public void setH(double h) {
        H = h;
    }

    public double getmH() {
        return mH;
    }

    public void setmH(double mH) {
        this.mH = mH;
    }
}
