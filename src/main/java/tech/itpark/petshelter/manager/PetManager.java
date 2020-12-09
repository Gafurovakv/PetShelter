package tech.itpark.petshelter.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.stereotype.Component;
import tech.itpark.petshelter.exception.DataAccessException;
import tech.itpark.petshelter.exception.NotFoundException;
import tech.itpark.petshelter.mapper.PetRowMapper;
import tech.itpark.petshelter.model.Pet;
import tech.itpark.petshelter.model.PetDto;
import tech.itpark.petshelter.model.Shelter;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PetManager {
    private final NamedParameterJdbcTemplate template;
    private final PetRowMapper rowMapper = new PetRowMapper();

    public List<Pet> getAll() {
        return template.query(
                "SELECT pet.id as pet_id, pet.name as pet_name, pet.type, pet.age, pet.gender ," +
                        "pet.shelters_id, shelter.id as shelter_id, shelter.name as shelter_name, shelter.address from pets pet " +
                        "inner join shelters shelter on shelter.id = pet.shelters_id order by pet.id limit 10",
                new EmptySqlParameterSource(), (rs, rowNum) ->
                        new Pet(rs.getInt("pet_id"),
                                rs.getString("pet_name"),
                                rs.getString("type"),
                                rs.getInt("age"),
                                rs.getString("gender"),
                                new Shelter(
                                        rs.getInt("shelter_id"),
                                        rs.getString("shelter_name"),
                                        rs.getString("address"))
                        ));
    }

    public Pet getById(int id) {
        return template.queryForObject(
                "SELECT pet.id as pet_id, pet.name as pet_name, pet.type, pet.age, pet.gender, pet.shelters_id," +
                        " shelter.id as shelter_id, shelter.name as shelter_name, shelter.address from pets pet " +
                        "inner join shelters shelter on shelter.id = pet.shelters_id WHERE pet.id = :id",
                Map.of("id", id),
                rowMapper);
    }


    public Pet save(PetDto petToSave) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
                "insert into pets (name, type, age ,gender, shelters_id) " +
                        "values (:name, :type, :age, :gender, :shelterId)",
                new MapSqlParameterSource(Map.of(
                        "name", petToSave.getName(),
                        "type", petToSave.getType(),
                        "age", petToSave.getAge(),
                        "gender", petToSave.getGender(),
                        "shelterId", petToSave.getShelterId()
                )),
                keyHolder,
                new String[]{"id"});
        int id = keyHolder.getKey().intValue();
        return getById(id);
    }

    public Pet update(PetDto petUpdate) {
            template.update(
                    "update pets set name =:name, shelters_id =:shelters_id where id =:id;",
                    Map.of(
                            "id", petUpdate.getId(),
                            "name", petUpdate.getName(),
                            "shelters_id", petUpdate.getShelterId()

                    ));
        return getById(petUpdate.getId());
    }


    public List<Pet> search(String type, int age, String gender) {
        return template.query("SELECT pet.id as pet_id, pet.name as pet_name, pet.type, pet.age," +
                        " pet.gender, shelter.id as shelter_id, shelter.name as shelter_name, shelter.address" +
                        " from pets pet inner join shelters shelter on shelter.id = pet.shelters_id " +
                        " WHERE type =:type and age =:age and gender =:gender",
                Map.of("type", type,
                        "age", age,
                        "gender", gender),
                rowMapper
        );
    }

    public Pet removeById(int id) {
        Pet item = getById(id);

        template.update(
                "delete from pets where id =:id",
                Map.of("id", item.getId())
        );
        return item;
    }
}




