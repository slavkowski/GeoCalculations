package pl.sgeonet.Exceptions;

public class DuplicatedFixedPoints extends Exception {
    public DuplicatedFixedPoints(String errorMessage, Throwable err){
        super(errorMessage, err);
    }
    public DuplicatedFixedPoints(String errorMessage){
        super(errorMessage);
    }
}
