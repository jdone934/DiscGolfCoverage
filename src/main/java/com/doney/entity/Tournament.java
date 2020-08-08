package com.doney.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer tournamentId;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Integer year;

    @Column(name = "tournamentSeries")
    private String tournamentSeries;

    @Column(name = "website")
    private String website;

    public Tournament() {
    }

    public Tournament(String name, Integer year) {
        this.name = name;
        this.year = year;
    }

    public Tournament(String name, Integer year, String website) {
        this.name = name;
        this.year = year;
        this.website = website;
    }

    public Tournament(String name, Integer year, String tournamentSeries, String website) {
        this.name = name;
        this.year = year;
        this.tournamentSeries = tournamentSeries;
        this.website = website;
    }

    public Tournament(Integer tournamentId, String name, Integer year, String tournamentSeries, String website) {
        this.tournamentId = tournamentId;
        this.name = name;
        this.year = year;
        this.tournamentSeries = tournamentSeries;
        this.website = website;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tournament that = (Tournament) o;
        return name.equals(that.name) &&
                year.equals(that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, tournamentSeries, website);
    }
}
