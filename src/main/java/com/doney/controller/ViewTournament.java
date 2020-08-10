package com.doney.controller;

import com.doney.entity.Tournament;
import com.doney.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/viewTournament"}
)
public class ViewTournament extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao tournamentDao = new GenericDao(Tournament.class);
        Tournament tournament = (Tournament) tournamentDao.getById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("tournament", tournament);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewTournament.jsp");
        dispatcher.forward(req, resp);
    }
}
