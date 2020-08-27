package com.doney.persistence;

import com.doney.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import com.doney.testUtils.Database;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RoundDaoTest {
    GenericDao roundDao;
    GenericDao tournamentDao;
    GenericDao playersInRoundDao;
    GenericDao playerDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        roundDao = new GenericDao(Round.class);
        tournamentDao = new GenericDao(Tournament.class);
        playersInRoundDao = new GenericDao(PlayersInRound.class);
        playerDao = new GenericDao(Player.class);
    }

    @Test
    void getByIdSuccess() {
        Round retrievedRound = (Round) roundDao.getById(1);
        Round expectedRound = new Round( 2, "Front", 9, "testLink", "JomezPro", null, (Tournament) tournamentDao.getById(1));

        assertEquals(expectedRound, retrievedRound);
    }

    @Test
    void insertSuccess() {
        Round newRound = new Round(3, "Front", 9,"testLinkBack9", "JomezPro", null,(Tournament) tournamentDao.getById(1));
        int id = roundDao.insert(newRound);
        assertNotEquals(0, id);
        newRound.setRoundId(id);
        Round roundAfterInsert = (Round) roundDao.getById(id);
        assertEquals(newRound, roundAfterInsert);
    }

    @Test
    void updateSuccess() {
        Round expectedRound = new Round(2, "Front", 9,"newTestLink", "JomezPro", null, (Tournament) tournamentDao.getById(1));
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
        assertEquals(3, rounds.size());
    }

    @Test
    void findPlayersInRoundSuccess() {
        Player expectedPlayerInRound = new Player(1, "Ricky", "Wysocki", "RickyWysocki.jpg");
        Round retrievedRound = (Round) roundDao.getById(2);
        Set<PlayersInRound> retrievedPlayers = retrievedRound.getPlayersInRound();

        assertEquals(4, retrievedPlayers.size());

        Iterator playersList = retrievedPlayers.iterator();
        Player retrievedPlayer = null;
        PlayersInRound currentPlayer;
        while (playersList.hasNext()) {
            currentPlayer = (PlayersInRound) playersList.next();
            if (currentPlayer.getPlayer().getFirstName().equals("Ricky")) {
                retrievedPlayer = currentPlayer.getPlayer();
                break;
            }
        }

        assertEquals(expectedPlayerInRound, retrievedPlayer);
    }

    @Test
    void updatePlayerInRoundSuccess() {
        Round testRound = (Round) roundDao.getById(2);
        Set<PlayersInRound> playersInRoundConnectors = testRound.getPlayersInRound();

        for (PlayersInRound connector : playersInRoundConnectors) {
            playersInRoundDao.delete(connector);
        }

        Set<PlayersInRound> playersInRoundsToUpdate = new HashSet<>();
        playersInRoundsToUpdate.add(new PlayersInRound(testRound, (Player) playerDao.getById(3)));
        playersInRoundsToUpdate.add(new PlayersInRound(
                testRound, (Player) playerDao.getById(4)));
        playersInRoundsToUpdate.add(new PlayersInRound(testRound, (Player) playerDao.getById(5)));
        playersInRoundsToUpdate.add(new PlayersInRound(testRound, (Player) playerDao.getById(6)));

        testRound.setPlayersInRound(playersInRoundsToUpdate);
        roundDao.saveOrUpdate(testRound);
        assertNull(playersInRoundDao.getById(1));

        Round testRoundAfterUpdate = (Round) roundDao.getById(2);
        assertEquals(playersInRoundsToUpdate, testRoundAfterUpdate.getPlayersInRound());
    }

    @Test
    void findTournamentSuccess() {
        Round retrievedRound = (Round) roundDao.getById(1);
        Tournament retrievedTournament = retrievedRound.getTournament();
        Tournament expectedTournamnet = new Tournament("Ledgestone Insurance Open", 2019, "", "");
        assertEquals(expectedTournamnet, retrievedTournament);
    }

    @Test
    void roundDeleteCascadeSuccess() {
        Round roundToDelete = (Round) roundDao.getById(1);
        roundDao.delete(roundToDelete);

        assertNull(playersInRoundDao.getById(5));

        Tournament tournament = (Tournament) tournamentDao.getById(1);
        Set<Round> roundsInTournament = tournament.getRounds();
        Iterator roundsIterator = roundsInTournament.iterator();
        Round currentRound;
        Round roundFound = null;
        while(roundsIterator.hasNext()) {
            currentRound = (Round) roundsIterator.next();
            if (currentRound.getRoundId() == 1) {
                roundFound = currentRound;
                break;
            }
        }

        assertNull(roundFound);
    }

    @Test
    void getCommentatorsSuccess() {
        Round retrievedRound = (Round) roundDao.getById(2);
        Set<Commentators> retrievedCommentators = retrievedRound.getCommentators();
        Set<Commentators> expectedCommentators = new HashSet<>();

        Player expectedPlayer1 = (Player) playerDao.getById(5);
        Player expectedPlayer2 = (Player) playerDao.getById(6);
        Round expectedRound = new Round(3, "Front", 9,"https://www.youtube.com/watch?v=h_whNud9KcM&list=PLZ1LrAadOyA0hTObHHKKHf2ezlUho4gDW&index=6&t=0s", "JomezPro", null, (Tournament) tournamentDao.getById(1));

        expectedCommentators.add(new Commentators(1, expectedRound, expectedPlayer1));
        expectedCommentators.add(new Commentators(2, expectedRound, expectedPlayer2));

        assertEquals(expectedCommentators, retrievedCommentators);
    }
}
