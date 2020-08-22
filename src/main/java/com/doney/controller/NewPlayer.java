package com.doney.controller;

import com.doney.entity.Player;
import com.doney.persistence.GenericDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(
        urlPatterns = {"/adminOnly/newPlayer"}
)
public class NewPlayer extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/newPlayer.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao playerDao = new GenericDao(Player.class);
        String errorMessage;

        // configures upload settings
        DiskFileItemFactory diskFactory = new DiskFileItemFactory();
        // sets temporary location to store files
        diskFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(diskFactory);
        FileItem image = null;

        String firstName = "";
        String lastName = "";

        try {
            // parses the request's content to extract file data
            List<FileItem> formItems = upload.parseRequest(req);

            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (item.isFormField()) {
                        if (item.getFieldName().equals("firstName")) {
                            firstName = item.getString();
                        } else if (item.getFieldName().equals("lastName")) {
                            lastName = item.getString();
                        }
                    } else {
                        image = item;
                    }
                }

                Map<String, Object> propertyMap = new HashMap<>();
                propertyMap.put("firstName", firstName);
                propertyMap.put("lastName", lastName);
                List<Player> players = playerDao.findByPropertyEqual(propertyMap);

                if (players.size() > 0) {
                    errorMessage = firstName + " " + lastName + " is already entered";
                    req.setAttribute("errorMessage", errorMessage);
                } else {
                    saveImage(image, firstName, lastName);
                    Player newPlayer = new Player(firstName, lastName, firstName + lastName + "." + image.getContentType().substring(6));
                    playerDao.insert(newPlayer);
                    logger.info("Player inserted: First Name = " + firstName + " Last Name = " + lastName);
                    String successMessage = firstName + " " + lastName + " was added";
                    req.setAttribute("successMessage", successMessage);
                }
            }
        } catch (Exception ex) {
            req.setAttribute("errorMessage",
                    "There was an error: " + ex.getMessage());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/newPlayer.jsp");
        dispatcher.forward(req, resp);
    }

    private void saveImage(FileItem image, String firstName, String lastName) throws Exception {
        String fileName = "";

        if (image.getSize() > 0) {
            // constructs the directory path to store upload file
            // this path is relative to application's directory

            //The following two lines are for local testing only
            //String webappPath = "src/main/webapp/";
            //String localUploadPath = getServletContext().getRealPath("").substring(0, 43) + webappPath;

            String uploadPath = getServletContext().getRealPath("");

            // creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uploadDirectory = uploadPath + "playerProfilePictures/";
            fileName = firstName + lastName;
            String filePath = uploadDirectory + File.separator + fileName;
            File storeFile = new File(filePath + "." + image.getContentType().substring(6));

            // saves the file on disk
            image.write(storeFile);
        }
    }
}
