package com.doney.controller;

import com.doney.entity.Round;
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
        urlPatterns = "/adminOnly/editRound"
)
public class EditRound extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao roundDao = new GenericDao(Round.class);
        Round round = (Round) roundDao.getById(Integer.parseInt(req.getParameter("roundId")));
        req.setAttribute("round", round);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editRound.jsp");
        dispatcher.forward(req, resp);
    }
}
