package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet  For Viewing all of the  Team table
 Created By Jonathan Beck3/18/2024
 ***************/
import com.beck.beck_demos.personal_project.data.TeamDAO;
import com.beck.beck_demos.personal_project.models.Team;
import com.beck.beck_demos.personal_project.models.TeamVM;
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
@WebServlet("/all-Teams")
public class AllTeamServlet extends HttpServlet {
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  HttpSession session = req.getSession();
  session.setAttribute("currentPage",req.getRequestURL());
  List<TeamVM> teams = null;

  teams =TeamDAO.getAllTeam();

  req.setAttribute("Teams", teams);
  req.setAttribute("pageTitle", "All Teams");
  //req.getRequestDispatcher("WEB-INF/personal-project/all-Teams.jsp").forward(req,resp);
    req.getRequestDispatcher("WEB-INF/personal-project/all-Team-Card.jsp").forward(req,resp);

}
}
