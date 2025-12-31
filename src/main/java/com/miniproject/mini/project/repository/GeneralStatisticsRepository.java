package com.miniproject.mini.project.repository;

import com.miniproject.mini.project.entity.GeneralStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralStatisticsRepository extends JpaRepository<GeneralStatistics,Long> {
}
