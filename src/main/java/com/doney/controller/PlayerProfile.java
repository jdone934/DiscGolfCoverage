package com.doney.controller;

import com.doney.entity.Player;
import com.doney.entity.User;
import com.doney.persistence.GenericDao;
import com.doney.utility.LoggedInUser;
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
        urlPatterns = {"/playerProfile"}
)
public class PlayerProfile extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao playerDao = new GenericDao(Player.class);
        Player player = (Player) playerDao.getById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("player", player);

        LoggedInUser helper = new LoggedInUser();
        User loggedInUser = helper.getLoggedInUser(req);

        if (loggedInUser != null) {
            if (loggedInUser.getFavoritePlayers().contains(player)) {
                req.setAttribute("favoritePlayer", "true");
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/playerProfile.jsp");
        dispatcher.forward(req, resp);
    }

}