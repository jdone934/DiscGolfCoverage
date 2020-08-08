package com.doney.controller;

import com.doney.entity.Player;
import com.doney.entity.Round;
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
        urlPatterns = {"/searchCoverage"}
)
public class SearchCoverage extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao dao;
        String searchType = req.getParameter("searchType");
        Map<String, Object> propertyMap = new HashMap<>();

        switch (searchType) {
            case "player":
                dao = new GenericDao(Player.class);
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");

                req.setAttribute("searchType", searchType);
                propertyMap.put("firstName", firstName);
                propertyMap.put("lastName", lastName);
                List<Player> results = dao.findByPropertyLikeMap(propertyMap);
                logger.info("Result size: " + results.size());

                req.setAttribute("players", results);
                logger.info("Search for player with first name " + firstName + " and last name " + lastName);
                break;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }

}