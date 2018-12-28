package pl.sats.FieldObservationsObjects;

public class Distance {
    private String pointFrom;
    private String pointTo;
    private Double distanceValue;
    private Double distanceStdMeanError;

    public Distance(String pointFrom, String pointTo, Double distanceValue) {
        this.pointFrom = pointFrom;
        this.pointTo = pointTo;
        this.distanceValue = distanceValue;
    }

    public Distance(String pointFrom, String pointTo, Double distanceValue, Double distanceStdMeanError) {
        this.pointFrom = pointFrom;
        this.pointTo = pointTo;
        this.distanceValue = distanceValue;
        this.distanceStdMeanError = distanceStdMeanError;
    }

    public String getPointFrom() {
        return pointFrom;
    }

    public String getPointTo() {
        return pointTo;
    }

    public Double getDistanceValue() {
        return distanceValue;
    }

    public Double getDistanceStdMeanError() {
        return distanceStdMeanError;
    }
}
