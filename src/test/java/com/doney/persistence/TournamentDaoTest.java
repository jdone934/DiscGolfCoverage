package com.doney.persistence;

import com.doney.entity.Course;
import com.doney.entity.Round;
import com.doney.entity.Tournament;
import com.doney.entity.TournamentAtCourse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import com.doney.testUtils.Database;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TournamentDaoTest {
    GenericDao tournamentDao;
    GenericDao roundDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        tournamentDao = new GenericDao(Tournament.class);
        roundDao = new GenericDao(Round.class);
    }

    @Test
    void getByIdSuccess() {
        Tournament retrievedTournament = (Tournament) tournamentDao.getById(1);
        Tournament expectedTournament = new Tournament("Ledgestone Insurance Open", 2019);

        assertEquals(expectedTournament, retrievedTournament);
    }

    @Test
    void insertSuccess() {
        Tournament newTournament = new Tournament("Idlewild", 2020);
        int id = tournamentDao.insert(newTournament);
        assertNotEquals(0, id);
        newTournament.setTournamentId(id);
        Tournament tournamentAfterInsert = (Tournament) tournamentDao.getById(id);
        assertEquals(newTournament, tournamentAfterInsert);
    }

    @Test
    void updateSuccess() {
        Tournament expectedTournament = new Tournament("Idlewild", 2020, "", "");
        Tournament tournamentToUpdate = (Tournament) tournamentDao.getById(1);
        tournamentToUpdate.setName("Idlewild");
        tournamentToUpdate.setYear(2020);
        tournamentDao.saveOrUpdate(tournamentToUpdate);
        Tournament tournamentAfterUpdate = (Tournament) tournamentDao.getById(1);
        assertEquals(expectedTournament, tournamentAfterUpdate);
    }

    @Test
    void deleteSuccess() {
        tournamentDao.delete(tournamentDao.getById(1));
        assertNull(tournamentDao.getById(1));
    }

    @Test
    void getAllSuccess() {
        List<Tournament> tournaments = tournamentDao.getAll();
        assertEquals(2, tournaments.size());
    }

    @Test
    void getByPropertyLikeSuccess() {
        List<Tournament> tournaments = tournamentDao.getByPropertyLike("name", "Ledge");
        assertEquals(1, tournaments.size());
        Tournament tournamentFound = tournaments.get(0);
        Tournament expectedTournament = new Tournament("Ledgestone Insurance Open", 2019, "", "");
        assertEquals(expectedTournament, tournamentFound);
    }

    @Test
    void getByPropertyLikeMapSuccess() {
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("name", "Ledge");
        propertyMap.put("year", 2019);
        List<Tournament> tournaments = tournamentDao.findByPropertyLikeMap(propertyMap);
        assertEquals(1, tournaments.size());
        Tournament tournamentFound = tournaments.get(0);
        Tournament expectedTournament = new Tournament("Ledgestone Insurance Open", 2019, "", "");
        assertEquals(expectedTournament, tournamentFound);
    }

    @Test
    void findByPropertyLikeMapSuccess() {
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("name", "Ledgestone Insurance Open");
        propertyMap.put("year", 2019);
        List<Tournament> tournaments = tournamentDao.findByPropertyEqual(propertyMap);
        assertEquals(1, tournaments.size());
        Tournament tournamentFound = tournaments.get(0);
        Tournament expectedTournament = new Tournament("Ledgestone Insurance Open", 2019, "", "");
        assertEquals(expectedTournament, tournamentFound);
    }

    @Test
    void findRoundsAtTournamentSuccess() {
        Tournament retrievedTournament = (Tournament) tournamentDao.getById(1);
        Set<Round> retrievedRounds = retrievedTournament.getRounds();

        assertEquals(2, retrievedRounds.size());

        Round expectedRound = new Round(2, "front", 9,"testLink",
                        "JomezPro", null, (Tournament) tournamentDao.getById(1));
        Iterator roundsList = retrievedRounds.iterator();
        Round retrievedRound = null;
        Round currentRound;
        while(roundsList.hasNext()) {
            currentRound = (Round) roundsList.next();
            if (currentRound.getRoundId() == 1) {
                retrievedRound = currentRound;
                break;
            }
        }

        assertEquals(expectedRound, retrievedRound);
    }

    @Test
    void tournamentDeleteCascadeSuccess() {
        Tournament tournamentToDelete = (Tournament) tournamentDao.getById(1);
        tournamentDao.delete(tournamentToDelete);

        assertNull(roundDao.getById(1));
        assertNull(roundDao.getById(2));
    }

    @Test
    void findCoursesSuccess() {
        Tournament retrievedTournament = (Tournament) tournamentDao.getById(1);
        Set<TournamentAtCourse> courseList = retrievedTournament.getCoursesAtTournament();

        assertEquals(1, courseList.size());

        Iterator iterator = courseList.iterator();
        Course retrievedCourse = null;
        TournamentAtCourse currentTournamentAtCourse;
        Course currentCourse;
        while(iterator.hasNext()) {
            currentTournamentAtCourse = (TournamentAtCourse) iterator.next();
            currentCourse = currentTournamentAtCourse.getCourse();
            if (currentCourse.getCourseId() == 1) {
                retrievedCourse = currentCourse;
                break;
            }
        }

        Course expectedCourse = new Course("Ledgestone", "Eureka", "IL", "US");

        assertEquals(expectedCourse, retrievedCourse);
    }
}
