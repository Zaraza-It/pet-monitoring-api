package com.example.demo.mapper;

import com.example.demo.DTO.ShelterDTO;
import com.example.demo.DTO.ShelterRequest;
import com.example.demo.entities.Shelter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ShelterMapper {
    ShelterMapper INSTANCE = Mappers.getMapper(ShelterMapper.class);

    @Mapping(source = "request.name", target = "name")
    Shelter toEntity(ShelterRequest request);

}
