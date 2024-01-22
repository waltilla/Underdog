package waltilla.sebastian.underdog.repository;

import org.springframework.jdbc.core.RowMapper;
import waltilla.sebastian.underdog.repository.entities.Dog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DogRowMapper implements RowMapper<Dog> {
    @Override
    public Dog mapRow(ResultSet rs, int rowNum) throws SQLException {
        Dog dog = new Dog();
        dog.setId(rs.getString("id"));
        dog.setBirthDate(rs.getString("birth_date"));
        dog.setBreed(rs.getString("breed"));
        dog.setName(rs.getString("name"));
        return  dog;
    }
}
