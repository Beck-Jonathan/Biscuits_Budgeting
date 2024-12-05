package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.learnx.data.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.beck.beck_demos.learnx.Models.User;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/apply")
public class ApplicationServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    List<User> users = null;
    //try {
    //  users = UserDAO.getAllUsers();
   // } catch (SQLException e) {
     // throw new RuntimeException(e);
    //}
    //req.setAttribute("users", users);
    req.getRequestDispatcher("WEB-INF/personal-project/Apply.jsp").forward(req,resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);
  }
}
