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

    @Query("SELECT COUNT(DISTINCT e.matchId) FROM entries e")
    int countTotalMatchs();

    @Query("SELECT SUM(e.vipTickets) FROM entries e WHERE e.spectator = :spectator")
    int totalVipTicketsBySpectator(@Param("spectator") Spectator spectator);

    @Query("SELECT SUM(e.premiumTickets) FROM entries e WHERE e.spectator = :spectator")
    int totalPremiumTicketsBySpectator(@Param("spectator") Spectator spectator);

    @Query("SELECT SUM(e.standardTickets) FROM entries e WHERE e.spectator = :spectator")
    int totalStandardTicketsBySpectator(@Param("spectator") Spectator spectator);

    @Query("SELECT SUM(e.economyTickets) FROM entries e WHERE e.spectator = :spectator")
    int totalEconomyTicketsBySpectator(@Param("spectator") Spectator spectator);

}
