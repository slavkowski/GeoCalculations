package pl.sats.FieldObservationsObjects;

public class DeltaHeight {
    private String pointFrom;
    private String pointTo;
    private double heightDifferenceValue;
    private double heightDifferenceStdMeanError;
    private int numberOfSetupsInSection;
    private double lengthOfSection;

    public void setPointFrom(String pointFrom) {
        this.pointFrom = pointFrom;
    }

    public void setPointTo(String pointTo) {
        this.pointTo = pointTo;
    }

    public void setHeightDifferenceValue(double heightDifferenceValue) {
        this.heightDifferenceValue = heightDifferenceValue;
    }

    public void setHeightDifferenceStdMeanError(double heightDifferenceStdMeanError) {
        this.heightDifferenceStdMeanError = heightDifferenceStdMeanError;
    }

    public void setNumberOfSetupsInSection(int numberOfSetupsInSection) {
        this.numberOfSetupsInSection = numberOfSetupsInSection;
    }

    public void setLengthOfSection(double lengthOfSection) {
        this.lengthOfSection = lengthOfSection;
    }

    public String getPointFrom() {
        return pointFrom;
    }

    public String getPointTo() {
        return pointTo;
    }

    public double getHeightDifferenceValue() {
        return heightDifferenceValue;
    }

    public double getHeightDifferenceStdMeanError() {
        return heightDifferenceStdMeanError;
    }

    public int getNumberOfSetupsInSection() {
        return numberOfSetupsInSection;
    }

    public double getLengthOfSection() {
        return lengthOfSection;
    }
}
