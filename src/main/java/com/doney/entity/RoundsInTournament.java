package com.doney.entity;

import javax.persistence.*;

@Entity
@Table(name = "rounds_in_tournament")
public class RoundsInTournament {
    @Id
    @Column(name = "roundId")
    private Integer roundId;

    @Id
    @Column(name = "tournamentId")
    private Integer tournamentId;


    public Integer getRoundId() {
        return this.roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getTournamentId() {
        return this.tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }
}
