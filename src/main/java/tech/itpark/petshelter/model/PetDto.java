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

//    public PetDto(String name, String type, int age, String gender, int shelterId) {
//        this.name = name;
//        this.type = type;
//        this.age = age;
//        this.gender = gender;
//        this.shelterId = shelterId;
//    }


}



