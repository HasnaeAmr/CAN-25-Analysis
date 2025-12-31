package com.miniproject.mini.project.repository;

import com.miniproject.mini.project.entity.Entries;
import com.miniproject.mini.project.entity.Spectator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntriesRepository extends JpaRepository<Entries, Long> {
    int countBySpectator(Spectator spectator);

}
