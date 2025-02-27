package com.example.demo.repository;

import com.example.demo.entities.BasicActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicActivityRepository extends JpaRepository<BasicActivity, Long> {
    BasicActivity findBasicActivityById(long id);
}