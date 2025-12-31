package com.miniproject.mini.project.entity;

import com.miniproject.mini.project.enums.Nationality;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="spectator")
@Getter
@Setter
@Builder
public class Spectator {

    @Id
    String id;

    @Column
    int age;

    @ManyToOne
    List<Entries> entries = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    Nationality nationality;

}