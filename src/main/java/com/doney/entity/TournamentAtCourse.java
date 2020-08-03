package com.doney.entity;

import javax.persistence.*;

@Entity
@Table(name = "tournament_at_course")
public class TournamentAtCourse {
    @Id
    @Column(name = "courseId")
    private Integer courseId;

    @Id
    @Column(name = "tournamentId")
    private Integer tournamentId;


    public Integer getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTournamentId() {
        return this.tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }
}
