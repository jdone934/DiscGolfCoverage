package com.doney.controller;

import com.doney.entity.User;
import com.doney.utility.LoggedInUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Redirect to previous page after logout
 * @author Jacob Doney
 */

@WebServlet(
        urlPatterns = {"/logout/*", "/adminOnly/logout/*"}
)

public class Logout extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoggedInUser helper = new LoggedInUser();
        User user = helper.getLoggedInUser(req);
        logger.info("Username: " + req.getRemoteUser());
        logger.info("User " + user.getUserId() + " logged out");

        HttpSession session = req.getSession();
        session.invalidate();

        resp.sendRedirect("/DiscGolfCoverage");
    }
}
