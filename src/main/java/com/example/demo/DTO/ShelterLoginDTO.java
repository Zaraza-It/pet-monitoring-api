package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ShelterLoginDTO {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
