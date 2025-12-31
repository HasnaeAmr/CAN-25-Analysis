package com.miniproject.mini.project.repository;

import com.miniproject.mini.project.entity.SpectatorStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpectatorStatisticsRepository extends JpaRepository<SpectatorStatistics,Long> {
}
