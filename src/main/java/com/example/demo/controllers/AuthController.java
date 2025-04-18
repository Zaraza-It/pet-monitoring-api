package com.example.demo.controllers;

import com.example.demo.DTO.LoginResponseDTO;
import com.example.demo.DTO.ShelterLoginDTO;
import com.example.demo.DTO.request.ShelterRequest;
import com.example.demo.DTO.response.ShelterResponse;
import com.example.demo.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ShelterService shelterService;

    @PostMapping("/register")
    public ResponseEntity<ShelterResponse> register(@Valid @RequestBody ShelterRequest request) {
            ShelterResponse response = shelterService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(response);
        }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody ShelterLoginDTO shelter) {
            LoginResponseDTO response = shelterService.verify(shelter);
            return ResponseEntity.ok(response);
    }
}
