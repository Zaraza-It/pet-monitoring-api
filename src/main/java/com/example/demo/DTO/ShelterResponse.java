package com.example.demo.DTO;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ShelterResponse {
  @Min(4)
  @Max(30)
  @NotBlank
  private String name;

  @Min(10)
  @Max(254)
  @NotBlank
  private String email;

}
