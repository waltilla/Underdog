package waltilla.sebastian.underdog.dogDb;


import waltilla.sebastian.underdog.dogDb.entities.Dog;

import java.util.List;
import java.util.Optional;

public interface DogRepository {

    Dog findById(String id);

    Dog saveDog(Dog dog);

    List<Dog> getAllDogs();


}