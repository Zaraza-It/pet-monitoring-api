package com.example.demo.controllers;

import com.example.demo.DTO.LoginResponseDTO;
import com.example.demo.DTO.ShelterLoginDTO;
import com.example.demo.entities.Shelter;
import com.example.demo.service.ShelterService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ShelterService shelterService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Shelter shelter) {
        try {
            ShelterResponse response = shelterService.register(shelter);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Ошибка при регистрации приюта: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody ShelterLoginDTO shelter) {
        try {
            LoginResponseDTO response = shelterService.verify(shelter);
            return ResponseEntity.ok(response);
        } catch (ResponseStatusException e) {
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Внутренняя ошибка сервера"));
        }
    }
}
