package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.UserBioDAO;
import com.beck.beck_demos.personal_project.data.UserDAO;
import com.beck.beck_demos.personal_project.models.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/team-admin-dash")
public class TeamAdminDashServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    int PRIVILEGE_NEEDED = 2;

    User user = (User)session.getAttribute("User");
    if (user==null||user.getPrivilege_ID()!=PRIVILEGE_NEEDED){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }
    session.setAttribute("currentPage",req.getRequestURL());
    List<EventVM> events = null;

    events = UserDAO.selectEventsByUser(user);
    List<TeamVM> teams = null;

    teams = UserDAO.selectTeamByUser(user);
    UserBio bio = new UserBio();
    try {
      bio = UserBioDAO.getUser_BioByPrimaryKey(user.getUser_ID());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }


    req.setAttribute("user_bio", bio);




    req.setAttribute("MyTeams", teams);

    session.setAttribute("Events", events);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Privacy Policy");
    req.getRequestDispatcher("WEB-INF/personal-project/TeamAdminDash.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
