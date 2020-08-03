package com.doney.persistence;

import com.doney.entity.Round;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import com.doney.testUtils.Database;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
