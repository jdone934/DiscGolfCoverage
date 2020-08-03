package com.doney.entity;

import javax.persistence.*;

@Entity
@Table(name = "favorite_tournaments")
public class FavoriteTournaments {
    @Id
    @Column(name = "tournamentId")
    private Integer tournamentId;

    @Id
    @Column(name = "userId")
    private Integer userId;


    public Integer getTournamentId() {
        return this.tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
