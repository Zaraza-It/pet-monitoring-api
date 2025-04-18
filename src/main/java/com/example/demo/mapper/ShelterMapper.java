package com.example.demo.mapper;

import com.example.demo.DTO.request.ShelterRequest;
import com.example.demo.DTO.response.ShelterResponse;
import com.example.demo.entities.Shelter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ShelterMapper {
    ShelterMapper INSTANCE = Mappers.getMapper(ShelterMapper.class);

    @Mapping(target = "createDt", defaultExpression = "java(LocalDateTime localDateTime = LocalDateTime.now();)" )
    Shelter toEntity(ShelterRequest request);

    ShelterResponse toResponse(Shelter entity);
}
