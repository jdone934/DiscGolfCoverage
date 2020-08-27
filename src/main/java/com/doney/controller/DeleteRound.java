package com.doney.controller;

import com.doney.entity.Round;
import com.doney.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/adminOnly/deleteRound"}
)
public class DeleteRound extends HttpServlet {
    GenericDao roundDao = new GenericDao(Round.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roundId = Integer.parseInt(req.getParameter("roundId"));
        roundDao.delete(roundDao.getById(roundId));
        req.setAttribute("successMessage", "Round was successfully deleted");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/adminHome.jsp");
        dispatcher.forward(req, resp);
    }
}