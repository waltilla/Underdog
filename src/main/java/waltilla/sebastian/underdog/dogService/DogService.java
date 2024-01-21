package waltilla.sebastian.underdog.dogService;

import waltilla.sebastian.underdog.dogDb.entities.Dog;
import waltilla.sebastian.underdog.dogDb.entities.DogRequest;

public interface DogService {
        Dog createDog(DogRequest dog);
        Dog getDogById(String id);
        Dog updateDog(String id, DogRequest dog);


}
