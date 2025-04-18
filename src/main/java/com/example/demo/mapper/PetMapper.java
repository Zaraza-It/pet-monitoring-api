package com.example.demo.mapper;

import com.example.demo.DTO.request.CreatePetRequest;
import com.example.demo.DTO.response.PetResponse;
import com.example.demo.entities.Pet;
import com.example.demo.entities.PetGroup;
import com.example.demo.entities.Shelter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PetMapper {
    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    @Mapping(target = "createDt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "shelter", target = "shelter")
    @Mapping(source = "group", target = "group")
    Pet toPet(CreatePetRequest request, Shelter shelter, PetGroup group);

    PetResponse toPetResponse(Pet pet);
}
