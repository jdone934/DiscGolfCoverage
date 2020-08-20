package com.doney.controller;

import com.doney.entity.Role;
import com.doney.entity.User;
import com.doney.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Sign up user with new account
 * @author Jacob Doney
 */

@WebServlet(
        urlPatterns = {"/signUp/*"}
)

public class SignUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestServlet = req.getPathInfo();
        String returnPath = "/";

        if (requestServlet != null) {
            returnPath = requestServlet + "?" + req.getQueryString();
        }

        req.setAttribute("path", returnPath);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/signUp.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String returnPath = req.getParameter("path");
        String errorMessage = null;

        if (!password.equals(confirmPassword)) {
            req = formatRequestForReEntry(req, username, email, firstName, lastName, returnPath,
                    "Passwords do not match. Please try again.");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/signUp.jsp");
            dispatcher.forward(req, resp);
        } else {
            User user = new User(username, password, firstName, lastName, email);

            int id = 0;

            if (validUsername(user)) {
                GenericDao userDao = new GenericDao(User.class);
                GenericDao roleDao = new GenericDao(Role.class);

                id = userDao.insert(user);
                roleDao.insert(new Role("user", user.getUsername(), (User) userDao.getById(id)));
            } else {
                errorMessage = "Username already in use. Please try another one.";
            }

            if (id == 0) {
                if (errorMessage.isEmpty()) {
                    req = formatRequestForReEntry(req, username, email, firstName, lastName, returnPath,
                            "There was an error inserting into the Database. Please try again.");
                } else {
                    req = formatRequestForReEntry(req, username, email, firstName, lastName, returnPath, errorMessage);
                }

                RequestDispatcher dispatcher = req.getRequestDispatcher("/signUp.jsp");
                dispatcher.forward(req, resp);
            } else {
                req.setAttribute("path", returnPath);
                resp.sendRedirect("/DiscGolfCoverage/accountCreated" + returnPath);
            }
        }
    }

    private HttpServletRequest formatRequestForReEntry(HttpServletRequest req,
                                                       String username,
                                                       String email,
                                                       String firstName,
                                                       String lastName,
                                                       String path,
                                                       String errorMessage) {

        req.setAttribute("username", username);
        req.setAttribute("email", email);
        req.setAttribute("firstName", firstName);
        req.setAttribute("lastName", lastName);
        req.setAttribute("path", path);
        req.setAttribute("errorMessage", errorMessage);

        return req;
    }

    private boolean validUsername(User user) {
        boolean isValid = true;
        GenericDao userDao = new GenericDao(User.class);

        if (userDao.getByPropertyLike("username", user.getUsername()).size() > 0) {
            isValid = false;
        }

        return isValid;
    }
}
