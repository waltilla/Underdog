package waltilla.sebastian.underdog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import waltilla.sebastian.underdog.dogDb.entities.Dog;
import waltilla.sebastian.underdog.dogDb.entities.DogRequest;
import waltilla.sebastian.underdog.dogService.DogService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dogs")
@Validated
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

    @PostMapping
    public ResponseEntity<Dog> createDog(@Valid @RequestBody DogRequest request) {
        return new ResponseEntity<>(service.createDog(request), HttpStatus.OK);
    }

}
