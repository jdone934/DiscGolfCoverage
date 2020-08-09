package com.doney.entity;

import javax.persistence.*;

@Entity
@Table(name = "commentators")
public class Commentators {
    @ManyToOne
    @JoinColumn(name = "roundId")
    private Round round;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player player;

    public Commentators() {
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
