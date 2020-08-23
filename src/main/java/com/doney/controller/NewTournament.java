package com.doney.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(
        urlPatterns = {"/adminOnly/newTournament"}
)
public class NewTournament extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/newTournament.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errorMessage;
        String successMessage;

        String name = req.getParameter("tournamentName");
        int year = Integer.parseInt(req.getParameter("year"));
        String tournamentSeries = req.getParameter("tournamentSeries");
        String[] courses = req.getParameterValues("coursesForTournament");

        if (courses != null && courses.length > 0) {

        } else {
            errorMessage = "You need to select at least one course";
            req.setAttribute("tournamentName", name);
            req.setAttribute("year", year);
            req.setAttribute("tournamentSeries", tournamentSeries);
            req.setAttribute("errorMessage", errorMessage);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/newTournament.jsp");
        dispatcher.forward(req, resp);
    }
}
