package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
@Table(name = "Videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @OneToOne(mappedBy = "video")
    BasicActivity basicActivity;

    @OneToOne(mappedBy = "video")
    Event event;

    @ManyToOne(fetch = FetchType.EAGER)
    Camera camera;

    String filePath;

    Date start_time;

    Date end_time;
}
