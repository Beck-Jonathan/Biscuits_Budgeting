package com.beck.beck_demos.personal_project.controllers;



import com.beck.beck_demos.personal_project.data.LeagueDAO;
import com.beck.beck_demos.personal_project.models.League;
import com.beck.beck_demos.personal_project.models.User;
import com.fasterxml.jackson.databind.node.DecimalNode;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  League table
 Created By Jonathan Beck 4/8/2024
 ***************/

@WebServlet("/editleague")
public class EditLeagueServlet extends HttpServlet{

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
    String mode = req.getParameter("mode");
    int primaryKey = Integer.parseInt(req.getParameter("leagueid"));
    League league= new League();
    league.setLeague_ID(primaryKey);
    try{
      league=LeagueDAO.getLeagueByPrimaryKey(league);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }

    session.setAttribute("league",league);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add League");
    req.getRequestDispatcher("WEB-INF/personal-project/EditLeague.jsp").forward(req, resp);
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
    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs
//to get the old League

    League _oldLeague= (League)session.getAttribute("league");
//to get the new event's info
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

    results.put("League_Name",_League_Name);
    results.put("League_Level",_League_Level);
    results.put("Monthly_Due",_Monthly_Due);
    results.put("Active_Days",_Active_Days);
    results.put("Description",_Description);

    League _newLeague = new League();
    int errors =0;
    try {
      _newLeague.setLeague_Name(_League_Name);
    } catch(IllegalArgumentException e) {results.put("leagueLeague_Nameerror", e.getMessage());
      errors++;
    }
    try {
      _newLeague.setLeague_Level(_League_Level);
    } catch(IllegalArgumentException e) {results.put("leagueLeague_Levelerror", e.getMessage());
      errors++;
    }
    try {
      Double db_Monthly_Due = Double.parseDouble(_Monthly_Due);
      _newLeague.setMonthly_Due(db_Monthly_Due);
    } catch(IllegalArgumentException e) {results.put("leagueMonthly_Dueerror", e.getMessage());
      errors++;
    }
    try {
      _newLeague.setActive_Days(_Active_Days);
    } catch(IllegalArgumentException e) {results.put("leagueActive_Dayserror", e.getMessage());
      errors++;
    }
    try {
      _newLeague.setDescription(_Description);
    } catch(IllegalArgumentException e) {results.put("leagueDescriptionerror", e.getMessage());
      errors++;
    }

    _newLeague.setis_active(true);
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=LeagueDAO.update(_oldLeague,_newLeague);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","League updated");
        resp.sendRedirect("all-Leagues");
        return;
      } else {
        results.put("dbStatus","League Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a League ");
    req.getRequestDispatcher("WEB-INF/personal-project/EditLeague.jsp").forward(req, resp);
  }
}



