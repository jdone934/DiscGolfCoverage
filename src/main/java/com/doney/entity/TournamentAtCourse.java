package com.doney.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tournament_at_course")
public class TournamentAtCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "tournaments_at_course_id")
    private Integer tournamentAtCourseId;

    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "tournamentId")
    private Tournament tournament;

    public TournamentAtCourse() {
    }

    public Integer getTournamentAtCourseId() {
        return tournamentAtCourseId;
    }

    public void setTournamentAtCourseId(Integer tournamentAtCourseId) {
        this.tournamentAtCourseId = tournamentAtCourseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
