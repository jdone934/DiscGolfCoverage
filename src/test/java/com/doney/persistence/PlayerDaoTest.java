package com.doney.persistence;

import com.doney.entity.Player;
import com.doney.entity.Round;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import com.doney.testUtils.Database;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerDaoTest {
    GenericDao playerDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        playerDao = new GenericDao(Player.class);
    }

    @Test
    void getByIdSuccess() {
        Player retrievedPlayer = (Player) playerDao.getById(1);
        Player expectedPlayer = new Player(1, "Ricky", "Wysocki", "RickyWysocki.jpg");

        assertEquals(expectedPlayer, retrievedPlayer);
    }

    @Test
    void insertSuccess() {
        Player newPlayer = new Player(2, "Paul", "McBeth");
        int id = playerDao.insert(newPlayer);
        assertNotEquals(0, id);
        newPlayer.setPlayerId(id);
        Player playerAfterInsert = (Player) playerDao.getById(id);
        assertEquals(newPlayer, playerAfterInsert);
    }

    @Test
    void updateSuccess() {
        Player expectedPlayer = new Player(1, "Paul", "McBeth");
        Player playerToUpdate = (Player) playerDao.getById(1);
        playerToUpdate.setFirstName("Paul");
        playerToUpdate.setLastName("McBeth");
        playerDao.saveOrUpdate(playerToUpdate);
        Player playerAfterUpdate = (Player) playerDao.getById(1);
        assertEquals(expectedPlayer, playerAfterUpdate);
    }

    @Test
    void deleteSuccess() {
        playerDao.delete(playerDao.getById(1));
        assertNull(playerDao.getById(1));
    }

    @Test
    void getAllSuccess() {
        List<Player> players = playerDao.getAll();
        assertEquals(1, players.size());
    }

    @Test
    void getByPropertyLikeSuccess() {
        List<Player> players = playerDao.getByPropertyLike("firstName", "Ricky");
        assertEquals(1, players.size());
        Player playerFound = players.get(0);
        Player expectedPlayer = new Player(1, "Ricky", "Wysocki");
        assertEquals(expectedPlayer, playerFound);
    }

    @Test
    void findByPropertyLikeMapSuccess() {
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("firstName", "Ricky");
        List<Player> players = playerDao.findByPropertyEqual(propertyMap);
        assertEquals(1, players.size());
        Player playerFound = players.get(0);
        Player expectedPlayer = new Player(1, "Ricky", "Wysocki");
        assertEquals(expectedPlayer, playerFound);
    }
}
