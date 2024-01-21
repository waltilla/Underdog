package waltilla.sebastian.underdog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import waltilla.sebastian.underdog.dogDb.entities.Dog;
import waltilla.sebastian.underdog.dogDb.entities.DogRequest;
import waltilla.sebastian.underdog.dogService.DogService;

import javax.validation.Valid;

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

    @PostMapping
    public ResponseEntity<Dog> createDog(@Valid @RequestBody DogRequest request) {
        return new ResponseEntity<>(service.createDog(request), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dog> updateDog(@PathVariable String uuidIdString, @RequestBody DogRequest request) {
        return new ResponseEntity<>(service.updateDog(uuidIdString, request), HttpStatus.OK);
    }

}
