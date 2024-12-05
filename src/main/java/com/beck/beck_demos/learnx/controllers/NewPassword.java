package com.beck.beck_demos.learnx.controllers;

import com.beck.beck_demos.learnx.Models.User;
import com.beck.beck_demos.learnx.data.UserDAO;

import com.beck.beck_demos.shared.EmailService;
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

@WebServlet("/new-password")
public class NewPassword extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String token = req.getParameter("token");
    String tempEmail = null;
    try {
      tempEmail = UserDAO.validToken(token);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    if(token == null || tempEmail.equals("")) {
      resp.sendRedirect("learnx");
      return;
    }
    HttpSession session = req.getSession();
    session.setAttribute("tempEmail", tempEmail);
    req.setAttribute("pageTitle", "New Password");

    req.getRequestDispatcher("WEB-INF/learnx/resetPassword.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String password1 = req.getParameter("inputPassword1");
    String password2 = req.getParameter("inputPassword2");
    Map<String, String> results = new HashMap<>();
    results.put("password1", password1);
    results.put("password2", password2);

    User user = new User();
    try {
      user.setPassword(password1.toCharArray());
    } catch(IllegalArgumentException e) {
      results.put("password1Error", e.getMessage());
    }
    if(password2.equals("")) {
      results.put("password2Error", "This input is required");
    }
    if(!password1.equals(password2)) {
      results.put("password2Error", "Passwords don't match");
    }

    if (!results.containsKey("password1Error")
        && !results.containsKey("password2Error")
    ) {
      HttpSession session = req.getSession();
      boolean resetComplete = UserDAO.resetPassword((String)session.getAttribute("tempEmail"), password1);
      // Send user an email
      // To do: Display error if the email could not be sent
      session.setAttribute("flashMessageSuccess", "Your password has been reset. Please login");
      resp.sendRedirect("login");
      return;

      // To do: Display error saying "Could not add user" if twoFactorInfo is empty

    }

    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "New Password");
    req.getRequestDispatcher("WEB-INF/learnx/resetPassword.jsp").forward(req, resp);
  }
}