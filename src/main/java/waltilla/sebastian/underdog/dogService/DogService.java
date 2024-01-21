package waltilla.sebastian.underdog.dogService;

import waltilla.sebastian.underdog.repository.entities.Dog;
import waltilla.sebastian.underdog.repository.entities.DogRequest;

import java.util.List;

public interface DogService {
        Dog createDog(DogRequest dog);
        Dog getDogById(String id);
        List<Dog> getAllDogs();
}
