package waltilla.sebastian.underdog.dogDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import waltilla.sebastian.underdog.dogDb.entities.Dog;
import waltilla.sebastian.underdog.exeptions.DogSaveException;

import java.util.NoSuchElementException;

@Repository
public class DogRepositoryImpl implements DogRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public Dog saveDog(Dog dog) {
            String sql = "INSERT INTO dogs (id, birth_date, breed, name) VALUES (?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, dog.getId(), dog.getBirthDate(), dog.getBreed(), dog.getName());
            return dog;
        } catch (Exception e) {
            throw new DogSaveException("Error saving dog: " + e.getMessage());
        }
    }

    @Override
    public Dog updateDog(Dog dog) {

        try {
            String sql = "UPDATE dogs SET birth_date = ?, breed = ?, name = ? WHERE id = ?";
            jdbcTemplate.update(sql, dog.getBirthDate(), dog.getBreed(), dog.getName(), dog.getId());
            return dog;
        } catch (Exception e) {
            throw new DogSaveException("Could not update the dog with Id: " + dog.getId());
        }

    }

}