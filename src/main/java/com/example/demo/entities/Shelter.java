package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;


@Entity
@Data
@Table(name = "Shelters")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    List<Pet> pets;

    @OneToMany(mappedBy = "shelter",fetch = FetchType.LAZY)
    List<PetGroup> groups;

    @Min(4)
    @Max(30)
    @NotBlank
    String name;

    @Min(4)
    @Max(30)
    @NotBlank
    String shelterLogin;

    @NotBlank
    @Min(5)
    String shelterPassword;

    @Email
    @Nullable
    String email;

    @Min(10)
    @Max(254)
    @NotBlank
    String description;

    Date createDt;
}
