package com.example.demo.controllers;

import com.example.demo.DAO.PetDAO;
import com.example.demo.entities.Pet;
import com.example.demo.entities.Shelter;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shelter/pets")
@RequiredArgsConstructor
@SessionAttributes("currentShelter")
public class PetController {
    final PetDAO petDAO;

    @PostMapping("/add")
    public void addPet(@RequestBody Pet pet){
        petDAO.insertPet(pet);
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add-pet";
    }
}
