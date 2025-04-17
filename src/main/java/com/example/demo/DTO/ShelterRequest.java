package com.example.demo.DTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ShelterRequest {
    @Min(4)
    @Max(30)
    @NotBlank
    private String name;

    @Min(4)
    @Max(30)
    @NotBlank
    private String login;

    @NotBlank
    @Min(5)
    private String password;

    @Min(10)
    @Max(254)
    @NotBlank
    private String email;

}
