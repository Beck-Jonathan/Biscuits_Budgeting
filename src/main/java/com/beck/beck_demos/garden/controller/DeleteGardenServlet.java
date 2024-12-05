package com.beck.beck_demos.garden.controller;

/******************
 Create the Servlet For Deleteing from the Garden table
 Created By Jonathan Beck 4/13/2024
 ***************/

import com.beck.beck_demos.garden.data.GardenDAO;
import com.beck.beck_demos.garden.model.Garden;
import com.beck.beck_demos.personal_project.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/deletegarden")
public class DeleteGardenServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");


    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete Garden");
    int GardenID = Integer.valueOf(req.getParameter("gardenid"));
    int mode = Integer.valueOf(req.getParameter("mode"));
    int result = 0;
    if (mode==0){
      try{
        result = GardenDAO.deleteGarden(GardenID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    else {
      try{
        result = GardenDAO.undeleteGarden(GardenID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    List<Garden> gardens = null;
    gardens = GardenDAO.getAllGarden();
    req.setAttribute("results",results);
    req.setAttribute("Gardens", gardens);
    req.setAttribute("pageTitle", "All Garden");
    req.getRequestDispatcher("WEB-INF/garden/Garden_All.jsp").forward(req, resp);
  }
}

