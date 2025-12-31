package com.miniproject.mini.project.entity;

import com.miniproject.mini.project.enums.TicketType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="entries")
@Getter
@Setter
@Builder
public class Entries {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name = "id")
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

}
