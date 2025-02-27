package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;
import java.util.UUID;



@Entity
@Data
@Table(name = "Shelters")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @OneToMany(mappedBy = "shelter")
    List<Pet> pet;

    @OneToMany(mappedBy = "shelter")
    List<Camera> camera;

    String name;

    String shelter_login;

    String shelter_password;

    String email;

    Date create_dt;


}
