package com.doney.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Round")
@Table(name = "round")
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer roundId;

    @NotNull
    @Column(name = "round_number")
    private Integer roundNumber;

    @NotNull
    @Column(name = "front_vs_back")
    private String frontVsBack;

    @NotNull
    @Column(name = "number_of_holes")
    private int numberOfHoles;

    @NotNull
    @Column(name = "coverage_link")
    private String coverageLink;

    @NotNull
    @Column(name = "coverage_provider")
    private String coverageProvider;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<PlayersInRound> playersInRound = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "tournamentId")
    @JsonIgnore
    private Tournament tournament;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Commentators> commentators = new HashSet<>();

    public Round() {
    }

    public Round(Integer roundNumber, String coverageLink, String coverageProvider) {
        this.roundNumber = roundNumber;
        this.coverageLink = coverageLink;
        this.coverageProvider = coverageProvider;
    }

    public Round (Integer roundId, Integer roundNumber, String coverageLink, String coverageProvider) {
        this.roundId = roundId;
        this.roundNumber = roundNumber;
        this.coverageLink = coverageLink;
        this.coverageProvider = coverageProvider;
    }

    public Round(Integer roundNumber, String coverageLink, String coverageProvider, Set<PlayersInRound> playersInRound, Tournament tournament) {
        this.roundNumber = roundNumber;
        this.coverageLink = coverageLink;
        this.coverageProvider = coverageProvider;
        this.playersInRound = playersInRound;
        this.tournament = tournament;
    }

    public Round(Integer roundId, Integer roundNumber, String coverageLink, String coverageProvider, Set<PlayersInRound> playersInRound, Tournament tournament) {
        this.roundId = roundId;
        this.roundNumber = roundNumber;
        this.coverageLink = coverageLink;
        this.coverageProvider = coverageProvider;
        this.playersInRound = playersInRound;
        this.tournament = tournament;
    }

    public Round(Integer roundNumber, String frontVsBack, int numberOfHoles, String coverageLink, String coverageProvider, Set<PlayersInRound> playersInRound, Tournament tournament) {
        this.roundNumber = roundNumber;
        this.frontVsBack = frontVsBack;
        this.numberOfHoles = numberOfHoles;
        this.coverageLink = coverageLink;
        this.coverageProvider = coverageProvider;
        this.playersInRound = playersInRound;
        this.tournament = tournament;
    }

    public Round(Integer roundNumber, String frontVsBack, int numberOfHoles, String coverageLink, String coverageProvider, Tournament tournament) {
        this.roundNumber = roundNumber;
        this.frontVsBack = frontVsBack;
        this.numberOfHoles = numberOfHoles;
        this.coverageLink = coverageLink;
        this.coverageProvider = coverageProvider;
        this.tournament = tournament;
    }

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

    public Set<PlayersInRound> getPlayersInRound() {
        return playersInRound;
    }

    public void setPlayersInRound(Set<PlayersInRound> playersInRound) {
        this.playersInRound = playersInRound;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public String getFrontVsBack() {
        return frontVsBack;
    }

    public void setFrontVsBack(String frontVsBack) {
        this.frontVsBack = frontVsBack;
    }

    public int getNumberOfHoles() {
        return numberOfHoles;
    }

    public void setNumberOfHoles(int numberOfHoles) {
        this.numberOfHoles = numberOfHoles;
    }

    public Set<Commentators> getCommentators() {
        return commentators;
    }

    public void setCommentators(Set<Commentators> commentators) {
        this.commentators = commentators;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return numberOfHoles == round.numberOfHoles &&
                Objects.equals(roundNumber, round.roundNumber) &&
                Objects.equals(frontVsBack, round.frontVsBack) &&
                Objects.equals(coverageLink, round.coverageLink) &&
                Objects.equals(coverageProvider, round.coverageProvider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundNumber, frontVsBack, numberOfHoles, coverageLink, coverageProvider);
    }
}
