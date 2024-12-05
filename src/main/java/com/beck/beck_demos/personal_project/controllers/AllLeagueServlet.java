package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet  For Viewing all of the  League table
 Created By Jonathan Beck3/18/2024
 ***************/
import com.beck.beck_demos.personal_project.data.LeagueDAO;
import com.beck.beck_demos.personal_project.models.League;
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
@WebServlet("/all-Leagues")
public class AllLeagueServlet extends HttpServlet {
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  HttpSession session = req.getSession();
  session.setAttribute("currentPage",req.getRequestURL());
  List<League> leagues = null;

    leagues =LeagueDAO.getAllLeague();


  req.setAttribute("Leagues", leagues);
  req.setAttribute("pageTitle", "All Leagues");
  req.getRequestDispatcher("WEB-INF/personal-project/all-League.jsp").forward(req,resp);



}
}