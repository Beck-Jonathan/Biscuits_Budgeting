package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.LeagueDAO;
import com.beck.beck_demos.personal_project.data.TeamDAO;
import com.beck.beck_demos.personal_project.models.League;
import com.beck.beck_demos.personal_project.models.Team;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import  java.io.*;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/******************
 Create the Servlet  For adding to The  Team table
 Created By Jonathan Beck3/18/2024
 Updated 4/6/2024 to add two more colors to the team definition
 help from https://www.digitalocean.com/community/tutorials/servlet-3-file-upload-multipartconfig-part
 ***************/


@WebServlet("/addTeam")
@MultipartConfig(

    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

public class AddTeamServlet extends HttpServlet{
  private static final String UPLOAD_DIR = "uploads";

  static List<League> allLeagues = LeagueDAO.getActiveLeague();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Team");
    allLeagues = LeagueDAO.getAllLeague();
    req.setAttribute("Leagues", allLeagues);
    req.getRequestDispatcher("WEB-INF/personal-project/AddTeam.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String applicationPath = req.getServletContext().getRealPath("");
    String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
    File fileSaveDir = new File(uploadFilePath);
    if (!fileSaveDir.exists()) {
      fileSaveDir.mkdirs();
    }
    allLeagues = LeagueDAO.getAllLeague();
    req.setAttribute("Leagues", allLeagues);
    String _League_ID = req.getParameter("inputteamLeague_ID");
    _League_ID = _League_ID.trim();
    String _Name = req.getParameter("inputteamName");
    _Name = _Name.trim();
    String _Team_Primary_Color = req.getParameter("inputteamTeam_Primary_Color");
    _Team_Primary_Color = _Team_Primary_Color.trim();
    String _Team_Secondary_Color = req.getParameter("inputteamTeam_Secondary_Color");
    _Team_Secondary_Color = _Team_Secondary_Color.trim();
    String _Team_Tertiary_Color = req.getParameter("inputteamTeam_Tertiary_Color");
    _Team_Tertiary_Color = _Team_Tertiary_Color.trim();
    String _Team_City = req.getParameter("inputteamTeam_City");
    _Team_City = _Team_City.trim();
    String _Team_State = req.getParameter("inputteamTeam_State");
    _Team_State = _Team_State.trim();

    //testing file upload stuff

    //ServletContext context = pageContext.getServletContext();
    Map<String, String> results = new HashMap<>();
    Part filePart = req.getPart("inputteamLogo");

    String fileName = filePart.getSubmittedFileName();
    try {
      for (Part part : req.getParts()) {
        part.write(uploadFilePath + File.separator + fileName);

      }
    } catch (Exception ex){
      results.put("dbStatus",ex.getMessage());
      req.setAttribute("results", results);
      req.setAttribute("pageTitle", "Create a Team ");
      req.getRequestDispatcher("WEB-INF/personal-project/AddTeam.jsp").forward(req, resp);
      return;
    }

    results.put("League_ID",_League_ID);
    results.put("Name",_Name);
    results.put("Team_Primary_Color",_Team_Primary_Color);
    results.put("Team_Secondary_Color",_Team_Secondary_Color);
    results.put("Team_Tertiary_Color",_Team_Tertiary_Color);
    results.put("Team_City",_Team_City);
    results.put("Team_State",_Team_State);
    results.put("Logo",fileName);

    Team team = new Team();
    int errors =0;
    try {
      team.setLeague_ID(Integer.valueOf(_League_ID));
    } catch(IllegalArgumentException e) {results.put("teamLeague_IDerror", e.getMessage());
      errors++;
    }
    try {
      team.setName(_Name);
    } catch(IllegalArgumentException e) {results.put("teamNameerror", e.getMessage());
      errors++;
    }
    try {
      team.setTeam_Primary_Color(_Team_Primary_Color);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Primary_Colorerror", e.getMessage());
      errors++;
    }
    try {
      team.setTeam_Secondary_Color(_Team_Secondary_Color);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Secondary_Colorerror", e.getMessage());
      errors++;
    }
    try {
      team.setTeam_Tertiary_Color(_Team_Tertiary_Color);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Tertiary_Colorerror", e.getMessage());
      errors++;
    }
    try {
      team.setTeam_City(_Team_City);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Cityerror", e.getMessage());
      errors++;
    }
    try {
      team.setTeam_State(_Team_State);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Stateerror", e.getMessage());
      errors++;
    }
    try {
      team.setLogo(fileName);
    } catch(IllegalArgumentException e) {results.put("teamLogoerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=TeamDAO.add(team);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Team Added");
        resp.sendRedirect("all-Teams");
        return;
      } else {
        results.put("dbStatus","Team Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Team ");
    req.getRequestDispatcher("WEB-INF/personal-project/AddTeam.jsp").forward(req, resp);

  }
}
