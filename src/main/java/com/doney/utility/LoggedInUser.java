package com.doney.utility;

import com.doney.entity.User;
import com.doney.persistence.GenericDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The type Logged in user.
 */
public class LoggedInUser {
    private GenericDao userDao = new GenericDao(User.class);

    /**
     * Gets logged in user.
     *
     * @param req the req
     * @return the logged in user
     */
    public User getLoggedInUser (HttpServletRequest req) {
        User loggedInUser = null;

        List<User> usersResult = userDao.getByPropertyLike("username", req.getRemoteUser());
        if (usersResult.size() > 0) {
            loggedInUser = usersResult.get(0);
        }

        return loggedInUser;
    }
}
