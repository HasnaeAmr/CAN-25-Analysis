package com.miniproject.mini.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name="spectator-statistics")
@Builder
public class SpectatorStatistics {

    @Id
    Long id;

    @Column
    Long spectatorID;

    @Column
    float loyalty;

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
    String classification;

}
