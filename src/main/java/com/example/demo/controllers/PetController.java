package com.example.demo.controllers;

import com.example.demo.DTO.ActivityDurationDTO;
import com.example.demo.DTO.CreatePetDTO;
import com.example.demo.DTO.PetDTO;
import com.example.demo.entities.Pet;
import com.example.demo.entities.Shelter;
import com.example.demo.model.ShelterDetails;
import com.example.demo.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PetController {
    final PetService petService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('SHELTER')")
    public ResponseEntity<?> addPet(@Valid @RequestBody CreatePetDTO pet) {
        try {
            log.info("adding pet {}", pet);
            ShelterDetails userDetails = (ShelterDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();
            Long shelterId = userDetails.getShelterId();

            petService.createPet(pet, shelterId);
            return ResponseEntity.ok().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal server error: " + e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error occurred"));
        }
    }


    @GetMapping("/{petId}")
    public ResponseEntity<PetDTO> getPetById
            (@Positive @PathVariable Long petId) {
        Optional<Pet> pet = petService.findPetById(petId);
        if (pet.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new PetDTO(pet.get()));
    }

    @GetMapping("/{petId}/photo")
    public ResponseEntity<Resource> getPetPhoto
            (@Positive @PathVariable Long petId) {
        Resource photo = petService.getPetPhoto(petId);
        if (photo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(photo);
    }

    @DeleteMapping("/{petId}/delete")
    public ResponseEntity<?> deletePetById(@Positive @PathVariable Long petId) {
        try {
            petService.deletePet(petId);
            return ResponseEntity.ok().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));

        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of(
                    "error", "Ошибка при удалении питомца: " + e.getMessage()));
        }
    }

    @GetMapping("/{petId}/top-activities")
    public ResponseEntity<?> getTopActivities(@PathVariable Long petId) {
        try {
            List<ActivityDurationDTO> result = petService.getTopActivities(petId);
            if (result.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap(
                                "error", "Данные об активностях питомца не найдены"
                        ));
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap(
                            "error", "Ошибка при получении данных: " + e.getMessage()
                    ));
        }
    }
}
