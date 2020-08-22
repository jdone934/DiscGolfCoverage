package com.doney.controller;

import com.doney.entity.Course;
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
        urlPatterns = {"/adminOnly/newCourse"}
)
public class NewCourse extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao courseDao = new GenericDao(Course.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/newCourse.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getParameter("name");
        String locationCity = (String) req.getParameter("locationCity");
        String locationState = (String) req.getParameter("locationState");
        String locationCountry = (String) req.getParameter("locationCountry");
        String website = (String) req.getParameter("website");

        Course courseToInsert = new Course(name, locationCity, locationState, locationCountry, website);
        logger.info(courseToInsert.toString());
        courseDao.insert(courseToInsert);
        logger.info(name + " inserted with City: " + locationCity + ", State: " + locationState + ", Country: " + locationCountry);
        String successMessage = name + " successfully inserted";
        req.setAttribute("successMessage", successMessage);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/newCourse.jsp");
        dispatcher.forward(req, resp);
    }
}
