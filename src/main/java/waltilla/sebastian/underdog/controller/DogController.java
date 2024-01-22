package waltilla.sebastian.underdog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waltilla.sebastian.underdog.dogService.DogService;
import waltilla.sebastian.underdog.repository.entities.Dog;
import waltilla.sebastian.underdog.repository.entities.DogRequest;

import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {

    private final DogService service;

    public DogController(DogService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDog(@PathVariable String uuidIdString) {
        return new ResponseEntity<>(service.getDogById(uuidIdString), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Dog>> getAllDogs() {
        return new ResponseEntity<>(service.getAllDogs(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dog> createDog(@RequestBody DogRequest request) {
        return new ResponseEntity<>(service.createDog(request), HttpStatus.OK);
    }

}
