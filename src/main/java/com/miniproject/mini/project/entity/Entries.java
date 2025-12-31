package com.miniproject.mini.project.entity;

import com.miniproject.mini.project.enums.TicketType;
import com.miniproject.mini.project.model.SeatLocation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="entries")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entries {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name = "spectator_id")
    Spectator spectator;

    @Column
    String matchId;

    @Column
    LocalDateTime entryTime;

    @Column
    String gate;

    @Column
    String ticketNumber;

    @Enumerated(EnumType.STRING)
    TicketType ticketType;

    @Column
    String tribune;
    @Column
    String bloc;
    @Column
    int rang;
    @Column
    int siege;

}
