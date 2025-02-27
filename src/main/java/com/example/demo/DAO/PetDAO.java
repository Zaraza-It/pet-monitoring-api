package com.example.demo.DAO;

import com.example.demo.entities.Pet;
import com.example.demo.repository.PetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PetDAO {
    private final PetsRepository petsRepository;

    Pet getPetById(long id){
        return petsRepository.findPetById(id);
    }

    public void insertPet(Pet pet){
        petsRepository.save(pet);
    }
}
