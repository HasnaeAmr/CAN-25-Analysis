package com.miniproject.mini.project.entity;

import com.miniproject.mini.project.enums.Nationality;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="spectator")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Spectator {

    @Id
    String id;

    @Column
    int age;

    @OneToMany(mappedBy = "spectator", cascade = CascadeType.ALL)
    List<Entries> entries = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    Nationality nationality;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "statistics_id")
    SpectatorStatistics spectatorStatistics;

}