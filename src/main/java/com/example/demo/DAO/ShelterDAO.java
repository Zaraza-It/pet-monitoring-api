package com.example.demo.DAO;

import com.example.demo.entities.Shelter;
import com.example.demo.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShelterDAO {
    private final ShelterRepository shelterRepository;

    Shelter getShelterById(long id){
        return shelterRepository.findShelterById(id);
    }
}
