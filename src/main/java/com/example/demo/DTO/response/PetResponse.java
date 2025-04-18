package com.example.demo.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetResponse {

    private int age;

    private double weight;

    private double height;

    private String breed;

    private String species;

    private String name;

    private String description;

}
