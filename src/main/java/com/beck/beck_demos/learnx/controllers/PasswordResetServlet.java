package com.beck.beck_demos.learnx.controllers;

import com.beck.beck_demos.learnx.Models.User;
import com.beck.beck_demos.learnx.data.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/resetpassword")
public class PasswordResetServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("pageTitle", "Reset Your Password");
    req.getRequestDispatcher("WEB-INF/learnx/reset.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String email = req.getParameter("inputEmail1");

    Map<String, String> results = new HashMap<>();
    results.put("email", email);

    try {
      UserDAO.passwordReset(email, req);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    results.put("passwordResetMsg", "If there is an account found, we will send an email");
    results.put("passwordResetMsg", "If there is an account found, we will send an email");

    resp.sendRedirect("learnx");
  }
}