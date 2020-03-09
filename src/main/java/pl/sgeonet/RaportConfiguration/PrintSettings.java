package pl.sgeonet.RaportConfiguration;

public class PrintSettings {
    //Settings for displaying calculated unknown parameters
    //X
    private Unit unitOfCalculatedParameters = Unit.M;
    private NumberOfDecimalDigits numberOfDecimalDigitsOfCalculatedParameters = NumberOfDecimalDigits.THREE;
    private double ratioCalculatedParameters = 1.0;
    //mX
    private Unit unitOfCalculatedStdErrorOfCalculatedParameters = Unit.MM;
    private NumberOfDecimalDigits numberOfDecimalDigitsOfStdErrorOfCalculatedParameters = NumberOfDecimalDigits.ONE;
    private double ratioStdErrorOfCalculatedParameters = 1.0;
    //Settings for displaying given parameters
    //dH
    private Unit unitOfHeightObservations = Unit.M;
    private NumberOfDecimalDigits numberOfDecimalDigitsOfHeightObservations = NumberOfDecimalDigits.THREE;
    private double ratioHeightObservations = 1.0;
    //mdH
    private Unit unitStdErrorOfHeightObservations = Unit.MM;
    private NumberOfDecimalDigits numberOfDecimalDigitsOfStdErrorHeightObservations = NumberOfDecimalDigits.ONE;
    private double ratioStdErrorHeightObservations = 1.0;
    //Settings for displaying adjusted observations [residuals]
    //V
    private Unit unitOfResiduals = Unit.MM;
    private NumberOfDecimalDigits numberOfDecimalDigitsOfResiduals = NumberOfDecimalDigits.TWO;
    private double ratioResiduals = 1.0;
    //mV
    private Unit unitStdErrorOfResiduals = Unit.MM;
    private NumberOfDecimalDigits numberOfDecimalDigitsOfStdErrorResiduals = NumberOfDecimalDigits.TWO;
    private double ratioStdErrorOfResiduals = 1.0;
    //dHA
    private Unit unitOfAdjustedHeightObservations = Unit.M;
    private NumberOfDecimalDigits numberOfDecimalDigitsOfAdjustedHeightObservations = NumberOfDecimalDigits.THREE;
    private double ratioAdjustedHeightObservations = 1.0;
    //mdHA
    private Unit unitStdErrorOfAdjustedHeightObservations = Unit.MM;
    private NumberOfDecimalDigits numberOfDecimalDigitsOfStdErrorAdjustedHeightObservations = NumberOfDecimalDigits.TWO;
    private double ratioStdErrorAdjustedHeightObservations = 1.0;



    public PrintSettings() {
        calculatePrintSettingsParameters();
    }

    private void calculatePrintSettingsParameters() {
        switch (unitOfCalculatedParameters){
            case CM:
                ratioCalculatedParameters = 100.0;
                break;
            case MM:
                ratioCalculatedParameters = 1000.0;
                break;
            case KM:
                ratioCalculatedParameters = 0.001;
                break;
        }
        switch (unitOfCalculatedStdErrorOfCalculatedParameters){
            case CM:
                ratioStdErrorOfCalculatedParameters = 100.0;
                break;
            case MM:
                ratioStdErrorOfCalculatedParameters = 1000.0;
                break;
            case KM:
                ratioStdErrorOfCalculatedParameters = 0.001;
                break;
        }
        switch (unitOfHeightObservations){
            case CM:
                ratioHeightObservations = 100.0;
                break;
            case MM:
                ratioHeightObservations = 1000.0;
                break;
            case KM:
                ratioHeightObservations = 0.001;
                break;
        }
        switch (unitStdErrorOfHeightObservations){
            case CM:
                ratioStdErrorHeightObservations = 100.0;
                break;
            case MM:
                ratioStdErrorHeightObservations = 1000.0;
                break;
            case KM:
                ratioStdErrorHeightObservations = 0.001;
                break;
        }
        switch (unitOfResiduals){
            case CM:
                ratioResiduals = 100.0;
                break;
            case MM:
                ratioResiduals = 1000.0;
                break;
            case KM:
                ratioResiduals = 0.001;
                break;
        }
        switch (unitStdErrorOfResiduals){
            case CM:
                ratioStdErrorOfResiduals = 100.0;
                break;
            case MM:
                ratioStdErrorOfResiduals = 1000.0;
                break;
            case KM:
                ratioStdErrorOfResiduals = 0.001;
                break;
        }
        switch (unitOfAdjustedHeightObservations){
            case CM:
                ratioAdjustedHeightObservations = 100.0;
                break;
            case MM:
                ratioAdjustedHeightObservations = 1000.0;
                break;
            case KM:
                ratioAdjustedHeightObservations = 0.001;
                break;
        }
        switch (unitStdErrorOfAdjustedHeightObservations){
            case CM:
                ratioStdErrorAdjustedHeightObservations = 100.0;
                break;
            case MM:
                ratioStdErrorAdjustedHeightObservations = 1000.0;
                break;
            case KM:
                ratioStdErrorAdjustedHeightObservations = 0.001;
                break;
        }
    }

    public NumberOfDecimalDigits getNumberOfDecimalDigitsOfCalculatedParameters() {
        return numberOfDecimalDigitsOfCalculatedParameters;
    }

    public Unit getUnitOfCalculatedParameters() {
        return unitOfCalculatedParameters;
    }

    public NumberOfDecimalDigits getNumberOfDecimalDigitsOfStdErrorOfCalculatedParameters() {
        return numberOfDecimalDigitsOfStdErrorOfCalculatedParameters;
    }

    public double getRatioCalculatedParameters() {
        return ratioCalculatedParameters;
    }

    public double getRatioStdErrorOfCalculatedParameters() {
        return ratioStdErrorOfCalculatedParameters;
    }

    public Unit getUnitOfHeightObservations() {
        return unitOfHeightObservations;
    }

    public NumberOfDecimalDigits getNumberOfDecimalDigitsOfHeightObservations() {
        return numberOfDecimalDigitsOfHeightObservations;
    }

    public double getRatioHeightObservations() {
        return ratioHeightObservations;
    }

    public Unit getUnitOfCalculatedStdErrorOfCalculatedParameters() {
        return unitOfCalculatedStdErrorOfCalculatedParameters;
    }

    public Unit getUnitStdErrorOfHeightObservations() {
        return unitStdErrorOfHeightObservations;
    }

    public NumberOfDecimalDigits getNumberOfDecimalDigitsOfStdErrorHeightObservations() {
        return numberOfDecimalDigitsOfStdErrorHeightObservations;
    }

    public double getRatioStdErrorHeightObservations() {
        return ratioStdErrorHeightObservations;
    }

    public Unit getUnitOfResiduals() {
        return unitOfResiduals;
    }

    public NumberOfDecimalDigits getNumberOfDecimalDigitsOfResiduals() {
        return numberOfDecimalDigitsOfResiduals;
    }

    public double getRatioResiduals() {
        return ratioResiduals;
    }

    public Unit getUnitOfAdjustedHeightObservations() {
        return unitOfAdjustedHeightObservations;
    }

    public NumberOfDecimalDigits getNumberOfDecimalDigitsOfAdjustedHeightObservations() {
        return numberOfDecimalDigitsOfAdjustedHeightObservations;
    }

    public double getRatioAdjustedHeightObservations() {
        return ratioAdjustedHeightObservations;
    }

    public Unit getUnitStdErrorOfAdjustedHeightObservations() {
        return unitStdErrorOfAdjustedHeightObservations;
    }

    public NumberOfDecimalDigits getNumberOfDecimalDigitsOfStdErrorAdjustedHeightObservations() {
        return numberOfDecimalDigitsOfStdErrorAdjustedHeightObservations;
    }

    public double getRatioStdErrorAdjustedHeightObservations() {
        return ratioStdErrorAdjustedHeightObservations;
    }

    public Unit getUnitStdErrorOfResiduals() {
        return unitStdErrorOfResiduals;
    }

    public NumberOfDecimalDigits getNumberOfDecimalDigitsOfStdErrorResiduals() {
        return numberOfDecimalDigitsOfStdErrorResiduals;
    }

    public double getRatioStdErrorOfResiduals() {
        return ratioStdErrorOfResiduals;
    }
}
