package com.miniproject.mini.project.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpectatorEntry {

    private String spectatorId;
    private String matchId;
    private LocalDateTime entryTime;
    private String gate;
    private String ticketNumber;
    private int age;
    private String nationality;
    private String ticketType;
    private SeatLocation seatLocation;
}