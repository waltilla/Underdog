package waltilla.sebastian.underdog.utils;

import waltilla.sebastian.underdog.dogDb.entities.Dog;
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