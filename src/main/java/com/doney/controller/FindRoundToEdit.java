package com.doney.controller;

import com.doney.entity.Round;
import com.doney.entity.Tournament;
import com.doney.persistence.GenericDao;
import org.apache.commons.lang3.math.NumberUtils;
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
        urlPatterns = {"/adminOnly/findRoundToEdit"}
)
public class FindRoundToEdit extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao roundDao = new GenericDao(Round.class);
        String searchType = req.getParameter("searchType");
        RequestDispatcher dispatcher = null;

        if (searchType.equals("roundId")) {
            if (NumberUtils.isCreatable(req.getParameter("roundId"))) {
                int roundId = Integer.parseInt(req.getParameter("roundId"));
                Round round = (Round) roundDao.getById(roundId);
                if (round != null) {
                    req.setAttribute("round", round);
                    dispatcher = req.getRequestDispatcher("/editRound.jsp");
                } else {
                    req.setAttribute("errorMessage", "No Round was found with id: " + roundId);
                    dispatcher = req.getRequestDispatcher("/adminHome.jsp");
                }
            } else {
                List<Round> rounds = roundDao.getAll();
                req.setAttribute("rounds", rounds);
                dispatcher = req.getRequestDispatcher("/editRoundRoundSearchResults.jsp");
            }
        } else {
           String tournamentName = req.getParameter("tournamentName");
           int tournamentYear = 0;

            Map<String, Object> propertyMap = new HashMap<>();
            propertyMap.put("name", tournamentName);
            if (NumberUtils.isCreatable(req.getParameter("tournamentYear"))) {
                tournamentYear = Integer.parseInt(req.getParameter("tournamentYear"));
                propertyMap.put("year", tournamentYear);
            }

            GenericDao tournamentDao = new GenericDao(Tournament.class);
            List<Tournament> tournaments = tournamentDao.findByPropertyLikeMap(propertyMap);

            if (tournaments.size() > 0) {
                req.setAttribute("tournaments", tournaments);
                dispatcher = req.getRequestDispatcher("/editRoundTournamentSearchResults.jsp");
            } else {
                String errorMessage = "No tournaments named " + tournamentName;
                if (tournamentYear > 0) {
                    errorMessage += " in year " + tournamentYear;
                }
                errorMessage += " were found";

                req.setAttribute("errorMessage", errorMessage);
                dispatcher = req.getRequestDispatcher("/adminHome.jsp");
            }
        }

        dispatcher.forward(req, resp);
    }
}
