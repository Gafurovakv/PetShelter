package tech.itpark.petshelter.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark.petshelter.manager.PetManager;
import tech.itpark.petshelter.model.Pet;
import tech.itpark.petshelter.model.PetDto;

import java.util.List;

@RestController
@RequestMapping("pets")
@AllArgsConstructor
public class PetShelterController {
    private final PetManager manager;

    @GetMapping("/")
    public List<Pet> getAll() {
        return manager.getAll();
    }

    @GetMapping("/{id}")
    public Pet getById(@PathVariable int id) {
        return manager.getById(id);
    }

    @PostMapping("/save")
    public Pet save(@RequestBody PetDto pet) {
        return manager.save(pet);
    }

    @PostMapping("/update")
    public Pet update(@RequestBody PetDto petUpdate) {
        return manager.update(petUpdate);
    }

    @GetMapping("/search")
    public List<Pet> search(@RequestParam String type,
                            @RequestParam int age,
                            @RequestParam String gender) {
        return manager.search(type, age, gender);
    }

    @DeleteMapping("/remove/{id}")
    public Pet removeById(@PathVariable int id) {
        return manager.removeById(id);
    }
}
