package pl.sats.FieldObservationsObjects.PointCoordinates;

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
public class LHD extends Point{
    private double L;
    private double mL;
    private double H;
    private double mH;
    private double D;
    private double mD;

    public LHD(){

    }

    public LHD(String id, double l, double h, double d) {
        super(id);
        L = l;
        H = h;
        D = d;
    }

    public LHD(String id, double l, double mL, double h, double mH, double d, double mD) {
        super(id);
        L = l;
        this.mL = mL;
        H = h;
        this.mH = mH;
        D = d;
        this.mD = mD;
    }

    public double getL() {
        return L;
    }

    public double getmL() {
        return mL;
    }

    public double getH() {
        return H;
    }

    public double getmH() {
        return mH;
    }

    public double getD() {
        return D;
    }

    public double getmD() {
        return mD;
    }
}
