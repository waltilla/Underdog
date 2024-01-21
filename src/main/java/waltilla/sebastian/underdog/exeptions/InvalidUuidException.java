package waltilla.sebastian.underdog.exeptions;


import java.util.UUID;

public class InvalidUuidException extends RuntimeException {

    public InvalidUuidException(String message) {
        super(message);
    }

}
