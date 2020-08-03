package com.doney.entity;

import javax.persistence.*;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @Column(name = "tournamentId")
    private Integer tournamentId;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Integer year;

    @Column(name = "tournamentSeries")
    private String tournamentSeries;

    @Column(name = "website")
    private String website;


    public Integer getTournamentId() {
        return this.tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTournamentSeries() {
        return this.tournamentSeries;
    }

    public void setTournamentSeries(String tournamentSeries) {
        this.tournamentSeries = tournamentSeries;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
