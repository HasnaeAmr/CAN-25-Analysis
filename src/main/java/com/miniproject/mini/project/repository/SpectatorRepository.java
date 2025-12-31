package com.miniproject.mini.project.repository;

import com.miniproject.mini.project.entity.Spectator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpectatorRepository extends JpaRepository<Spectator, String> {
}
