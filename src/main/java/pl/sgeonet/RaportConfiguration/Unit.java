package pl.sgeonet.RaportConfiguration;

public enum Unit {
    KM ("km"),
    M ("m"),
    CM ("cm"),
    MM ("mm");

    private final String printValue;

    Unit(String printValue) {
        this.printValue = printValue;
    }

    public String getPrintValue() {
        return printValue;
    }
}
