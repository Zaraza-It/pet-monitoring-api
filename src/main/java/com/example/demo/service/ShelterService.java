package com.example.demo.service;

import com.example.demo.DTO.LoginResponseDTO;
import com.example.demo.DTO.ShelterLoginDTO;
import com.example.demo.DTO.request.ShelterRequest;
import com.example.demo.DTO.response.PetResponse;
import com.example.demo.DTO.response.ShelterResponse;
import com.example.demo.entities.Shelter;
import com.example.demo.mapper.ShelterMapper;
import com.example.demo.model.ShelterDetails;
import com.example.demo.repository.PetRepository;
import com.example.demo.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShelterService {
    private final ShelterRepository shelterRepository;
    private final ShelterMapper shelterMapper;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    private final PetRepository petRepository;
    private final

    final AuthenticationManager authManager;

    public List<ShelterResponse> getAllShelters() {
        return shelterRepository.findAll().stream()
                .map(shelterMapper::toResponse)
                .toList();
    }

    public Optional<ShelterResponse> findShelterById(long id) {
        return shelterRepository.findShelterById(id).map(
                shelterMapper::toResponse);
    }

    public ShelterResponse register(ShelterRequest request) {

        if (shelterRepository.existsByShelterLogin(request.getLogin())) {
            throw new RuntimeException("Приют с таким логином уже существует");
        }

        Shelter shelter = shelterMapper.toEntity(request);
        shelter.setShelterPassword(encoder.encode(shelter.getShelterPassword()));
        shelterRepository.save(shelter);
        return shelterMapper.toResponse(shelter);
    }


    public LoginResponseDTO verify(ShelterLoginDTO shelter) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            shelter.getLogin(),
                            shelter.getPassword()
                    )
            );

            ShelterDetails shelterDetails = (ShelterDetails) auth.getPrincipal();

            final String token = jwtService.generateToken(shelter.getLogin());

            return new LoginResponseDTO(token, shelterDetails.getUsername());
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Логин или пароль введены не правильно!");
        }

    }
