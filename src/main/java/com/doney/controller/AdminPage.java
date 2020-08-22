package com.doney.controller;

import com.doney.utility.LoggedInUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/adminOnly/home"}
)
public class AdminPage extends HttpServlet {
    private LoggedInUser helper = new LoggedInUser();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/adminHome.jsp");
        dispatcher.forward(req, resp);
    }
}