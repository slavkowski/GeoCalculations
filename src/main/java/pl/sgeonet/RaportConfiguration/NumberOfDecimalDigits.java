package pl.sgeonet.RaportConfiguration;

public enum NumberOfDecimalDigits {
    ZERO ("0"),
    ONE ("1"),
    TWO ("2"),
    THREE ("3"),
    FOUR ("4"),
    FIVE ("5");

    private final String printValue;

    NumberOfDecimalDigits(String printValue) {
        this.printValue = printValue;
    }

    public String getPrintValue() {
        return printValue;
    }
}
