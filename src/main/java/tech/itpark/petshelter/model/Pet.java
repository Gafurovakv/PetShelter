package tech.itpark.petshelter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class Pet {
    int id;
    String name;
    String type;
    int age;
    String gender;
    Shelter shelter;

    public Pet(String name, String type, int age, String gender) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.gender = gender;
    }

    public Pet(int id, String name) {
        this.id = id;
        this.name = name;
    }

}



