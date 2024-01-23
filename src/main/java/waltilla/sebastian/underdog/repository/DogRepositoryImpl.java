package waltilla.sebastian.underdog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import waltilla.sebastian.underdog.exceptions.DogSaveException;
import waltilla.sebastian.underdog.exceptions.NoDogFoundException;
import waltilla.sebastian.underdog.repository.entities.Dog;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class DogRepositoryImpl implements DogRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DogRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //TODO: @ApiResponses?
    @Override
    public Dog findById(String id) {
        var sql = "SELECT * FROM dogs WHERE id = ?";
        return jdbcTemplate.query(sql, new DogRowMapper(), id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoDogFoundException("No dog found!"));
    }

    @Override
    public List<Dog> getAllDogs() {
        return Optional.of(jdbcTemplate
                        .query("SELECT * FROM dogs", new DogRowMapper()))
                        .orElseThrow(() -> new NoSuchElementException("No dog found"));
    }

    @Override
    public Dog saveDog(Dog dog) {
        var sql = "INSERT INTO dogs (id, birth_date, breed, name) VALUES (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, dog.getId(), dog.getBirthDate(), dog.getBreed(), dog.getName());
            return dog;
        } catch (Exception e) {
            throw new DogSaveException("Error saving dog: " + e.getMessage());
        }
    }
}