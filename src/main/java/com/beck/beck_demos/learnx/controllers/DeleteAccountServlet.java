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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/delete-account")
public class DeleteAccountServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = Helpers.getUserFromSession(session);
    if(user == null) {
      resp.sendRedirect("signin?redirect=delete-account");
      return;
    }
    req.setAttribute("pageTitle", "Delete Account");
    req.getRequestDispatcher("WEB-INF/learnx/delete-account.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String email = req.getParameter("emailInput");
    Map<String,String> results = new HashMap<>();

    results.clear();
    results.put("email", email);

    HttpSession session = req.getSession();
    User activeUser = Helpers.getUserFromSession(session);
    if(!email.equals(activeUser.getEmail())) {
      results.put("emailError", "The value you entered is not the same as <b>'" + activeUser.getEmail() + "'</b>.");
    }

    if(!results.containsKey("emailError")) {

      int result = UserDAO.delete(activeUser);
      if (result==1) {
        session.invalidate();
        session = req.getSession();
        session.setAttribute("flashMessageSuccess", "Your account has been deleted.");
        resp.sendRedirect("learnx");
      }
      else {

        session.setAttribute("flashMessageSuccess", "Your account has not been deleted.");
      }
      return;
    }

    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Delete Account");
    req.getRequestDispatcher("WEB-INF/learnx/delete-account.jsp").forward(req, resp);
  }
}

