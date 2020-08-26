package com.doney.persistence;

import com.doney.entity.Player;
import com.doney.entity.PlayersInRound;
import com.doney.entity.Round;
import com.doney.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayersInRoundDaoTest {
    GenericDao playersInRoundDao;
    GenericDao roundDao;
    GenericDao playerDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        playersInRoundDao = new GenericDao(PlayersInRound.class);
        roundDao = new GenericDao(Round.class);
        playerDao = new GenericDao(Player.class);
    }

    @Test
    void getByIdSuccess() {
        PlayersInRound retrievedConnector = (PlayersInRound) playersInRoundDao.getById(1);
        PlayersInRound expectedConnector = new PlayersInRound((Round) roundDao.getById(2), (Player) playerDao.getById(1));

        assertEquals(expectedConnector, retrievedConnector);
    }

    @Test
    void insertSuccess() {
        Round round = (Round) roundDao.getById(1);
        Player player = (Player) playerDao.getById(2);
        PlayersInRound newConnector = new PlayersInRound(round, player);
        int id = playersInRoundDao.insert(newConnector);

        PlayersInRound connectorAfterInsert = (PlayersInRound) playersInRoundDao.getById(id);
        assertEquals(newConnector, connectorAfterInsert);
    }

    @Test
    void deleteSuccess() {
        playersInRoundDao.delete(playersInRoundDao.getById(1));
        assertNull(playersInRoundDao.getById(1));
        assertNotNull(playerDao.getById(1));
        assertNotNull(roundDao.getById(2));
    }

    @Test
    void getAllSuccess() {
        assertEquals(7, playersInRoundDao.getAll().size());
    }
}
