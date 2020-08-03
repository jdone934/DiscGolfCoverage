package com.doney.persistence;

import com.doney.entity.Round;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import com.doney.testUtils.Database;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoundDaoTest {
    GenericDao roundDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        roundDao = new GenericDao(Round.class);
    }

    @Test
    void getByIdSuccess() {
        Round retrievedRound = (Round) roundDao.getById(1);
        Round expectedRound = new Round(1, 2, "testLink", "JomezPro");

        assertEquals(expectedRound, retrievedRound);
    }

    @Test
    void insertSuccess() {
        Round newRound = new Round(2, "testLinkBack9", "JomezPro");
        int id = roundDao.insert(newRound);
        assertNotEquals(0, id);
        newRound.setRoundId(id);
        Round roundAfterInsert = (Round) roundDao.getById(id);
        assertEquals(newRound, roundAfterInsert);
    }

    @Test
    void updateSuccess() {
        Round expectedRound = new Round(2, "newTestLink", "JomezPro");
        Round roundToUpdate = (Round) roundDao.getById(1);
        roundToUpdate.setCoverageLink("newTestLink");
        roundDao.saveOrUpdate(roundToUpdate);
        Round roundAfterUpdate = (Round) roundDao.getById(1);
        assertEquals(expectedRound, roundAfterUpdate);
    }

    @Test
    void deleteSuccess() {
        roundDao.delete(roundDao.getById(1));
        assertNull(roundDao.getById(1));
    }

    @Test
    void getAllSuccess() {
        List<Round> rounds = roundDao.getAll();
        assertEquals(1, rounds.size());
    }
}
