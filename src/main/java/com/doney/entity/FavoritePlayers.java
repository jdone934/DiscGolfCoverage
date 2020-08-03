package com.doney.entity;

import javax.persistence.*;

@Entity
@Table(name = "favorite_players")
public class FavoritePlayers {
    @Id
    @Column(name = "playerId")
    private Integer playerId;

    @Id
    @Column(name = "userId")
    private Integer userId;


    public Integer getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
