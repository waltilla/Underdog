package waltilla.sebastian.underdog.dogDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import waltilla.sebastian.underdog.dogDb.entities.Dog;
import waltilla.sebastian.underdog.exeptions.DogSaveException;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class DogRepositoryImpl implements DogRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DogRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Dog findById(String id) {
        try {
            String sql = "SELECT * FROM dogs WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                    new Dog(rs.getString("id"), rs.getString("birth_date"),
                            rs.getString("breed"), rs.getString("name")));
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("No dog was found with id (uuid): " + id);
        }
    }

    @Override

    public List<Dog> getAllDogs() {
        try {
            String sql = "SELECT * FROM dogs";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dog.class));
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("No dog was found");
        }
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