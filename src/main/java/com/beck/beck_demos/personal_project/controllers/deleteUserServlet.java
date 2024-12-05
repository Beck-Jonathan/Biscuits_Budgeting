package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet For Deleteing from the User table
 Created By Jonathan Beck 5/5/2024
 ***************/

import com.beck.beck_demos.personal_project.data.UserDAO;
import com.beck.beck_demos.personal_project.models.User;
import com.beck.beck_demos.personal_project.models.User;
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
@WebServlet("/deleteaccount")
public class deleteUserServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 1;
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User");
    if (user == null || user.getPrivilege_ID() != PRIVILEGE_NEEDED) {
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    session.setAttribute("currentPage", req.getRequestURL());
    req.setAttribute("pageTitle", "Delete User");




    req.setAttribute("pageTitle", "All User");
    req.getRequestDispatcher("WEB-INF/personal-project/delete-account.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 1;
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User");
    if (user == null || user.getPrivilege_ID() != PRIVILEGE_NEEDED) {
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    session.setAttribute("currentPage", req.getRequestURL());
    req.setAttribute("pageTitle", "Delete User");
    int UserID = user.getUser_ID();

    boolean result = false;
    try {
      result = UserDAO.deleteUser(UserID);
      if (result){
        session.invalidate();
        resp.sendRedirect("home");
        return;
      }
    } catch (Exception ex) {
      results.put("dbStatus", ex.getMessage());
    }

    req.setAttribute("results", results);

    req.setAttribute("pageTitle", "All User");
    req.getRequestDispatcher("WEB-INF/personal-project/all-Users.jsp").forward(req, resp);

  }
}

