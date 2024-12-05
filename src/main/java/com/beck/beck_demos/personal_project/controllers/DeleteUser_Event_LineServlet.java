package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet For Deleteing from the User_Event_Line table
 Created By Jonathan Beck3/19/2024
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
@WebServlet("/deleteuser_event_line")
public class DeleteUser_Event_LineServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete User_Event_Line");
    int User_Event_LineID = Integer.valueOf(req.getParameter("user_event_lineid"));
    int mode = Integer.valueOf(req.getParameter("mode"));
    int result = 0;
    if (mode==0){
      try{
       // result = User_Event_LineDAO.remove(User_Event_LineID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    else {
      try{
        result = User_Event_LineDAO.undeleteUser_Event_Line(User_Event_LineID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    List<User_Event_Line> user_event_lines = null;
    user_event_lines = User_Event_LineDAO.getAllUser_Event_Line();
    req.setAttribute("results",results);
    req.setAttribute("User_Event_Lines", user_event_lines);
    req.setAttribute("pageTitle", "All User_Event_Line");
    req.getRequestDispatcher("WEB-INF/personal-project/all-User_Event_Line.jsp").forward(req, resp);
  }
}

