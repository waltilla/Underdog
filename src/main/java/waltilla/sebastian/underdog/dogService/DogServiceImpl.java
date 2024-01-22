package waltilla.sebastian.underdog.dogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waltilla.sebastian.underdog.repository.DogRepositoryImpl;
import waltilla.sebastian.underdog.utils.DogValidator;
import waltilla.sebastian.underdog.repository.entities.Dog;
import waltilla.sebastian.underdog.repository.entities.DogRequest;

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
        var uuid = UUID.randomUUID().toString();  //TODO task does not have a restriction of dogs with same date, birth and breed ;D
        var validatedRequest = DogValidator.validateDogRequest(request);
        var dog = new Dog(uuid, validatedRequest.getBirthDate(), validatedRequest.getBreed(), validatedRequest.getName());
        return repository.saveDog(dog);
    }
}