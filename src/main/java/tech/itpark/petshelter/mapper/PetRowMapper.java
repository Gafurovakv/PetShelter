package tech.itpark.petshelter.mapper;

import org.springframework.jdbc.core.RowMapper;
import tech.itpark.petshelter.model.Pet;
import tech.itpark.petshelter.model.Shelter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetRowMapper implements RowMapper<Pet> {
    public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Pet(rs.getInt("pet_id"),
                rs.getString("pet_name"),
                rs.getString("type"),
                rs.getInt("age"),
                rs.getString("gender"),
                new Shelter(
                        rs.getInt("shelter_id"),
                        rs.getString("shelter_name"),
                        rs.getString("address")
                )
        );
    }
}
