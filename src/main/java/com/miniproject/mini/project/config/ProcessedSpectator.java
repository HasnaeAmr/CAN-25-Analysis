package com.miniproject.mini.project.config;

import com.miniproject.mini.project.entity.Entries;
import com.miniproject.mini.project.entity.Spectator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProcessedSpectator {
    private Spectator spectator;
    private Entries entry;
}