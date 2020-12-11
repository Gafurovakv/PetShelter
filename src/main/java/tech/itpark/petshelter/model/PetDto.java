package tech.itpark.petshelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetDto {
    int id;
    String name;
    String type;
    int age;
    String gender;
    int shelterId;

    public PetDto(int id, String name, int shelterId) {
        this.id = id;
        this.name = name;
        this.shelterId = shelterId;
    }

    public PetDto() {
    }
}




