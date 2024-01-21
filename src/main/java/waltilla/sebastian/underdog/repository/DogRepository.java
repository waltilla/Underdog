package waltilla.sebastian.underdog.repository;


import waltilla.sebastian.underdog.repository.entities.Dog;

import java.util.List;

public interface DogRepository {

    Dog findById(String id);

    Dog saveDog(Dog dog);

    List<Dog> getAllDogs();


}