package com.doney.persistence;

import com.doney.entity.Player;
import com.doney.entity.Tournament;
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

public class TournamentDaoTest {
    GenericDao tournamentDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        tournamentDao = new GenericDao(Tournament.class);
    }

    @Test
    void getByIdSuccess() {
        Tournament retrievedTournament = (Tournament) tournamentDao.getById(1);
        Tournament expectedTournament = new Tournament("Ledgestone", 2019);

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
        Tournament expectedTournament = new Tournament("Idlewild", 2020);
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
        assertEquals(1, tournaments.size());
    }

    @Test
    void getByPropertyLikeSuccess() {
        List<Tournament> tournaments = tournamentDao.getByPropertyLike("name", "Ledge");
        assertEquals(1, tournaments.size());
        Tournament tournamentFound = tournaments.get(0);
        Tournament expectedTournament = new Tournament("Ledgestone", 2019);
        assertEquals(expectedTournament, tournamentFound);
    }

    @Test
    void getByPropertyLikeMapSuccess() {
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("name", "Ledge");
        List<Tournament> tournaments = tournamentDao.findByPropertyLikeMap(propertyMap);
        assertEquals(1, tournaments.size());
        Tournament tournamentFound = tournaments.get(0);
        Tournament expectedTournament = new Tournament("Ledgestone", 2019);
        assertEquals(expectedTournament, tournamentFound);
    }

    @Test
    void findByPropertyLikeMapSuccess() {
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("name", "Ledgestone");
        propertyMap.put("year", 2019);
        List<Tournament> tournaments = tournamentDao.findByPropertyEqual(propertyMap);
        assertEquals(1, tournaments.size());
        Tournament tournamentFound = tournaments.get(0);
        Tournament expectedTournament = new Tournament("Ledgestone", 2019);
        assertEquals(expectedTournament, tournamentFound);
    }
}
