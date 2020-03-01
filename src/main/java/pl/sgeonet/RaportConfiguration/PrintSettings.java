package pl.sgeonet.RaportConfiguration;

public class PrintSettings {
    Unit unitOfCalculatedHeight = Unit.M;
    NumberOfDecimalDigits numberOfDecimalDigitsOfCalculatedHeight = NumberOfDecimalDigits.THREE;

    Unit unitOfCalculatedStdErrorOfCalculatedHeight = Unit.MM;
    NumberOfDecimalDigits numberOfDecimalDigitsOfStdErrorOfCalculatedHeight = NumberOfDecimalDigits.ONE;

    float calculatedHeightVsStdErrorRatio = 1F;

    Unit unitOfHeightObservations = Unit.M;
    NumberOfDecimalDigits numberOfDecimalDigitsOfHeightObservations = NumberOfDecimalDigits.THREE;

    public Unit getUnitOfCalculatedHeight() {
        return unitOfCalculatedHeight;
    }

    public NumberOfDecimalDigits getNumberOfDecimalDigitsOfCalculatedHeight() {
        return numberOfDecimalDigitsOfCalculatedHeight;
    }

    public Unit getUnitOfCalculatedStdErrorOfCalculatedHeight() {
        return unitOfCalculatedStdErrorOfCalculatedHeight;
    }

    public NumberOfDecimalDigits getNumberOfDecimalDigitsOfStdErrorOfCalculatedHeight() {
        return numberOfDecimalDigitsOfStdErrorOfCalculatedHeight;
    }

    public float getCalculatedHeightVsStdErrorRatio() {
        if (unitOfCalculatedHeight == Unit.M && unitOfCalculatedStdErrorOfCalculatedHeight == Unit.MM) {
            return 1000F;
        } else if (unitOfCalculatedHeight == Unit.MM && unitOfCalculatedStdErrorOfCalculatedHeight == Unit.M) {
            return .001F;
        }
        return calculatedHeightVsStdErrorRatio;
    }
}
