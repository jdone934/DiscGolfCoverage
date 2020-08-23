package com.doney.controller;

import com.doney.entity.Course;
import com.doney.persistence.GenericDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        urlPatterns = {"/adminOnly/searchCourse"}
)
public class SearchCourse extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchTerm = req.getParameter("courseName");

        try {
            GenericDao courseDao = new GenericDao(Course.class);
            ObjectMapper jsonMapper = new ObjectMapper();

            List<Course> courses = courseDao.getByPropertyLike("name", searchTerm);
            String coursesJson = jsonMapper.writeValueAsString(courses);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(coursesJson);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
