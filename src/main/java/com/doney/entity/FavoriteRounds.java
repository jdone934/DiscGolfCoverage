package com.doney.entity;

import javax.persistence.*;

@Entity
@Table(name = "favorite_rounds")
public class FavoriteRounds {
    @Id
    @Column(name = "roundId")
    private Integer roundId;

    @Id
    @Column(name = "userId")
    private Integer userId;


    public Integer getRoundId() {
        return this.roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
