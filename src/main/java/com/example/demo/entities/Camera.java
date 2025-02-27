package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Camera")
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @ManyToOne(fetch = FetchType.EAGER)
    Shelter shelter;

    @OneToMany(mappedBy = "camera")
    List<Video> video;

    String url;

    String model;

    boolean is_active;

    Date created_dt;
}
