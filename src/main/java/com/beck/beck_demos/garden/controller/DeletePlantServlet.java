package com.beck.beck_demos.garden.controller;

/******************
 Create the Servlet For Deleteing from the Plant table
 Created By Jonathan Beck 4/13/2024
 ***************/

import com.beck.beck_demos.garden.data.PlantDAO;
import com.beck.beck_demos.garden.model.Plant;
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
@WebServlet("/deleteplant")
public class DeletePlantServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");


    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete Plant");
    int PlantID = Integer.valueOf(req.getParameter("plantid"));
    int mode = Integer.valueOf(req.getParameter("mode"));
    int result = 0;
    if (mode==0){
      try{
        result = PlantDAO.deletePlant(PlantID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    else {
      try{
        result = PlantDAO.undeletePlant(PlantID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    List<Plant> plants = null;
    plants = PlantDAO.getAllPlant();
    req.setAttribute("results",results);
    req.setAttribute("Plants", plants);
    req.setAttribute("pageTitle", "All Plant");
    req.getRequestDispatcher("WEB-INF/garden/Plant_All.jsp").forward(req, resp);
  }
}


