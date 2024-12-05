package com.beck.beck_demos.personal_project.controllers;
import com.beck.beck_demos.personal_project.data.LeagueDAO;
import com.beck.beck_demos.personal_project.models.League;
import com.beck.beck_demos.personal_project.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  League table
 Created By Jonathan Beck3/18/2024
 Modified 4/6/2024
 ***************/

@WebServlet("/addLeague")
public class AddLeagueServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||user.getPrivilege_ID()<PRIVILEGE_NEEDED){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add League");
    req.getRequestDispatcher("WEB-INF/personal-project/AddLeague.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||user.getPrivilege_ID()<PRIVILEGE_NEEDED){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }
    String _League_Name = req.getParameter("inputleagueLeague_Name");
    _League_Name=_League_Name.trim();
    String _League_Level = req.getParameter("inputleagueLeague_Level");
    _League_Level=_League_Level.trim();
    String _Monthly_Due = req.getParameter("inputleagueMonthly_Due");
    _Monthly_Due=_Monthly_Due.trim();

    String _Active_Days = req.getParameter("inputleagueActive_Days");
    _Active_Days=_Active_Days.trim();
    String _Description = req.getParameter("inputleagueDescription");
    _Description=_Description.trim();

    Map<String, String> results = new HashMap<>();
    results.put("League_Name",_League_Name);
    results.put("League_Level",_League_Level);
    results.put("Monthly_Due",_Monthly_Due);
    results.put("Active_Days",_Active_Days);
    results.put("Description",_Description);

    League league = new League();
    int errors =0;
    try {
      league.setLeague_Name(_League_Name);
    } catch(IllegalArgumentException e) {results.put("leagueLeague_Nameerror", e.getMessage());
      errors++;
    }
    try {
      league.setLeague_Level(_League_Level);
    } catch(IllegalArgumentException e) {results.put("leagueLeague_Levelerror", e.getMessage());
      errors++;
    }
    try {
      league.setMonthly_Due(Double.valueOf(_Monthly_Due));
    } catch(IllegalArgumentException e) {results.put("leagueMonthly_Dueerror", e.getMessage());
      errors++;
    }
    try {
      league.setActive_Days(_Active_Days);
    } catch(IllegalArgumentException e) {results.put("leagueActive_Dayserror", e.getMessage());
      errors++;
    }
    try {
      league.setDescription(_Description);
    } catch(IllegalArgumentException e) {results.put("leagueDescriptionerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=LeagueDAO.add(league);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","League Added");
        resp.sendRedirect("all-Leagues");
        return;
      } else {
        results.put("dbStatus","League Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a League ");
    req.getRequestDispatcher("WEB-INF/personal-project/AddLeague.jsp").forward(req, resp);

  }
}

