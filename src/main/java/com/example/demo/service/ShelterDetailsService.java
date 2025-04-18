package com.example.demo.service;

import com.example.demo.entities.Shelter;
import com.example.demo.model.ShelterDetails;
import com.example.demo.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShelterDetailsService implements UserDetailsService {

    private final ShelterRepository shelterRepository;

    @Override
    public UserDetails loadUserByUsername(String shelterName) throws UsernameNotFoundException {
        Optional<Shelter> shelter = shelterRepository.findByShelterLogin(shelterName);
        if (shelter.isEmpty()) {
            throw new UsernameNotFoundException("shelter not found");
        }
        return new ShelterDetails(shelter.get());
    }

}
