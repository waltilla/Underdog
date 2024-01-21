package waltilla.sebastian.underdog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import waltilla.sebastian.underdog.repository.entities.Dog;
import waltilla.sebastian.underdog.exeptions.DogSaveException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DogRepositoryImpl implements DogRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DogRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Dog findById(String id) {
            String sql = "SELECT * FROM dogs WHERE id = ?";
            return jdbcTemplate.query(sql, new DogRowMapper(), id)
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("No dog found or more than one dog found!"));
    }

    @Override

    public List<Dog> getAllDogs() {
        return Optional.of(jdbcTemplate.query("SELECT * FROM dogs", new DogRowMapper()))
                .orElseThrow(() -> new NoSuchElementException("No dog was found"));
    }

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