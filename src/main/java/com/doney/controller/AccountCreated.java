package com.doney.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Pass new account info to confirmation page
 * @author Jacob Doney
 */
@WebServlet(
        urlPatterns = {"/accountCreated/*"}
)
public class AccountCreated extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestServlet = req.getPathInfo();
        String returnPath = "/";

        if (requestServlet != null) {
            returnPath = requestServlet + "?" + req.getQueryString();
        }

        req.setAttribute("path", returnPath);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/accountCreated.jsp");
        dispatcher.forward(req, resp);
    }
}
