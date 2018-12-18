package pl.sats.FieldObservationsObjects;

/**
 * This class contains components which describe point
 * after orthogonal projection on line <b>l</b> created by two points (P<sub>start</sub> and P<sub>end</sub>)
 * Let's consider virtual point P<sup>'</sup> which is orthogonal projected point P<sub>i</sub> onto line <b>l</b>.
 * Then we get:
 * <ul>
 * <li>L - distance between P<sub>start</sub> and P<sup>'</sup>.</li>
 * <li>D - distance between P<sup>'</sup> and P<sub>i</sub>.</li>
 * <li>H - height of the point. Directly from XYH format.</li>
 * </ul>
 *
 * @author Slawomir Szwed
 * @version 1.0
 * @since 2018-12-15
 */
public class LDH {
    private String name;
    private double L;
    private double mL;
    private double H;
    private double mH;
    private double D;
    private double mD;

    public LDH() {
    }

    public LDH(double l, double h, double d) {
        L = l;
        H = h;
        D = d;
    }

    @Override
    public String toString() {
        return "LDH{" +
                "name='" + name + '\'' +
                ", L=" + L +
                ", mL=" + mL +
                ", H=" + H +
                ", mH=" + mH +
                ", D=" + D +
                ", mD=" + mD +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getL() {
        return L;
    }

    public void setL(double l) {
        L = l;
    }

    public double getmL() {
        return mL;
    }

    public void setmL(double mL) {
        this.mL = mL;
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

    public double getD() {
        return D;
    }

    public void setD(double d) {
        D = d;
    }

    public double getmD() {
        return mD;
    }

    public void setmD(double mD) {
        this.mD = mD;
    }
}
