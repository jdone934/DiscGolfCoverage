package com.doney.controller;

import com.doney.entity.Course;
import com.doney.entity.Player;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        urlPatterns = {"/searchByCourse"}
)
public class CourseSearch extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao dao  = new GenericDao(Course.class);;
        String searchType = req.getParameter("searchType");
        Map<String, Object> propertyMap = new HashMap<>();

        String name = (String) req.getParameter("courseName");
        String city = (String) req.getParameter("courseCity");
        String state = (String) req.getParameter("courseState");
        String country = (String) req.getParameter("courseCountry");

        logger.info("Name: "+ name + " City: " + city + " State: " + state + " Country: " + country);

        propertyMap.put("name", name);
        propertyMap.put("locationCity", city);
        propertyMap.put("locationState", state);
        propertyMap.put("locationCountry", country);
        List<Course> results = dao.findByPropertyLikeMap(propertyMap);

        String loggingMessage = "Search for Courses with";
        if (name.length() > 0) {
            loggingMessage += " name of " + name;
        }
        if (city.length() > 0) {
            loggingMessage += " city of " + city;
        }
        if (state.length() > 0) {
            loggingMessage += " state of " + state;
        }
        if (country.length() > 0) {
            loggingMessage += " country of " + country;
        }
        logger.info(loggingMessage);

        req.setAttribute("searchType", searchType);
        req.setAttribute("courses", results);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}
