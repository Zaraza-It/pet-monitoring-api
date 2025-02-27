package com.example.demo.DAO;

import com.example.demo.entities.Species;
import com.example.demo.repository.SpeciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpeciesDAO {
    private final SpeciesRepository speciesRepository;

    Species getSpeciesById(long id){
        return speciesRepository.findSpeciesById(id);
    }
}
