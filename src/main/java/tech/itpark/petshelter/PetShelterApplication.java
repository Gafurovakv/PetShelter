package tech.itpark.petshelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.itpark.petshelter.manager.PetManager;
import tech.itpark.petshelter.model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

@SpringBootApplication
public class PetShelterApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetShelterApplication.class, args);
    }
}
