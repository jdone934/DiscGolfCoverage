package com.doney.controller;

import com.doney.entity.Player;
import com.doney.entity.Tournament;
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
        urlPatterns = {"/searchByTournament"}
)
public class TournamentSearch extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao dao  = new GenericDao(Tournament.class);;
        String searchType = req.getParameter("searchType");
        Map<String, Object> propertyMap = new HashMap<>();

        String tournamentName = req.getParameter("tournamentName");
        String tournamentYearString = req.getParameter("tournamentYear");
        Integer tournamentYear = 0;
        if (tournamentYearString != "") {
            tournamentYear = Integer.parseInt(tournamentYearString);
            propertyMap.put("year", tournamentYear);
        }

        req.setAttribute("searchType", searchType);
        propertyMap.put("name", tournamentName);

        List<Player> results = dao.findByPropertyLikeMap(propertyMap);

        req.setAttribute("tournaments", results);

        String loggingMessage = "Search for tournaments with name " + tournamentName;
        if (tournamentYear > 0) {
            loggingMessage += " and in year " + tournamentYear;
        }
        logger.info(loggingMessage);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}
