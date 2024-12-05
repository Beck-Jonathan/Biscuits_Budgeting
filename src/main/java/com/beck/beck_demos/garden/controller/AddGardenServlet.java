package com.beck.beck_demos.garden.controller;


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
/******************
 Create the Servlet  For adding to The  Garden table
 Created By Jonathan Beck 4/13/2024
 ***************/

@WebServlet("/addGarden")
public class AddGardenServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");


    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Garden");
    req.getRequestDispatcher("WEB-INF/garden/Garden_Add.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");


    String _Garden_Name = req.getParameter("inputgardenGarden_Name");
    _Garden_Name=_Garden_Name.trim();

    Map<String, String> results = new HashMap<>();
    results.put("Garden_Name",_Garden_Name);

    Garden garden = new Garden();
    int errors =0;
    try {
      garden.setGarden_Name(_Garden_Name);
    } catch(IllegalArgumentException e) {results.put("gardenGarden_Nameerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=GardenDAO.add(garden);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Garden Added");
        resp.sendRedirect("all-Gardens");
        return;
      } else {
        results.put("dbStatus","Garden Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Garden ");
    req.getRequestDispatcher("WEB-INF/garden/Garden_Add.jsp").forward(req, resp);

  }
}

