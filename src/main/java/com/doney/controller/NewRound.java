package com.doney.controller;

import com.doney.entity.Player;
import com.doney.entity.PlayersInRound;
import com.doney.entity.Round;
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

@WebServlet(
        urlPatterns = {"/adminOnly/newRound"}
)
public class NewRound extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/newRound.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errorMessage;
        String successMessage;

        int roundNumber = Integer.parseInt(req.getParameter("roundNumber"));
        String coverageLink = req.getParameter("coverageLink");
        String coverageProvider = req.getParameter("coverageProvider");
        String frontVsBack = req.getParameter("frontVsBack");
        int numberOfHoles = Integer.parseInt(req.getParameter("numberOfHoles"));
        int tournamentId = Integer.parseInt(req.getParameter("tournamentForRound"));
        String[] players = req.getParameterValues("playersInRound");

        if (tournamentId != 0) {
            if (players != null && players.length > 0) {
                GenericDao tournamentDao = new GenericDao(Tournament.class);
                Tournament tournament = (Tournament) tournamentDao.getById(tournamentId);

                GenericDao roundDao = new GenericDao(Round.class);
                Round roundToInsert = new Round(roundNumber, frontVsBack, numberOfHoles, coverageLink, coverageProvider, tournament);
                roundDao.insert(roundToInsert);

                GenericDao playerDao = new GenericDao(Player.class);
                for (String playerIdString : players) {
                    int playerId = Integer.parseInt(playerIdString);
                    Player playerOnRound = (Player) playerDao.getById(playerId);

                    PlayersInRound playerInRoundConnector = new PlayersInRound(roundToInsert, playerOnRound);
                    GenericDao playersInRoundDao = new GenericDao(PlayersInRound.class);
                    playersInRoundDao.insert(playerInRoundConnector);

                    successMessage = "Round successfully added";
                    req.setAttribute("successMessage", successMessage);
                }
            } else {
                errorMessage = "You need to enter at least one player";
                req = setRequestAttributes(req, roundNumber, coverageLink, coverageProvider, frontVsBack, numberOfHoles, errorMessage);
            }
        } else {
            errorMessage = "You need to select a tournament";
            req = setRequestAttributes(req, roundNumber, coverageLink, coverageProvider, frontVsBack, numberOfHoles, errorMessage);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/newRound.jsp");
        dispatcher.forward(req, resp);
    }

    private HttpServletRequest setRequestAttributes(HttpServletRequest request,
                                                    int roundNumber,
                                                    String coverageLink,
                                                    String coverageProvider,
                                                    String frontOrBack,
                                                    int numberOfHoles,
                                                    String errorMessage) {
        request.setAttribute("roundNumber", roundNumber);
        request.setAttribute("coverageLink", coverageLink);
        request.setAttribute("coverageProvider", coverageProvider);
        request.setAttribute("frontOrBack", frontOrBack);
        request.setAttribute("numberOfHoles", numberOfHoles);
        request.setAttribute("errorMessage", errorMessage);

        return request;
    }
}
