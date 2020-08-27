package com.doney.persistence;

import com.doney.entity.Course;
import com.doney.entity.Player;
import com.doney.entity.Role;
import com.doney.entity.User;
import com.doney.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoTest {
    GenericDao userDao;
    GenericDao playerDao;
    GenericDao courseDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        userDao = new GenericDao(User.class);
        playerDao = new GenericDao(Player.class);
        courseDao = new GenericDao(Course.class);
    }

    @Test
    void getByIdSuccess() {
        User retrievedUser = (User) userDao.getById(1);
        User expectedUser = new User("jdone934", "password", "jdone934@hotmail.com");

        assertEquals(expectedUser, retrievedUser);
    }

    @Test
    void insertSuccess() {
        User newUser = new User("zfabry", "iltms", "zfabry@gmail.com");
        int id = userDao.insert(newUser);
        assertNotEquals(0, id);
        newUser.setUserId(id);
        User userAfterInsert = (User) userDao.getById(id);
        assertEquals(newUser, userAfterInsert);
    }

    @Test
    void updateSuccess() {
        User expectedUser = new User("zfabry", "iltms", "zfabry@gmail.com");
        User userToUpdate = (User) userDao.getById(1);
        userToUpdate.setUsername("zfabry");
        userToUpdate.setPassword("iltms");
        userToUpdate.setEmail("zfabry@gmail.com");
        userDao.saveOrUpdate(userToUpdate);
        User userAfterUpdate = (User) userDao.getById(1);
        assertEquals(expectedUser, userAfterUpdate);
    }

    @Test
    void deleteSuccess() {
        userDao.delete(userDao.getById(1));
        assertNull(userDao.getById(1));
    }

    @Test
    void getAllSuccess() {
        List<User> users = userDao.getAll();
        assertEquals(2, users.size());
    }

    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = userDao.getByPropertyLike("username", "jdone934");
        assertEquals(1, users.size());
        User userFound = users.get(0);
        User expectedUser = new User("jdone934", "password", "jdone934@hotmail.com");
        assertEquals(expectedUser, userFound);
    }

    @Test
    void getRoleSuccess() {
        User retrievedUser = (User) userDao.getById(1);
        Set<Role> retrievedRoles = retrievedUser.getRoles();

        assertEquals(2, retrievedRoles.size());

        Set<Role> expectedRoles = new HashSet<>();
        expectedRoles.add(new Role("admin", "jdone934", retrievedUser));
        expectedRoles.add(new Role("user", "jdone934", retrievedUser));

        assertEquals(expectedRoles, retrievedRoles);
    }

    @Test
    void getPlayerFromFavorite() {
        User retrievedUser = (User) userDao.getById(1);
        Set<Player> favorites = retrievedUser.getFavoritePlayers();

        assertEquals(2, favorites.size());
        for(Player player : favorites) {
            logger.info(player.toString());
        }

        Player expectedPlayer = new Player("Ricky", "Wysocki", "RickyWysocki.jpg");
        assertEquals(true, favorites.contains(expectedPlayer));
    }

    @Test
    void addFavoritePlayerSuccess() {
        User retrievedUser = (User) userDao.getById(1);
        Set<Player> favorites = retrievedUser.getFavoritePlayers();

        Player playerToAdd = (Player) playerDao.getById(3);
        favorites.add(playerToAdd);
        retrievedUser.setFavoritePlayers(favorites);
        userDao.saveOrUpdate(retrievedUser);

        User userAfterUpdate = (User) userDao.getById(1);
        Set<Player> favoritesAfterUpdate = userAfterUpdate.getFavoritePlayers();

        assertEquals(3, favoritesAfterUpdate.size());
        assertEquals(true, favoritesAfterUpdate.contains(playerToAdd));
    }

    @Test
    void deleteFavoritePlayerSuccess() {
        User retrievedUser = (User) userDao.getById(1);
        Set<Player> favorites = retrievedUser.getFavoritePlayers();

        Player playerToDelete = (Player) playerDao.getById(2);
        favorites.remove(playerToDelete);
        retrievedUser.setFavoritePlayers(favorites);
        userDao.saveOrUpdate(retrievedUser);

        User userAfterUpdate = (User) userDao.getById(1);
        Set<Player> favoritesAfterUpdate = userAfterUpdate.getFavoritePlayers();

        assertEquals(1, favoritesAfterUpdate.size());
        assertEquals(false, favoritesAfterUpdate.contains(playerToDelete));
    }

    @Test
    void getCourseFromFavorite() {
        User retrievedUser = (User) userDao.getById(1);
        Set<Course> favorites = retrievedUser.getFavoriteCourses();

        assertEquals(1, favorites.size());

        Course expectedCourse = new Course("Ledgestone", "Eureka", "IL", "US");
        assertEquals(true, favorites.contains(expectedCourse));
    }

    @Test
    void insertFavoriteCourseSuccess() {
        User retrievedUser = (User) userDao.getById(1);
        Set<Course> favorites = retrievedUser.getFavoriteCourses();

        Course newCourse = (Course) courseDao.getById(2);
        favorites.add(newCourse);
        retrievedUser.setFavoriteCourses(favorites);
        userDao.saveOrUpdate(retrievedUser);

        User userAfterUpdate = (User) userDao.getById(1);
        Set<Course> favoritesAfterUpdate = userAfterUpdate.getFavoriteCourses();
        assertEquals(2, favoritesAfterUpdate.size());
        assertEquals(true, favoritesAfterUpdate.contains(newCourse));
    }

    @Test
    void deleteFavoriteCourseSuccess() {
        User retrievedUser = (User) userDao.getById(1);
        Set<Course> favorites = retrievedUser.getFavoriteCourses();

        Course courseToDelete = (Course) courseDao.getById(1);
        favorites.remove(courseToDelete);
        retrievedUser.setFavoriteCourses(favorites);
        userDao.saveOrUpdate(retrievedUser);

        User userAfterUpdate = (User) userDao.getById(1);
        Set<Course> favoritesAfterUpdate = userAfterUpdate.getFavoriteCourses();
        assertEquals(0, favoritesAfterUpdate.size());
        assertEquals(false, favoritesAfterUpdate.contains(courseToDelete));
    }
}
