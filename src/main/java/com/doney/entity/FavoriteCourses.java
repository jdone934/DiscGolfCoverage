package com.doney.entity;

import javax.persistence.*;

@Entity
@Table(name = "favorite_courses")
public class FavoriteCourses {
    @Id
    @Column(name = "courseId")
    private Integer courseId;

    @Id
    @Column(name = "userId")
    private Integer userId;


    public Integer getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
