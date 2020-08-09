package com.doney.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "commentators")
public class Commentators {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "commentators_id")
    private Integer commentatorsId;


    @ManyToOne
    @JoinColumn(name = "roundId")
    private Round round;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player player;

    public Commentators() {
    }

    public Commentators(Round round, Player player) {
        this.round = round;
        this.player = player;
    }

    public Commentators(Integer commentatorsId, Round round, Player player) {
        this.commentatorsId = commentatorsId;
        this.round = round;
        this.player = player;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commentators that = (Commentators) o;
        return Objects.equals(round, that.round) &&
                Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, player);
    }
}
