package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name = "BasicActivities")
public class BasicActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @ManyToOne(fetch = FetchType.EAGER)
    Pet pet;

    @OneToOne(fetch = FetchType.EAGER)
    Video video;

    Type type;

    Date start_time;

    Date end_time;

    boolean has_video;


}

enum Type {

}