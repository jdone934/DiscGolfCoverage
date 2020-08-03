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

        RequestDispatcher dispatcher = req.getRequestDispatcher("/playerProfile.jsp");
        dispatcher.forward(req, resp);
    }

}