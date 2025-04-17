package com.example.demo.repository;

import com.example.demo.entities.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Optional<Shelter> findShelterById(long id);

    Optional<Shelter> findByShelterLogin(String shelterName);
    boolean existsByShelterLogin(String shelterName);
}