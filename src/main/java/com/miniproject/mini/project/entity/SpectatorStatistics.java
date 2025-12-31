package com.miniproject.mini.project.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name="spectator-statistics")
@Builder
public class SpectatorStatistics {

    @Id
    Long id;

    @Column
    @OneToOne
    Spectator spectator;

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
