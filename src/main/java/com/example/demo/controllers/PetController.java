package com.example.demo.controllers;

import com.example.demo.DTO.request.CreatePetRequest;
import com.example.demo.DTO.response.PetResponse;
import com.example.demo.entities.Pet;
import com.example.demo.model.ShelterDetails;
import com.example.demo.service.PetService;
import com.example.demo.service.ShelterService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PetController {
    private final PetService petService;
    private final ShelterService shelterService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('SHELTER')")
    public ResponseEntity<?> addPet(@Valid @RequestBody CreatePetRequest pet) {
        ShelterDetails userDetails = (ShelterDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Long shelterId = userDetails.getShelterId();

        petService.addPetToShelter(pet, shelterId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{petId}")
    public ResponseEntity<PetResponse> getPetById
            (@Positive @PathVariable Long petId) {
        Optional<PetResponse> pet = petService.findPetById(petId);
        return pet.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{petId}/delete")
    public ResponseEntity<?> deletePetById(@Positive @PathVariable Long petId) {
        if (petService.deletePetById(petId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<List<PetResponse>> getShelterPets(
            @Positive @PathVariable long id) {
        return ResponseEntity.ok(petService.getPetsShelterById(id));
    }
}
