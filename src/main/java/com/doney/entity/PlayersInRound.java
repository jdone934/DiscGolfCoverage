package com.doney.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "players_in_round")
public class PlayersInRound {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "players_in_round_id")
    private Integer playersInRoundId;

    @ManyToOne
    @JoinColumn(name = "roundId")
    private Round round;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player player;

    public PlayersInRound() {
    }

    public Integer getPlayersInRoundId() {
        return playersInRoundId;
    }

    public void setPlayersInRoundId(Integer playersInRoundId) {
        this.playersInRoundId = playersInRoundId;
    }

    public Round getRound() {
        return this.round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayersInRound that = (PlayersInRound) o;
        return Objects.equals(round, that.round) &&
                Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, player);
    }
}
