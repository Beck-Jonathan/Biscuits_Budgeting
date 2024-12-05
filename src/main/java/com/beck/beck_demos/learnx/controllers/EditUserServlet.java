package com.beck.beck_demos.learnx.controllers;


import com.beck.beck_demos.learnx.Models.User;
import com.beck.beck_demos.learnx.data.UserDAO;
import com.beck.beck_demos.shared.Helpers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/edit-user")
public class EditUserServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
//        User userFromSession = (User)session.getAttribute("activeUser");
//        if(userFromSession == null || !userFromSession.getStatus().equals("active") || !userFromSession.getPrivileges().equals("admin")) {
//            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
    int userId = 0;
    try {
      userId = Integer.parseInt(req.getParameter("id"));
    } catch(NumberFormatException e) {
      resp.sendRedirect("all-users");
      return;
    }
    User userFromDatabase = UserDAO.get(userId);
    if(userFromDatabase != null) {
      req.setAttribute("user", userFromDatabase);
      String userName = "User";
      if(!userFromDatabase.getFirst_name().equals("")) {
        userName = userFromDatabase.getFirst_name();
      }
      if(!userFromDatabase.getLast_name().equals("")) {
        userName += " " + userFromDatabase.getLast_name();
      }
      req.setAttribute("pageTitle", "Edit " + userName);
      req.getRequestDispatcher("WEB-INF/learnx/edit-user.jsp").forward(req, resp);
    } else {
      session.setAttribute("flashMessageWarning", "No user found with that id");
      resp.sendRedirect("all-users");
    }

  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    List<String> potentialErrors = new ArrayList<>();

    potentialErrors.add("firstNameError");
    potentialErrors.add("lastNameError");
    potentialErrors.add("emailError");
    potentialErrors.add("phoneError");
    potentialErrors.add("languageError");
    potentialErrors.add("statusError");
    potentialErrors.add("privError");


    int userId = 0;
    try {
      userId = Integer.parseInt(req.getParameter("id"));
    } catch(NumberFormatException e) {
      resp.sendRedirect("all-users");
      return;
    }
    String firstNameInput = req.getParameter("firstNameInput");
    String lastNameInput = req.getParameter("lastNameInput");
    String emailInput = req.getParameter("emailInput");
    String phoneInput = req.getParameter("phoneInput");
    String languageInput = req.getParameter("languageInput");
    String statusInput = req.getParameter("statusInput");
    String privilegesInput = req.getParameter("privInput");

    HashMap<String, String> results = new HashMap<>();
    User user = UserDAO.get(userId);
    if(user != null) {
      // todo: validate these strings
      user.setFirst_name(firstNameInput);
      user.setLast_name(lastNameInput);

      User userFromDatabase = null;
      try {
        userFromDatabase = UserDAO.getUserByPrimaryKey(emailInput);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      if(emailInput != null && !emailInput.equals(user.getEmail()) && userFromDatabase != null) {
        results.put("emailError", "A user with that email address already exists.");
        potentialErrors.add("emailError");
      }
      if(!results.containsKey("emailError")) {
        try {
          user.setEmail(emailInput);
        } catch (IllegalArgumentException e) {
          results.put("emailError", e.getMessage());
        }
      }

      try {
        if (phoneInput != null && !phoneInput.equals("") && userFromDatabase!=null && !phoneInput.equals(userFromDatabase.getPhone())) {
          user.setPhone(phoneInput);
        }
      } catch (Exception e) {
        results.put("phoneError", e.getMessage());

      }

      try {
        user.setLanguage(languageInput);
      } catch (IllegalArgumentException e) {
        results.put("languageError", e.getMessage());
      }

      try {
        user.setStatus(statusInput);
      } catch (IllegalArgumentException e) {
        results.put("statusError", e.getMessage());
      }

      try {
        user.setPrivileges(privilegesInput);
      } catch (IllegalArgumentException e) {
        results.put("privError", e.getMessage());
      }
      HttpSession session = req.getSession();
      if (Helpers.noErrors(results,potentialErrors)
      ) {
        UserDAO.update(user);
        session.setAttribute("flashMessageSuccess", "User updated");
      } else {
        session.setAttribute("flashMessageWarning", "Please check your inputs");
      }


    } else {
      resp.sendRedirect("all-users");
      return;
    }

    req.setAttribute("user", user);
    String userName = "User";
    if(!user.getFirst_name().equals("")) {
      userName = user.getFirst_name();
    }
    if(!user.getLast_name().equals("")) {
      userName += " " + user.getLast_name();
    }
    req.setAttribute("pageTitle", "Edit " + userName);
    req.getRequestDispatcher("WEB-INF/learnx/edit-user.jsp").forward(req, resp);


  }
}
