package pl.sats.GeodeticNetworkAdjustment;

import pl.sats.Exceptions.DuplicatedFixedPionts;

public abstract class Adjustment {
    public abstract void proceedAdjustment() throws DuplicatedFixedPionts;
    protected abstract void checkDataCorrectness() throws DuplicatedFixedPionts;
    protected abstract void createVariablesForAdjustment();
}
