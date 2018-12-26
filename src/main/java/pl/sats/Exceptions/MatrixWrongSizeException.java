package pl.sats.Exceptions;

public class MatrixWrongSizeException extends Exception {
    public MatrixWrongSizeException() {
    }

    public MatrixWrongSizeException(String message) {
        super(message);
    }
}
