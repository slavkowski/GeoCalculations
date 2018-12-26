package pl.sats;

/**
 * This class is responsible for converting azimuth between different units
 *
 * @author Slawomir Szwed
 * @version 1.0
 * @since 2018-12-15
 */
public class AngleConverter {

    public AngleConverter() {
    }

    /**
     * Grad to Rad converter
     *
     * @param inputValue given azimuth in Grad
     * @return double - azimuth in Rad
     */
    public double gradToRad(double inputValue) {
        return (inputValue * Math.PI) / 200;
    }

    /**
     * Rad to Grad converter
     *
     * @param inputValue given azimuth in Rad
     * @return double - azimuth in Grad
     */
    public double radToGrad(double inputValue) {
        return (200 * inputValue) / (Math.PI);
    }

    public double degToRad(double inputValue) {
        return (inputValue * Math.PI) / 180;
    }

    public double radToDeg(double inputValue) {
        return (180 * inputValue) / (Math.PI);
    }

    public double degToGrad(double inputValue) {
        return (200 * inputValue) / 180;
    }

    public double gradToDeg(double inputValue) {
        return (180 * inputValue) / 200;
    }

    /**
     * This method is responsible for converting azimuth in DEG format (Decimal Degrees) into DMS string format
     * @param inputValue azimuth in DEG format (Decimal Degrees - example: 111.432175448)
     * @return String DMS String representation of input value
     */
    public String degToDeg(double inputValue) {
        double inputValueAbs;
        String prefix;

        if (inputValue >= 0) {
            inputValueAbs = inputValue;
            prefix = "";
        } else {
            inputValueAbs = Math.abs(inputValue);
            prefix = "-";
        }
        double degreesDouble = inputValueAbs;
        String degrees = Integer.toString((int) degreesDouble);
        double minutesDouble = (inputValueAbs - (int) degreesDouble) * 60;
        String minutes = Integer.toString((int) minutesDouble);
        double secondsDouble = (minutesDouble - (int) minutesDouble) * 60;
        String seconds = Double.toString(secondsDouble);
        return prefix + degrees + "°" + minutes + "′" + seconds + "″";
    }
}
