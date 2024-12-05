package com.beck.beck_demos.learnx.controllers;

/******************
 Create the Servlet For Deleteing from the User table
 Created By Jonathan Beck 4/25/2024
 ***************/

import com.beck.beck_demos.learnx.data.UserDAO;
import com.beck.beck_demos.learnx.Models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    String PRIVILEGE_NEEDED = "admin";
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("activeUser");
    String priv = user.getPrivileges();
    if (user==null||!user.getPrivileges().equals("admin")){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete User");
    String id = req.getParameter("userid");
    int x = 0;
    int UserID = Integer.valueOf(req.getParameter("userid"));

    int result = 0;

      try{
        result = UserDAO.deleteUser(UserID);
        if (result>0){
          session.setAttribute("flashMessageSuccess", "User updated");
        }
        else {
          session.setAttribute("flashMessageWarning", "User unable to be deleted");
        }
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
        session.setAttribute("flashMessageFailure", "User Not Deleted");
      }

    List<User> users = null;
    try {
      users = UserDAO.getAllUsers();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    session.setAttribute("flashMessageSuccess", "User Deleted");
    req.setAttribute("results",results);
    req.setAttribute("users", users);
    req.setAttribute("pageTitle", "All User");
    req.getRequestDispatcher("WEB-INF/learnx/all-users.jsp").forward(req,resp);
  }
}

