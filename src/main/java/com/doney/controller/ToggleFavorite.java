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
import java.util.Collections;
import java.util.List;
import java.util.Set;

@WebServlet(
        urlPatterns = {"/toggleFavorite"}
)
public class ToggleFavorite extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao userDao = new GenericDao(User.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> paramList = Collections.list(req.getParameterNames());
        String searchType = paramList.get(0);
        int id = Integer.parseInt(req.getParameter(searchType));
        searchType = searchType.substring(0, searchType.length() - 2);

        LoggedInUser helper = new LoggedInUser();
        User user = helper.getLoggedInUser(req);

        switch (searchType) {
            case "player":
                GenericDao playerDao = new GenericDao(Player.class);
                Player player = (Player) playerDao.getById(id);

                Set<Player> favorites = user.getFavoritePlayers();
                String toggleType;
                if (favorites.contains(player)) {
                    favorites.remove(player);
                    toggleType = "removed";
                } else {
                    favorites.add(player);
                    toggleType = "added";
                }

                logger.info("User " + user.getUserId() + " " + toggleType + " " + player.getFirstName() + " "
                                + player.getLastName() + " as a favorite");

                user.setFavoritePlayers(favorites);
                userDao.saveOrUpdate(user);
                break;
        }
    }
}
