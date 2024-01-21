package waltilla.sebastian.underdog.exeptions;

public class InvalidUuidException extends RuntimeException {

    public InvalidUuidException(String message) {
        super(message);
    }
}