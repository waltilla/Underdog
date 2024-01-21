package waltilla.sebastian.underdog.exeptions;

public class DogSaveException extends RuntimeException {
    public DogSaveException(String message) {
        super(message);
    }
}
