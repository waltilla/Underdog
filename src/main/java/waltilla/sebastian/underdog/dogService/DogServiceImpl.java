package waltilla.sebastian.underdog.dogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waltilla.sebastian.underdog.dogDb.DogRepositoryImpl;
import waltilla.sebastian.underdog.utils.DogValidator;
import waltilla.sebastian.underdog.dogDb.entities.Dog;
import waltilla.sebastian.underdog.dogDb.entities.DogRequest;

import java.util.List;
import java.util.UUID;

@Service
public class DogServiceImpl implements DogService {

    DogRepositoryImpl repository;
    @Autowired
    public DogServiceImpl(DogRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public Dog getDogById(String uuidString) {
        return  repository.findById(DogValidator.validateUuid(uuidString));
    }

    @Override
    public List<Dog> getAllDogs() {
        return repository.getAllDogs();
    }

    @Override
    public Dog createDog(DogRequest request) {
        var uuid = UUID.fromString(request.toString()).toString();
        var validatedRequest = validateDogRequest(request);
        var dog = new Dog(uuid, validatedRequest.getBirthDate(), validatedRequest.getBreed(), validatedRequest.getName());
        return repository.saveDog(dog);
    }

    public DogRequest validateDogRequest(DogRequest request){
        var validatedBirthDate = DogValidator.validateIsoDateFormat(request.getBirthDate());
        var breed = request.getBreed() != null ? request.getBreed() : "";
        // Name is taken care of by javax.validation, discussion about this with a more senior dude...
        return new DogRequest(validatedBirthDate, breed, request.getName());
    }
}