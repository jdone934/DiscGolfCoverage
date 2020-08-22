package com.doney.controller;

import com.doney.entity.Player;
import com.doney.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        urlPatterns = {"/adminOnly/newPlayer"}
)
public class NewPlayer extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/newPlayer.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao playerDao = new GenericDao(Player.class);

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put("firstName", firstName);
        propertyMap.put("lastName", lastName);
        List<Player> players = playerDao.findByPropertyEqual(propertyMap);

        Player newPlayer = new Player(firstName, lastName, "test");
        playerDao.insert(newPlayer);
        logger.info("Player inserted: First Name = " + firstName + " Last Name = " + lastName);
        String successMessage = firstName + " " + lastName + " was added";
        req.setAttribute("successMessage", successMessage);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/newPlayer.jsp");
        dispatcher.forward(req, resp);
    }
}
