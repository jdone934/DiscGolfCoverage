package com.doney.controller;

import com.doney.entity.Player;
import com.doney.entity.Round;
import com.doney.entity.User;
import com.doney.persistence.GenericDao;
import com.doney.utility.LoggedInUser;
import com.doney.utility.YoutubeHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
    urlPatterns = {"/viewRound"}
)
public class ViewRound extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao roundDao = new GenericDao(Round.class);
        Round round = (Round) roundDao.getById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("round", round);

        YoutubeHelper youtubeHelper = new YoutubeHelper(round.getCoverageLink());
        req.setAttribute("embedUrl", youtubeHelper.getEmbedUrl());

        LoggedInUser userHelper = new LoggedInUser();
        User user = userHelper.getLoggedInUser(req);
        if (user != null) {
            req.setAttribute("user", user);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewRound.jsp");
        dispatcher.forward(req, resp);
    }
}
