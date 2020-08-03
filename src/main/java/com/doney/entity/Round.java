package com.doney.entity;

import javax.persistence.*;

@Entity
@Table(name = "round")
public class Round {
    @Id
    @Column(name = "roundId")
    private Integer roundId;

    @Column(name = "round_number")
    private Integer roundNumber;

    @Column(name = "coverage_link")
    private String coverageLink;

    @Column(name = "coverage_provider")
    private String coverageProvider;


    public Integer getRoundId() {
        return this.roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public Integer getRoundNumber() {
        return this.roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public String getCoverageLink() {
        return this.coverageLink;
    }

    public void setCoverageLink(String coverageLink) {
        this.coverageLink = coverageLink;
    }

    public String getCoverageProvider() {
        return this.coverageProvider;
    }

    public void setCoverageProvider(String coverageProvider) {
        this.coverageProvider = coverageProvider;
    }
}
