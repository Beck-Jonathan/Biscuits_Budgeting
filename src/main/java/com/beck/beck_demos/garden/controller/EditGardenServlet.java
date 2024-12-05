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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  Garden table
 Created By Jonathan Beck 4/13/2024
 ***************/

@WebServlet("/editgarden")
public class EditGardenServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");


    String mode = req.getParameter("mode");
    int primaryKey = Integer.parseInt(req.getParameter("gardenid"));
    Garden garden= new Garden();
    garden.setGarden_id(primaryKey);
    try{
      garden=GardenDAO.getGardenByPrimaryKey(garden);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }

    session.setAttribute("garden",garden);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Garden");
    req.getRequestDispatcher("WEB-INF/garden/Garden_Edit.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");


    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs
//to get the old Garden

    Garden _oldGarden= (Garden)session.getAttribute("garden");
//to get the new event's info
    String _Garden_Name = req.getParameter("inputgardenGarden_Name");
    _Garden_Name=_Garden_Name.trim();


    results.put("Garden_Name",_Garden_Name);

    Garden _newGarden = new Garden();
    int errors =0;
    try {
      _newGarden.setGarden_Name(_Garden_Name);
    } catch(IllegalArgumentException e) {results.put("gardenGarden_Nameerror", e.getMessage());
      errors++;
    }

    _newGarden.setis_active(true);
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=GardenDAO.update(_oldGarden,_newGarden);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Garden updated");
        resp.sendRedirect("all-Gardens");
        return;
      } else {
        results.put("dbStatus","Garden Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Garden ");
    req.getRequestDispatcher("WEB-INF/garden/Garden_Edit.jsp").forward(req, resp);
  }
}


