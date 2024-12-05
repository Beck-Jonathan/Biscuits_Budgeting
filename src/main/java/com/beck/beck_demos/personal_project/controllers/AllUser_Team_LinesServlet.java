package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet  For Viewing all of the  User_Team_Line table
 Created By Jonathan Beck3/18/2024
 ***************/
import com.beck.beck_demos.personal_project.data.User_Team_LineDAO;
import com.beck.beck_demos.personal_project.models.User_Team_Line;
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
@WebServlet("/all-User_Team_Lines")
public class AllUser_Team_LinesServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute("currentPage", req.getRequestURL());
    List<User_Team_Line> user_team_lines = null;

      user_team_lines = User_Team_LineDAO.getAllUser_Team_Line();
    req.setAttribute("User_Team_Lines", user_team_lines);
    req.setAttribute("pageTitle", "All User_Team_Lines");
    req.getRequestDispatcher("WEB-INF/personal-project/all-UserTeamLine.jsp").forward(req, resp);

  }
}
