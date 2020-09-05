package pl.sgeonet.GeodeticNetworkAdjustment;

import pl.sgeonet.Exceptions.DuplicatedFixedPoints;

public abstract class Adjustment {
    public abstract void proceedAdjustment() throws DuplicatedFixedPoints;
    protected abstract void checkDataCorrectness() throws DuplicatedFixedPoints;
    protected abstract void createVariablesForAdjustment();
}
