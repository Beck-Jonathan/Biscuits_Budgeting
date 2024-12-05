package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet For Deleteing from the League table
 Created By Jonathan Beck3/19/2024
 ***************/

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
import java.util.List;
import java.util.Map;
@WebServlet("/deleteleague")public class DeleteLeagueServlet extends HttpServlet {
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
    Map<String, String> results = new HashMap<>();

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete League");
    int LeagueID = Integer.valueOf(req.getParameter("leagueid"));
    int mode = Integer.valueOf(req.getParameter("mode"));
    int result = 0;
    if (mode==0){
      try{
        result = LeagueDAO.deleteLeague(LeagueID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    else {
      try{
        result = LeagueDAO.undeleteLeague(LeagueID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    List<League> leagues = null;
    leagues = LeagueDAO.getAllLeague();
    req.setAttribute("results",results);
    req.setAttribute("Leagues", leagues);
    req.setAttribute("pageTitle", "All League");
    req.getRequestDispatcher("WEB-INF/personal-project/all-League.jsp").forward(req, resp);
  }
}

