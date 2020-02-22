package pl.sgeonet.Exceptions;

public class DuplicatedFixedPionts extends Exception {
    public DuplicatedFixedPionts(String errorMessage, Throwable err){
        super(errorMessage, err);
    }
    public DuplicatedFixedPionts(String errorMessage){
        super(errorMessage);
    }
}
