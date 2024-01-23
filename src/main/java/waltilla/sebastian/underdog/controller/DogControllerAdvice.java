package waltilla.sebastian.underdog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import waltilla.sebastian.underdog.exceptions.DogSaveException;
import waltilla.sebastian.underdog.exceptions.DogValidationException;
import waltilla.sebastian.underdog.exceptions.InvalidUuidException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class DogControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>("[]", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUuidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidUuidException(InvalidUuidException ex) {
        return new ResponseEntity<>("Invalid format on UUID for dog id", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DogValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidUuidException(DogValidationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    //TODO: With current implementation, i think this is never used cause its catched in validation
    @ExceptionHandler(DogSaveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleDogSaveException(DogSaveException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}