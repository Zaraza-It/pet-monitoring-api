package com.example.demo.service;

import com.example.demo.DTO.request.CreatePetRequest;
import com.example.demo.DTO.response.PetResponse;
import com.example.demo.entities.*;
import com.example.demo.exception.PetNotFoundException;
import com.example.demo.mapper.PetMapper;
import com.example.demo.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final ShelterRepository shelterRepository;
    private final PetGroupRepository petGroupRepository;
    private final PetMapper petMapper;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public List<PetResponse> getPetsShelterById(Long id) {
        List<Pet> pets = petRepository.findByShelterId(id);
        if (pets.isEmpty()) {
            throw new PetNotFoundException("Pets not found");
        }

        return pets.stream()
                .map(petMapper::toPetResponse)
                .toList();
    }


    public Optional<PetResponse> findPetById(Long petId) {
        return petRepository.findPetById(petId)
                .map(petMapper::toPetResponse);
    }

    public void addPetToShelter(CreatePetRequest request, Long shelterId) {
        Shelter shelter = shelterRepository.findById(shelterId)
                .orElseThrow(() -> new EntityNotFoundException("Shelter not found"));

        PetGroup group = petGroupRepository.findByShelterIdAndGroupId(shelterId, request.getGroup())
                .orElseGet(() -> {
                    return petGroupRepository.save(PetGroup.builder()
                            .shelter(shelter)
                            .groupNumber(request.getGroup())
                            .createdDt(LocalDateTime.now())
                            .build());
                });
        petRepository.save(petMapper.toPet(request, shelter, group));
    }

    public boolean deletePetById(long petId) {
        if (petRepository.existsById(petId)) {
            petRepository.deletePetById(petId);
            return true;
        }
        return false;
    }

}
