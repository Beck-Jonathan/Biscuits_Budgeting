package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.UserDAO;
import com.beck.beck_demos.personal_project.data.User_Team_LineDAO;
import com.beck.beck_demos.personal_project.models.Team;
import com.beck.beck_demos.personal_project.models.TeamVM;
import com.beck.beck_demos.personal_project.models.User;
import com.beck.beck_demos.personal_project.models.User_Team_Line;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/manage-teams")
public class ManageUserTeamLineServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();

    User user = (User)session.getAttribute("User");



    session.setAttribute("currentPage",req.getRequestURL());
    String mode = req.getParameter("mode");
    int teamid = Integer.parseInt(req.getParameter("teamid"));
    User_Team_Line line = new User_Team_Line();
    line.setTeam_ID(teamid);
    line.setUser_ID(user.getUser_ID());
    if (mode.equals("join")){
      User_Team_LineDAO.add(line);
    }
    if (mode.equals("leave")){
      User_Team_LineDAO.remove(line);
    }
    List<TeamVM> teams = null;

    teams = UserDAO.selectTeamByUser(user);
    List<TeamVM> unassignedTeams = null;

    unassignedTeams = UserDAO.selectUnAssignedTeamByUser(user);

    req.setAttribute("MyTeams", teams);
    req.setAttribute("NotMyTeams", unassignedTeams);

    req.setAttribute("pageTitle", "My Teams");
    req.getRequestDispatcher("WEB-INF/personal-project/MyTeams.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
