package com.doney.utility;

import com.doney.entity.*;
import com.doney.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RoundFormHelper {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private HttpServletRequest req;
    private int roundId;
    private String errorMessage;
    private String successMessage;
    private int roundNumber;
    private String coverageLink;
    private String coverageProvider;
    private String frontVsBack;
    private int numberOfHoles;
    private int tournamentId;
    private String[] players;
    Boolean forEdit = false;

    GenericDao roundDao = new GenericDao(Round.class);

    public RoundFormHelper (HttpServletRequest req) {
        this.req = req;
        roundNumber = Integer.parseInt(req.getParameter("roundNumber"));
        coverageLink = req.getParameter("coverageLink");
        coverageProvider = req.getParameter("coverageProvider");
        frontVsBack = req.getParameter("frontVsBack");
        numberOfHoles = Integer.parseInt(req.getParameter("numberOfHoles"));
        tournamentId = 0;
        players = req.getParameterValues("playersInRound");

        String tempRoundId = req.getParameter("roundId");
        if (tempRoundId != null) {
            roundId = Integer.parseInt(tempRoundId);
        }
    }

    public RoundFormHelper (HttpServletRequest req, Boolean forEdit) {
        this(req);
        this.forEdit = forEdit;
    }

    public void processForm() {
        if ((req.getParameter("tournamentForRound")) != null) {
            tournamentId = Integer.parseInt(req.getParameter("tournamentForRound"));
            if (players != null && players.length > 0) {
                GenericDao commentatorDao = new GenericDao(Commentators.class);
                GenericDao tournamentDao = new GenericDao(Tournament.class);

                Tournament tournament = (Tournament) tournamentDao.getById(tournamentId);

                Round roundToInsert;
                Round roundToDeleteFrom;

                if (!forEdit) {
                    roundToInsert = new Round(roundNumber, frontVsBack, numberOfHoles, coverageLink, coverageProvider, tournament);
                    roundDao.insert(roundToInsert);
                } else {
                    roundToDeleteFrom = (Round) roundDao.getById(roundId);
                    GenericDao playersInRoundDao = new GenericDao(PlayersInRound.class);
                    for (PlayersInRound connector : roundToDeleteFrom.getPlayersInRound()) {
                        playersInRoundDao.delete(connector);
                    }

                    for(Commentators connector : roundToDeleteFrom.getCommentators()) {
                        commentatorDao.delete(connector);
                    }

                    roundToInsert = new Round(roundId, roundNumber, frontVsBack, numberOfHoles, coverageLink, coverageProvider, tournament);
                    roundDao.saveOrUpdate(roundToInsert);
                }

                GenericDao playerDao = new GenericDao(Player.class);
                for (String playerIdString : players) {
                    int playerId = Integer.parseInt(playerIdString);
                    Player playerOnRound = (Player) playerDao.getById(playerId);

                    PlayersInRound playerInRoundConnector = new PlayersInRound(roundToInsert, playerOnRound);
                    GenericDao playersInRoundDao = new GenericDao(PlayersInRound.class);
                    playersInRoundDao.insert(playerInRoundConnector);

                    if (!forEdit) {
                        successMessage = "Round successfully added";
                    } else {
                        successMessage = "Round successfully edited";
                    }
                    req.setAttribute("successMessage", successMessage);
                }

                String[] commentatorIds = req.getParameterValues("commentatorsInRound");
                for (String commentatorIdString : commentatorIds) {
                    int commentatorId = Integer.parseInt(commentatorIdString);
                    Player commentator = (Player) playerDao.getById(commentatorId);
                    Commentators commentatorConnector = new Commentators(roundToInsert, commentator);
                    commentatorDao.insert(commentatorConnector);
                }
            } else {
                errorMessage = "You need to enter at least one player";
                req = setRequestAttributes(req, roundNumber, coverageLink, coverageProvider, frontVsBack, numberOfHoles, errorMessage);
            }
        } else {
            errorMessage = "You need to select a tournament";
            req = setRequestAttributes(req, roundNumber, coverageLink, coverageProvider, frontVsBack, numberOfHoles, errorMessage);
        }

        if (forEdit) {
            Round roundAfterUpdates = (Round) roundDao.getById(roundId);
            req.setAttribute("round", roundAfterUpdates);
        }
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

    public HttpServletRequest getReq() {
        return req;
    }

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }
}
