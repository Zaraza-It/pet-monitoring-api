package com.example.demo.controllers;

import com.example.demo.DTO.PetDTO;
import com.example.demo.entities.Pet;
import com.example.demo.DTO.ShelterDTO;
import com.example.demo.entities.Shelter;
import com.example.demo.service.ShelterService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shelters")
@RequiredArgsConstructor
public class ShelterController {
    final ShelterService shelterService;

    @GetMapping("/")
    public List<ShelterDTO> getAllShelters() {
        return shelterService
                .getAllShelters()
                .stream()
                .map(ShelterDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShelterDTO> getShelter
            (@PathVariable long id) {
       Optional<Shelter> shelter = shelterService.findShelter(id);
        if (shelter.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ShelterDTO(shelter.get()));
    }

    @GetMapping("/{id}/pets")
    public ResponseEntity<List<PetDTO>> getShelterPets(@PathVariable long id) {
       Optional<Shelter> shelter = shelterService.findShelter(id);
        if (shelter.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Pet> pets = shelter.get().getPets();

        List<PetDTO> petDTOs = pets.stream()
                .map(PetDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(petDTOs);
    }
}