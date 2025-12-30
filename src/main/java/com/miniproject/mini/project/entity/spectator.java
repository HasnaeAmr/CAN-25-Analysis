package com.miniproject.mini.project.entity;

import com.miniproject.mini.project.enums.Nationality;
import jakarta.persistence.*;

@Entity
public class spectator {

    @Id
    Long id;

    @Column
    String matchId;

    int age;

    @Enumerated(EnumType.STRING)
    Nationality nationality;

}