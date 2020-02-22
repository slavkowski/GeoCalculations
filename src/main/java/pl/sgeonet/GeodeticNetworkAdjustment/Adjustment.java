package pl.sgeonet.GeodeticNetworkAdjustment;

import pl.sgeonet.Exceptions.DuplicatedFixedPionts;

public abstract class Adjustment {
    public abstract void proceedAdjustment() throws DuplicatedFixedPionts;
    protected abstract void checkDataCorrectness() throws DuplicatedFixedPionts;
    protected abstract void createVariablesForAdjustment();
}
