package com.miniproject.mini.project.entity;

import com.miniproject.mini.project.enums.TicketType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="entries")
public class Entries {

    @Id
    Long id;

    @Column
    Long SprectatorID;

    @Column
    Long matchId;

    @Column
    LocalDate entryTime;
    @Column
    String gate;

    @Column
    String ticketNumber;

    @Enumerated(EnumType.STRING)
    TicketType ticketType;

}
