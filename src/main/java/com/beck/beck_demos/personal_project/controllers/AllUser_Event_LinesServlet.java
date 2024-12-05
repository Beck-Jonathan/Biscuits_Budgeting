package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet  For Viewing all of the  User_Event_Line table
 Created By Jonathan Beck3/18/2024
 ***************/
import com.beck.beck_demos.personal_project.data.User_Event_LineDAO;
import com.beck.beck_demos.personal_project.models.User_Event_Line;
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
@WebServlet("/all-User_Event_Lines")
public class AllUser_Event_LinesServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute("currentPage", req.getRequestURL());
    List<User_Event_Line> user_event_lines = null;

    user_event_lines = User_Event_LineDAO.getAllUser_Event_Line();

    req.setAttribute("User_Event_Lines", user_event_lines);
    req.setAttribute("pageTitle", "All User_Event_Lines");
    req.getRequestDispatcher("WEB-INF/personal-project/all-UserEventLine.jsp").forward(req, resp);

  }
}