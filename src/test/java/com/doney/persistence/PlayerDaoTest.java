package com.doney.persistence;

import com.doney.entity.Player;
import com.doney.entity.PlayersInRound;
import com.doney.entity.Round;
import com.doney.entity.Tournament;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import com.doney.testUtils.Database;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.NotNull;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerDaoTest {
    GenericDao playerDao;
    GenericDao tournamentDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        playerDao = new GenericDao(Player.class);
        tournamentDao = new GenericDao(Tournament.class);
    }

    @Test
    void getByIdSuccess() {
        Player retrievedPlayer = (Player) playerDao.getById(1);
        Player expectedPlayer = new Player(1, "Ricky", "Wysocki", "RickyWysocki.jpg");

        assertEquals(expectedPlayer, retrievedPlayer);
    }

    @Test
    void insertSuccess() {
        Player newPlayer = new Player( "James", "Conrad", "JamesConrad.jpg");
        int id = playerDao.insert(newPlayer);
        assertNotEquals(0, id);
        newPlayer.setPlayerId(id);
        Player playerAfterInsert = (Player) playerDao.getById(id);
        assertEquals(newPlayer, playerAfterInsert);
    }

    @Test
    void updateSuccess() {
        Player expectedPlayer = new Player("Paul", "McBeth", "PaulMcBeth.jpg");
        Player playerToUpdate = (Player) playerDao.getById(1);
        playerToUpdate.setFirstName("Paul");
        playerToUpdate.setLastName("McBeth");
        playerToUpdate.setProfilePicture("PaulMcBeth.jpg");
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
        assertEquals(6, players.size());
    }

    @Test
    void getByPropertyLikeSuccess() {
        List<Player> players = playerDao.getByPropertyLike("firstName", "Rick");
        assertEquals(1, players.size());
        Player playerFound = players.get(0);
        Player expectedPlayer = new Player("Ricky", "Wysocki", "RickyWysocki.jpg");
        assertEquals(expectedPlayer, playerFound);
    }

    @Test
    void getByPropertyLikeMapSuccess() {
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("firstName", "Ric");
        List<Player> players = playerDao.findByPropertyLikeMap(propertyMap);
        assertEquals(1, players.size());
        Player playerFound = players.get(0);
        Player expectedPlayer = new Player("Ricky", "Wysocki", "RickyWysocki.jpg");
        assertEquals(expectedPlayer, playerFound);
    }

    @Test
    void findByPropertyLikeMapSuccess() {
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("firstName", "Ricky");
        propertyMap.put("lastName", "Wysocki");
        List<Player> players = playerDao.findByPropertyEqual(propertyMap);
        assertEquals(1, players.size());
        Player playerFound = players.get(0);
        Player expectedPlayer = new Player("Ricky", "Wysocki", "RickyWysocki.jpg");
        assertEquals(expectedPlayer, playerFound);
    }

    @Test
    void findRoundsPlayerInSuccess() {
        Round expectedRound = new Round( 2, "front", 9, "testLink", "JomezPro", null, (Tournament) tournamentDao.getById(1));
        Player retrievedPlayer = (Player) playerDao.getById(1);
        Set<PlayersInRound> roundsPlayedIn = retrievedPlayer.getRoundsPlayedIn();

        assertEquals(3, roundsPlayedIn.size());

        Iterator rounds = roundsPlayedIn.iterator();
        Round roundFromRetrieved = null;
        PlayersInRound currentRound;
        while (rounds.hasNext()) {
            currentRound = (PlayersInRound) rounds.next();
            if(currentRound.getRound().getRoundId() == 1) {
                roundFromRetrieved = currentRound.getRound();
            }
        }

        assertEquals(expectedRound, roundFromRetrieved);
    }


}
