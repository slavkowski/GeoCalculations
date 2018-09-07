package pl.sats;

import java.text.Format;

public class AngleConverter {

    public AngleConverter() {
    }
    public double gradToRad(double inputValue) {
        return (inputValue * Math.PI) / 200;
    }
    public double radToGrad(double inputValue) {
        return (200 * inputValue) / (Math.PI);
    }
    public double degToRad(double inputValue) {
        return (inputValue * Math.PI) / 180;
    }
    public double radToDeg(double inputValue) {
        return (180 * inputValue) / (Math.PI);
    }
    public double degToGrad(double inputValue){
        return (200*inputValue)/180;
    }
    public double gradToDeg(double inputValue){
        return (180*inputValue)/200;
    }

    public String degToDeg(double inputValue){
        double inputValueAbs;
        String prefix;

        if(inputValue>=0){
            inputValueAbs = inputValue;
            prefix = "";
        }else{
            inputValueAbs = Math.abs(inputValue);
            prefix = "-";
        }
        double degreesDouble = inputValueAbs;
        String degrees = Integer.toString((int)degreesDouble);
        double minutesDouble = (inputValueAbs-(int)degreesDouble)*60;
        String minutes = Integer.toString((int)minutesDouble);
        double secondsDouble = (minutesDouble-(int)minutesDouble)*60;
        String seconds = Double.toString(secondsDouble);
        return prefix+degrees + "°" + minutes+"′"+seconds+"″";
    }
}
