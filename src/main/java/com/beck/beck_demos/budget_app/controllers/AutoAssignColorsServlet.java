package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/autoAssignColors")
public class AutoAssignColorsServlet extends HttpServlet {
  private iCategoryDAO categoryDAO;

  @Override
  public void init() throws ServletException {
    categoryDAO = new CategoryDAO();
  }

  public void init(iCategoryDAO categoryDAO) {
    this.categoryDAO = categoryDAO;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_B");
    Integer result = 0;
    if (user == null || !user.getRoles().contains("User")) {
      session.invalidate();
      result = -1;
      sendResponse(resp, result);
      return;
    }
    try {
      user.setUser_ID(user.getUser_ID());
    } catch (Exception e) {
      result = -2;
      sendResponse(resp, result);
      return;
    }

    try {
      result = categoryDAO.SmartAssignColor(user);
      sendResponse(resp, result);
    } catch (Exception ex) {

      result = -3;
      sendResponse(resp, result);
    }
  }

  private void sendResponse(HttpServletResponse response, Integer Result) {
    try {
      // 1. Set the status and headers before getting the writer
      response.setStatus(200);
      response.setContentType("text/plain");
      response.setCharacterEncoding("UTF-8");

      // 2. Use try-with-resources if you want to be safe,
      // though Tomcat generally manages the response writer for you.
      PrintWriter out = response.getWriter();
      out.print(Result.toString());
      out.flush();

    } catch (IOException e) {
      // Log the error if the connection was closed before we could write
      System.err.println("Error writing response: " + e.getMessage());
    }
  }
}
