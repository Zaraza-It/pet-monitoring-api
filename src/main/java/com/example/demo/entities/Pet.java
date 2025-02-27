package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "Pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @ManyToOne(fetch = FetchType.EAGER)
    Shelter shelter;

    @ManyToOne(fetch = FetchType.EAGER)
    Breed breed;

    @ManyToOne(fetch = FetchType.EAGER)
    Species species;

    @OneToMany(mappedBy = "pet")
    List<BasicActivity> basicActivity;

    @ManyToMany(mappedBy = "pets")
    List<Event> events;

    String name;

    int age;

    String health_info;

    String photo_url;

    Date create_dt;

    public Pet(String name, int age, String health_info, String photo_url, Date create_dt) {
        this.name = name;
        this.age = age;
        this.health_info = health_info;
        this.photo_url = photo_url;
        this.create_dt = create_dt;
    }

}
