package com.example.demo.entities;

import com.example.demo.model.ActivityType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "BasicActivities")
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor

public class BasicActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @ManyToOne(fetch = FetchType.EAGER)
    Pet pet;

    @ManyToOne(fetch = FetchType.EAGER)
    Video video;

    ActivityType activityType;

    long startTimeInSeconds;

    long endTimeInSeconds;
}