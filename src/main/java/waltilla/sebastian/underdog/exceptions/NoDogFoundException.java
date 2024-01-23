package waltilla.sebastian.underdog.exceptions;

public class NoDogFoundException extends RuntimeException {

    public NoDogFoundException(String message) {
        super(message);
    }
}