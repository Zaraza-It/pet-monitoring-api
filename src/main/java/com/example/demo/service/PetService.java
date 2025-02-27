package com.example.demo.service;

import com.example.demo.DAO.PetDAO;
import com.example.demo.entities.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {
    final PetDAO petDAO;

    public void insertPet(Pet pet){
        petDAO.insertPet(pet);
    }
}
