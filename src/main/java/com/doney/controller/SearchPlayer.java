package com.doney.controller;

import com.doney.entity.Player;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        urlPatterns = {"/adminOnly/searchPlayer"}
)
public class SearchPlayer extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        try {
            GenericDao playerDao = new GenericDao(Player.class);
            ObjectMapper jsonMapper = new ObjectMapper();

            Map<String, Object> propertyMap = new HashMap<>();
            propertyMap.put("firstName", firstName);
            propertyMap.put("lastName", lastName);

            List<Player> players = playerDao.findByPropertyLikeMap(propertyMap);
            String playersJson = jsonMapper.writeValueAsString(players);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(playersJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
