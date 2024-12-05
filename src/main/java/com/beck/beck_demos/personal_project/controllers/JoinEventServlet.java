package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.*;
import com.beck.beck_demos.personal_project.models.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/joinevent")
public class JoinEventServlet extends HttpServlet {
  static List<Facility> allFacilitys = FacilityDAO.getActiveFacility();
  static List<Type> allTypes = TypeDAO.getActiveType();
  static List<TeamVM> myTeams;
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
    int primaryKey = Integer.parseInt(req.getParameter("eventid"));
    Event event= new Event();
    event.setEvent_ID(primaryKey);
    try{
      event=EventDAO.getEventByPrimaryKey(event);
      myTeams = UserDAO.selectTeamByUser(user);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }

    session.setAttribute("event",event);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());

    allFacilitys = FacilityDAO.getAllFacility();
    req.setAttribute("Facilitys", allFacilitys);
    req.setAttribute("Teams", myTeams);
    allTypes = TypeDAO.getAllType();
    req.setAttribute("Types", allTypes);
    req.setAttribute("pageTitle", "Join a Event ");
    req.getRequestDispatcher("WEB-INF/personal-project/joinEvent.jsp").forward(req, resp);
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

    myTeams = UserDAO.selectTeamByUser(user);
    req.setAttribute("Teams", myTeams);

    String _Team_ID = req.getParameter("inputeventTeam_ID");
    _Team_ID=_Team_ID.trim();
    String _Event_ID = req.getParameter("eventID");
    _Event_ID=_Event_ID.trim();

    Map<String, String> results = new HashMap<>();
    results.put("Team_ID",_Team_ID);
    results.put("Event_ID",_Event_ID);

    TeamEventLine team_event_line = new TeamEventLine();
    int errors =0;
    try {
      team_event_line.setTeam_ID(Integer.valueOf(_Team_ID));
    } catch(IllegalArgumentException e) {results.put("team_event_lineTeam_IDerror", e.getMessage());
      errors++;
    }
    try {
      team_event_line.setEvent_ID(Integer.valueOf(_Event_ID));
    } catch(IllegalArgumentException e) {results.put("team_event_lineEvent_IDerror", e.getMessage());
      errors++;
    }

    int result=0;
    if (errors==0){
      try{
        result=TeamEventLineDAO.add(team_event_line);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Team_Event_Line Added");
        resp.sendRedirect("home");
        return;
      } else {
        results.put("dbStatus","Team_Event_Line Not Added");

      }
      req.setAttribute("pageTitle", "Join a Event ");
      req.getRequestDispatcher("WEB-INF/personal-project/joinEvent.jsp").forward(req, resp);
    }
  }
}

