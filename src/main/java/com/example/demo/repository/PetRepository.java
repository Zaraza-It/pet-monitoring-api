package com.example.demo.repository;

import com.example.demo.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
   Optional<Pet> findPetById(long id);

    List<Pet> findByGroupId(long id);
}