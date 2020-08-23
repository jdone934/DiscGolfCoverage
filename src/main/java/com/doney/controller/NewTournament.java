package com.doney.controller;

import com.doney.entity.Course;
import com.doney.entity.Tournament;
import com.doney.entity.TournamentAtCourse;
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
import java.util.*;

@WebServlet(
        urlPatterns = {"/adminOnly/newTournament"}
)
public class NewTournament extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao tournamentDao = new GenericDao(Tournament.class);
    GenericDao tournamentAtCourseDao = new GenericDao(TournamentAtCourse.class);
    GenericDao courseDao = new GenericDao(Course.class);

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
        String website = req.getParameter("website");

        if (courses != null && courses.length > 0) {
            Tournament tournamentToInsert = new Tournament(name, year, tournamentSeries, website);
            tournamentDao.insert(tournamentToInsert);

            for (String courseIdString : courses) {
                int courseId = Integer.parseInt(courseIdString);
                Course courseForTournament = (Course) courseDao.getById(courseId);

                TournamentAtCourse tournamentAtCourse = new TournamentAtCourse(courseForTournament, tournamentToInsert);
                tournamentAtCourseDao.insert(tournamentAtCourse);

                successMessage = name + " successfully added";
                req.setAttribute("successMessage", successMessage);
            }
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
