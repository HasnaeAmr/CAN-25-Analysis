package com.miniproject.mini.project.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class GeneralStatistics {

    @Id
    Long id;

    @Column
    int moroccanNationality;

    @Column
    int algerianNaionality;

    @Column
    int tunisiamNationalty;

    @Column
    LocalTime earliestArrivalTime;

    @Column
    LocalTime lastestArrivalTime;


}
