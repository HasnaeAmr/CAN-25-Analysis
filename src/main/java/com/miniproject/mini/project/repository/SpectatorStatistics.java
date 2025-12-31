package com.miniproject.mini.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpectatorStatistics extends JpaRepository<com.miniproject.mini.project.entity.SpectatorStatistics,Long> {
}
