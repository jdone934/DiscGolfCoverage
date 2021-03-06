package com.doney.persistence;

import com.doney.entity.Course;
import com.doney.entity.Tournament;
import com.doney.entity.TournamentAtCourse;
import com.doney.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseDaoTest {
    GenericDao courseDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        courseDao = new GenericDao(Course.class);
    }

    @Test
    void getByIdSuccess() {
        Course retrievedCourse = (Course) courseDao.getById(1);
        Course expectedCourse = new Course("Ledgestone", "Eureka", "IL", "US");

        assertEquals(expectedCourse, retrievedCourse);
    }

    @Test
    void insertSuccess() {
        Course newCourse = new Course("Token Creek", "Madison", "WI", "US");
        int id = courseDao.insert(newCourse);
        assertNotEquals(0, id);
        newCourse.setCourseId(id);
        Course courseAfterInsert = (Course) courseDao.getById(id);
        assertEquals(newCourse, courseAfterInsert);
    }

    @Test
    void updateSuccess() {
        Course expectedCourse = new Course("Token Creek", "Madison", "WI", "US");
        Course courseToUpdate = (Course) courseDao.getById(1);
        courseToUpdate.setName("Token Creek");
        courseToUpdate.setLocationCity("Madison");
        courseToUpdate.setLocationState("WI");
        courseToUpdate.setLocationCountry("US");
        courseDao.saveOrUpdate(courseToUpdate);
        Course courseAfterUpdate = (Course) courseDao.getById(1);
        assertEquals(expectedCourse, courseAfterUpdate);
    }

    @Test
    void deleteSuccess() {
        courseDao.delete(courseDao.getById(1));
        assertNull(courseDao.getById(1));
    }

    @Test
    void getAllSuccess() {
        List<Course> courses = courseDao.getAll();
        assertEquals(2, courses.size());
    }

    @Test
    void getByPropertyLikeSuccess() {
        List<Course> courses = courseDao.getByPropertyLike("name", "Ledge");
        assertEquals(1, courses.size());
        Course courseFound = courses.get(0);
        Course expectedCourse = new Course("Ledgestone", "Eureka", "IL", "US");
        assertEquals(expectedCourse, courseFound);
    }

    @Test
    void getByPropertyLikeMapSuccess() {
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("name", "Ledge");
        List<Course> courses = courseDao.findByPropertyLikeMap(propertyMap);
        assertEquals(1, courses.size());
        Course courseFound = courses.get(0);
        Course expectedCourse = new Course("Ledgestone", "Eureka", "IL", "US");
        assertEquals(expectedCourse, courseFound);
    }

    @Test
    void findByPropertyLikeMapSuccess() {
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("name", "Ledgestone");
        propertyMap.put("locationState", "IL");
        propertyMap.put("locationCity", "Eureka");
        propertyMap.put("locationCountry", "US");
        List<Course> courses = courseDao.findByPropertyEqual(propertyMap);
        assertEquals(1, courses.size());
        Course courseFound = courses.get(0);
        Course expectedCourse = new Course("Ledgestone", "Eureka", "IL", "US");
        assertEquals(expectedCourse, courseFound);
    }

    @Test
    void getTournamentsAtCourse() {
        Course retrievedCourse = (Course) courseDao.getById(1);
        Set<TournamentAtCourse> tournamentList = retrievedCourse.getTournamentsAtCourse();

        assertEquals(1, tournamentList.size());

        Iterator iterator = tournamentList.iterator();
        Tournament retrievedTournament = null;
        TournamentAtCourse currentTournamentAtCourse;
        Tournament currentTournament;
        while (iterator.hasNext()) {
            currentTournamentAtCourse = (TournamentAtCourse) iterator.next();
            currentTournament = currentTournamentAtCourse.getTournament();
            if(currentTournament.getTournamentId() == 1) {
                retrievedTournament = currentTournament;
                break;
            }
        }

        Tournament expectedTournament = new Tournament("Ledgestone Insurance Open", 2019, "", "");
        assertEquals(expectedTournament, retrievedTournament);
    }
}
