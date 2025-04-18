package com.example.demo.controllers;
import com.example.demo.DTO.response.ShelterResponse;
import com.example.demo.entities.Pet;
import com.example.demo.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shelters")
@RequiredArgsConstructor
public class ShelterController {
    private final ShelterService shelterService;

    @GetMapping("/")
    public ResponseEntity<List<ShelterResponse>> getAllShelters() {
        List<ShelterResponse> response = shelterService.getAllShelters();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShelterResponse> getShelterById(
            @Positive @PathVariable long id) {
        Optional<ShelterResponse> shelter = shelterService.findShelterById(id);
        return shelter.map(shelterResponse -> ResponseEntity.ok().body(shelterResponse))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}