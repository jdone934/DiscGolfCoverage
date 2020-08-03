package com.doney.entity;

import javax.persistence.*;

@Entity
@Table(name = "commentators")
public class Commentators {
    @Id
    @Column(name = "playerId")
    private Integer playerId;

    @Id
    @Column(name = "roundId")
    private Integer roundId;


    public Integer getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getRoundId() {
        return this.roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }
}
