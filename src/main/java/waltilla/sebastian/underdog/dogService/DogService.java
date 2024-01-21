package waltilla.sebastian.underdog.dogService;

import waltilla.sebastian.underdog.dogDb.entities.Dog;
import waltilla.sebastian.underdog.dogDb.entities.DogRequest;

import java.util.List;

public interface DogService {
        Dog createDog(DogRequest dog);
        Dog getDogById(String id);
        List<Dog> getAllDogs();
}
