package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.EventDAO;
import com.beck.beck_demos.personal_project.data.UserDAO;
import com.beck.beck_demos.personal_project.models.Event;
import com.beck.beck_demos.personal_project.models.EventVM;
import com.beck.beck_demos.personal_project.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/league-admin-dash")
public class LeagueAdminDashServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    int PRIVILEGE_NEEDED = 3;

    User user = (User)session.getAttribute("User");
    if (user==null||user.getPrivilege_ID()!=PRIVILEGE_NEEDED){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }
    session.setAttribute("currentPage",req.getRequestURL());
    List<EventVM> events = null;

    events = EventDAO.getAllEvent();

    req.setAttribute("Events", events);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "League Admin Dashboard");
    req.getRequestDispatcher("WEB-INF/personal-project/LeagueAdminDash.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
