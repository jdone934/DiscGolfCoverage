package com.doney.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer courseId;

    @Column(name = "name")
    private String name;

    @Column(name = "location_city")
    private String locationCity;

    @Column(name = "location_state")
    private String locationState;

    @Column(name = "location_country")
    private String locationCountry;

    @Column(name = "website")
    private String website;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<TournamentAtCourse> tournamentsAtCourse = new HashSet<>();

    @ManyToMany(mappedBy = "favoriteCourses", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<User> favoritedBy = new HashSet<>();

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    public Course(String name, String locationCity, String locationState, String locationCountry) {
        this.name = name;
        this.locationCity = locationCity;
        this.locationState = locationState;
        this.locationCountry = locationCountry;
    }

    public Course(String name, String locationCity, String locationState, String locationCountry, String website) {
        this.name = name;
        this.locationCity = locationCity;
        this.locationState = locationState;
        this.locationCountry = locationCountry;
        this.website = website;
    }

    public Course(Integer courseId, String name, String locationCity, String locationState, String locationCountry, String website) {
        this.courseId = courseId;
        this.name = name;
        this.locationCity = locationCity;
        this.locationState = locationState;
        this.locationCountry = locationCountry;
        this.website = website;
    }

    public Integer getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationCity() {
        return this.locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getLocationState() {
        return this.locationState;
    }

    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<TournamentAtCourse> getTournamentsAtCourse() {
        return tournamentsAtCourse;
    }

    public void setTournamentsAtCourse(Set<TournamentAtCourse> tournamentsAtCourse) {
        this.tournamentsAtCourse = tournamentsAtCourse;
    }

    public Set<User> getFavoritedBy() {
        return favoritedBy;
    }

    public void setFavoritedBy(Set<User> favoritedBy) {
        this.favoritedBy = favoritedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return name.equals(course.name) &&
                Objects.equals(locationCity, course.locationCity) &&
                Objects.equals(locationState, course.locationState) &&
                Objects.equals(locationCountry, course.locationCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, locationCity, locationState, locationCountry);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", locationCity='" + locationCity + '\'' +
                ", locationState='" + locationState + '\'' +
                ", locationCountry='" + locationCountry + '\'' +
                '}';
    }
}
