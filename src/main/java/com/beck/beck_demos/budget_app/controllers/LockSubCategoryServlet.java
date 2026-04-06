package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.SubCategory;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/LockSubcategory")
public class LockSubCategoryServlet extends HttpServlet {
  private iCategoryDAO categoryDAO;

  @Override
  public void init() {
    categoryDAO = new CategoryDAO();
  }

  public void init(iCategoryDAO categoryDAO) {
    this.categoryDAO = categoryDAO;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    HttpSession session = req.getSession();
    String Role = "User";
    Integer result = 0;
    User user = (User) session.getAttribute("User_B");
    if (user == null || !user.getRoles().contains("User")) {
      result = -1;
      sendResponse(resp, result);
      return;
    }

    String planned_transactionID = req.getParameter("subcategoryid");
    int mode = Integer.parseInt(req.getParameter("mode"));
    SubCategory toChenge = new SubCategory();
    try {
      toChenge.setCategory_ID(planned_transactionID);
    } catch (Exception e) {
      result = -2;
      sendResponse(resp, result);
      return;
    }
    try {
      toChenge.setUser_ID(user.getUser_ID());
    } catch (Exception e) {
      result = -3;
      sendResponse(resp, result);
      return;
    }

    if (mode == 0) {
      try {
        result = categoryDAO.unlockSubCategory(toChenge);

        sendResponse(resp, result);
      } catch (Exception ex) {
        result = -4;
        sendResponse(resp, result);
      }
    } else if (mode == 1) {
      try {
        result = categoryDAO.lockSubCategory(toChenge);
        sendResponse(resp, result);
      } catch (Exception ex) {
        result = -5;
        sendResponse(resp, result);
      }
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

