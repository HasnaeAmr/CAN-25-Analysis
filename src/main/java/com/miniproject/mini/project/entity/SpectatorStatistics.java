package com.miniproject.mini.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="spectator_statistics")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpectatorStatistics {

    @Id
    @GeneratedValue
    Long id;

    @OneToOne(mappedBy = "spectatorStatistics", cascade = CascadeType.ALL, orphanRemoval = true)
    private Spectator spectator;

    @Column
    double loyalty;

    @Column
    int matchs;

    @Column
    int vipTickets;

    @Column
    int premiumTickets;

    @Column
    int standardTickets;

    @Column
    int economyTickets;

    @Column
    String category;

}
