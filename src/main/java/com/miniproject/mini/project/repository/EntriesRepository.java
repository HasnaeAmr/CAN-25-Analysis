package com.miniproject.mini.project.repository;

import com.miniproject.mini.project.entity.Entries;
import com.miniproject.mini.project.entity.Spectator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntriesRepository extends JpaRepository<Entries, Long> {
    int countBySpectator(Spectator spectator);

    @Query("SELECT COUNT(DISTINCT e.matchId) FROM Entries e")
    int countTotalMatchs();

    @Query("""
    SELECT COUNT(e)
    FROM Entries e
    WHERE e.spectator = :spectator
      AND e.ticketType = VIP
""")
    int totalVipTicketsBySpectator(@Param("spectator") Spectator spectator);

    @Query("""
    SELECT COUNT(e)
    FROM Entries e
    WHERE e.spectator = :spectator
      AND e.ticketType = PREMIUM
""")
    int totalPremiumTicketsBySpectator(@Param("spectator") Spectator spectator);

    @Query("""
    SELECT COUNT(e)
    FROM Entries e
    WHERE e.spectator = :spectator
      AND e.ticketType = STANDARD
""")
    int totalStandardTicketsBySpectator(@Param("spectator") Spectator spectator);

    @Query("""
    SELECT COUNT(e)
    FROM Entries e
    WHERE e.spectator = :spectator
      AND e.ticketType = ECONOMY
""")
    int totalEconomyTicketsBySpectator(@Param("spectator") Spectator spectator);
    boolean existsBySpectatorAndMatchId(Spectator spectator, String matchId);

}
