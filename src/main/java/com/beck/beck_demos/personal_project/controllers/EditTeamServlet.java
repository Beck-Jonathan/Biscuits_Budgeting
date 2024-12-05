package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.LeagueDAO;
import com.beck.beck_demos.personal_project.data.TeamDAO;
import com.beck.beck_demos.personal_project.models.League;
import com.beck.beck_demos.personal_project.models.Team;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@MultipartConfig(

    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

@WebServlet("/editteam")
public class EditTeamServlet extends HttpServlet {
  private static final String UPLOAD_DIR = "uploads";


  static List<League> allLeagues = LeagueDAO.getActiveLeague();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String mode = req.getParameter("mode");
    int primaryKey = Integer.parseInt(req.getParameter("teamid"));
    Team team = new Team();
    team.setTeam_ID(primaryKey);
    try {
      team = TeamDAO.getTeamByPrimaryKey(team);
    } catch (SQLException e) {
      req.setAttribute("dbStatus", e.getMessage());
    }
    HttpSession session = req.getSession();
    session.setAttribute("team", team);
    String applicationPath = req.getServletContext().getRealPath("");
    String uploadFilePath = UPLOAD_DIR;
    session.setAttribute("displayTeamLogo",uploadFilePath+"//"+team.getLogo());
    req.setAttribute("mode", mode);
    session.setAttribute("currentPage", req.getRequestURL());
    req.setAttribute("pageTitle", "Edit Team");
    allLeagues = LeagueDAO.getAllLeague();
    req.setAttribute("Leagues", allLeagues);
    req.getRequestDispatcher("WEB-INF/personal-project/EditTeam.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String applicationPath = req.getServletContext().getRealPath("");
    String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
    File fileSaveDir = new File(uploadFilePath);
    if (!fileSaveDir.exists()) {
      fileSaveDir.mkdirs();
    }
    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs
    allLeagues = LeagueDAO.getAllLeague();
    req.setAttribute("Leagues", allLeagues);
//to get the old Team
    HttpSession session = req.getSession();
    Team _oldTeam= (Team)session.getAttribute("team");
//to get the new team's info
    String _League_ID = req.getParameter("inputteamLeague_ID");
    _League_ID=_League_ID.trim();
    String _Name = req.getParameter("inputteamName");
    _Name=_Name.trim();
    String _Team_Primary_Color = req.getParameter("inputteamTeam_Primary_Color");
    _Team_Primary_Color=_Team_Primary_Color.trim();
    String _Team_Secondary_Color = req.getParameter("inputteamTeam_Secondary_Color");
    _Team_Secondary_Color=_Team_Secondary_Color.trim();
    String _Team_Tertiary_Color = req.getParameter("inputteamTeam_Tertiary_Color");
    _Team_Tertiary_Color=_Team_Tertiary_Color.trim();
    String _Team_City = req.getParameter("inputteamTeam_City");
    _Team_City=_Team_City.trim();
    String _Team_State = req.getParameter("inputteamTeam_State");
    _Team_State=_Team_State.trim();
    //new logo
    Part filePart =null;
    try {

       filePart = req.getPart("inputteamLogo");
    }
    catch (Exception ex){
      results.put("dbStatus",ex.getMessage());
    }

    assert filePart != null;
    String fileName = filePart.getSubmittedFileName();
    String _Logo = fileName;
    _Logo=_Logo.trim();
    try {
      for (Part part : req.getParts()) {
        part.write(uploadFilePath + File.separator + fileName);

      }
    } catch (Exception ex){
      _Logo=_oldTeam.getLogo();
      //results.put("dbStatus",ex.getMessage());
      //req.setAttribute("results", results);
      //req.setAttribute("pageTitle", "Edit Team ");
      //req.getRequestDispatcher("WEB-INF/personal-project/EditTeam.jsp").forward(req, resp);
      //return;
    }




    results.put("League_ID",_League_ID);
    results.put("Name",_Name);
    results.put("Team_Primary_Color",_Team_Primary_Color);
    results.put("Team_Secondary_Color",_Team_Secondary_Color);
    results.put("Team_Tertiary_Color",_Team_Tertiary_Color);
    results.put("Team_City",_Team_City);
    results.put("Team_State",_Team_State);
    results.put("Logo",_Logo);

    Team _newTeam = new Team();
    int errors =0;
    try {
      _newTeam.setLeague_ID(Integer.valueOf(_League_ID));
    } catch(IllegalArgumentException e) {results.put("teamLeague_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newTeam.setName(_Name);
    } catch(IllegalArgumentException e) {results.put("teamNameerror", e.getMessage());
      errors++;
    }
    try {
      _newTeam.setTeam_Primary_Color(_Team_Primary_Color);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Primary_Colorerror", e.getMessage());
      errors++;
    }
    try {
      _newTeam.setTeam_Secondary_Color(_Team_Secondary_Color);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Secondary_Colorerror", e.getMessage());
      errors++;
    }
    try {
      _newTeam.setTeam_Tertiary_Color(_Team_Tertiary_Color);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Tertiary_Colorerror", e.getMessage());
      errors++;
    }
    try {
      _newTeam.setTeam_City(_Team_City);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Cityerror", e.getMessage());
      errors++;
    }
    try {
      _newTeam.setTeam_State(_Team_State);
    } catch(IllegalArgumentException e) {results.put("teamTeam_Stateerror", e.getMessage());
      errors++;
    }
    try {
      _newTeam.setLogo(_Logo);
    } catch(IllegalArgumentException e) {results.put("teamLogoerror", e.getMessage());
      errors++;
    }

    _newTeam.setis_active(true);
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=TeamDAO.update(_oldTeam,_newTeam);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Team updated");
        resp.sendRedirect("all-Teams");
        return;
      } else {
        results.put("dbStatus","Team Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Team ");
    req.getRequestDispatcher("WEB-INF/personal-project/EditTeam.jsp").forward(req, resp);
  }
}

