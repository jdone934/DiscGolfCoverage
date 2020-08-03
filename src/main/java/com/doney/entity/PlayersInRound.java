package com.doney.entity;

import javax.persistence.*;

@Entity
@Table(name = "players_in_round")
public class PlayersInRound {
    @Id
    @Column(name = "roundId")
    private Integer roundId;

    @Id
    @Column(name = "playerId")
    private Integer playerId;


    public Integer getRoundId() {
        return this.roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}
