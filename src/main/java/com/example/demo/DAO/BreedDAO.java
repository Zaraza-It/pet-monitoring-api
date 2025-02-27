package com.example.demo.DAO;

import com.example.demo.entities.Breed;
import com.example.demo.repository.BreedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BreedDAO {
    private final BreedRepository breedRepository;

    Breed getBreedById(long id){
        return breedRepository.findBreedById(id);
    }
}
