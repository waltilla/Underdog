package waltilla.sebastian.underdog.utils;

import waltilla.sebastian.underdog.repository.entities.Dog;
import waltilla.sebastian.underdog.repository.entities.DogRequest;
import waltilla.sebastian.underdog.exeptions.DogValidationException;
import waltilla.sebastian.underdog.exeptions.InvalidUuidException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public class DogValidator {

    public static void validateDog(Dog dog) {
        validateIsoDateFormat(dog.getBirthDate());
        validateUuid(dog.getId());
    }

    public static DogRequest validateDogRequest(DogRequest request){
        var validatedBirthDate = DogValidator.validateIsoDateFormat(request.getBirthDate());
        var breed = request.getBreed() != null ? request.getBreed() : "";
        // Name is taken care of by javax.validation, discussion about this with a more senior dude...
        return new DogRequest(validatedBirthDate, breed, request.getName());
    }

    public static String validateIsoDateFormat(String dateString) {
        try {
            LocalDate localDate = LocalDate.parse(dateString);
            Date date = java.sql.Date.valueOf(localDate);
        } catch (Exception e) {
            throw new DogValidationException("birthDate not matching iso format: yyyy-mm-dd");
        }
        return dateString;
    }

    public static String validateUuid(String uuidString) {
        try {
            UUID uuid = UUID.fromString(uuidString);
            return uuidString;
        } catch (IllegalArgumentException e) {
            throw new InvalidUuidException("id (UUID) is not a valid UUID");
        }
    }
}