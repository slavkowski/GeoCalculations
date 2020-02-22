package pl.sgeonet.RaportConfiguration;

public enum Unit {
    M ("m"),
    MM ("mm");

    private final String printValue;

    Unit(String printValue) {
        this.printValue = printValue;
    }

    public String getPrintValue() {
        return printValue;
    }
}
