package pl.sats.FieldObservationsObjects;

public class Angle {
    private String leftPoint;
    private String centralPoint;
    private String rightPoint;
    private Double angleValue;
    private Double angleStdMeanError;

    public Angle(String leftPoint, String centralPoint, String rightPoint, Double angleValue) {
        this.leftPoint = leftPoint;
        this.centralPoint = centralPoint;
        this.rightPoint = rightPoint;
        this.angleValue = angleValue;
    }

    public Angle(String leftPoint, String centralPoint, String rightPoint, Double angleValue, Double angleStdMeanError) {
        this.leftPoint = leftPoint;
        this.centralPoint = centralPoint;
        this.rightPoint = rightPoint;
        this.angleValue = angleValue;
        this.angleStdMeanError = angleStdMeanError;
    }

    public String getLeftPoint() {
        return leftPoint;
    }

    public String getCentralPoint() {
        return centralPoint;
    }

    public String getRightPoint() {
        return rightPoint;
    }

    public Double getAngleValue() {
        return angleValue;
    }

    public Double getAngleStdMeanError() {
        return angleStdMeanError;
    }
}
