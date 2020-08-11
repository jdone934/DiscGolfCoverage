package com.doney.persistence;

import com.doney.entity.Role;
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

public class RoleDaoTest {
    GenericDao roleDao;
    GenericDao userDao;

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

        roleDao = new GenericDao(Role.class);
        userDao = new GenericDao(User.class);
    }

    /**
     * Verify successful retrieval of a Role
     */
    @Test
    void getByIdSuccess() {
        Role retrievedRole = (Role) roleDao.getById(1);
        User user = (User) userDao.getById(1);
        Role expectedRole = new Role("admin", "jdone934", user);

        assertEquals(expectedRole, retrievedRole);
    }

    /**
     * Verify successful insert of a Role
     */
    @Test
    void insertSuccess() {
        User user = (User) userDao.getById(2);
        Role newRole = new Role("admin", "lktucker", user);
        int id = roleDao.insert(newRole);

        assertNotEquals(0,id);
        Role insertedRole = (Role) roleDao.getById(id);
        assertEquals(newRole, insertedRole);
    }

    /**
     * Verify successful update of a Role
     */
    @Test
    void updateSuccess() {
        User user = (User) userDao.getById(2);
        Role expectedRole = new Role("admin", "lktucker", user);

        Role roleToUpdate = (Role) roleDao.getById(3);
        roleToUpdate.setRoleName(expectedRole.getRoleName());
        roleDao.saveOrUpdate(roleToUpdate);
        Role roleAfterUpdate = (Role) roleDao.getById(3);
        assertEquals(expectedRole, roleAfterUpdate);
    }

    /**
     * Verify successful delete of Role
     */
    @Test
    void deleteSuccess() {
        roleDao.delete(roleDao.getById(1));
        assertNull(roleDao.getById(1));
    }

    /**
     * Verify successful retrieval of all Role
     */
    @Test
    void getAllSuccess() {
        List<Role> roles = roleDao.getAll();
        assertEquals(3, roles.size());
    }
}
