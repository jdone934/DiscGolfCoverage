package com.doney.persistence;

import com.doney.entity.User;
import com.doney.testUtils.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoTest {
    GenericDao userDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        userDao = new GenericDao(User.class);
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
}
