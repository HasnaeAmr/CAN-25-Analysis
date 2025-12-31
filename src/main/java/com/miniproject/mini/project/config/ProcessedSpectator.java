package com.miniproject.mini.project.config;

import com.miniproject.mini.project.entity.Entries;
import com.miniproject.mini.project.entity.Spectator;
import com.miniproject.mini.project.entity.SpectatorStatistics;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProcessedSpectator {
    private Spectator spectator;
    private Entries entry;
    SpectatorStatistics spectatorStatistics;
}