package com.doney.controller;

import com.doney.entity.Course;
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
import java.util.Set;

@WebServlet(
    urlPatterns = {"/viewCourse"}
)
public class ViewCourse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao courseDao = new GenericDao(Course.class);
        Course course = (Course) courseDao.getById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("course", course);

        LoggedInUser helper = new LoggedInUser();
        User user = helper.getLoggedInUser(req);
        if (user != null) {
            Set<Course> favorites = user.getFavoriteCourses();
            if (favorites.contains(course)) {
                req.setAttribute("favoriteCourse", true);
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewCourse.jsp");
        dispatcher.forward(req, resp);
    }
}
