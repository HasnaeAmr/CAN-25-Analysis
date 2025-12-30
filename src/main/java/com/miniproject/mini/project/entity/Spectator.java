package com.miniproject.mini.project.entity;

import com.miniproject.mini.project.enums.Nationality;
import jakarta.persistence.*;

@Entity
@Table(name="spectator")
public class Spectator {

    @Id
    Long id;

    @Column
    String matchId;

    int age;

    @Enumerated(EnumType.STRING)
    Nationality nationality;

}