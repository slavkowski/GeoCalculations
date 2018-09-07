package pl.sats;

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
}
