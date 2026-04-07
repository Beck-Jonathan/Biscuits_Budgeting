package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.CategoryPerformanceDTO;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet to fetch Budget vs Actual performance for a specific category and year.
 * Created By Jonathan Beck 2026-04-06
 */
@WebServlet("/getCategoryPerformance")
public class GetCategoryPerformanceServlet extends HttpServlet {
  private iCategoryDAO categoryDAO;

  @Override
  public void init() {
    categoryDAO = new CategoryDAO();
  }

  public void init(iCategoryDAO categoryDAO) {
    this.categoryDAO = categoryDAO;
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    // 1. Inputs
    String subcatId = request.getParameter("id");
    String yearStr = request.getParameter("year");
    String mode = request.getParameter("mode");
    String month = request.getParameter("month");
    int _mode = 0;
    try {
      _mode = Integer.parseInt(mode);
    } catch (Exception e) {

    }
    int _month = -1;

    try {
      _month = Integer.parseInt(month);
    } catch (Exception e) {

    }
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("User_B");

    // 2. Auth Guard (Matches AnalyzeCategoryAJAX)
    if (user == null || user.getRoles() == null || !user.getRoles().contains("User")) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("-1");
      return;
    }

    // Validation
    int year;
    try {
      year = Integer.parseInt(yearStr);
      if (year < 2000 || year > 2050) throw new NumberFormatException();
    } catch (NumberFormatException e) {
      response.getWriter().write("[]");
      return;
    }

    // 3. Data Fetch
    List<CategoryPerformanceDTO> performanceData;
    try {
      if (_mode == 0) {
        // Logic assumes categoryDAO now has this method to call your new stored procedure
        performanceData = categoryDAO.getCategoryPerformance(user.getUser_ID(), subcatId, year);
      } else if (_mode == 1) {
        performanceData = categoryDAO.getAllCategoryPerformanceByMonth(user.getUser_ID(), year, _month);
      } else {
        response.getWriter().write("[]");
        return;
      }
    } catch (SQLException e) {
      // Return -2 to match your error handling pattern in JS
      response.getWriter().write("-2");
      return;
    }

    // 4. Safety Check
    if (performanceData == null || performanceData.isEmpty()) {
      response.getWriter().write("[]");
      return;
    }

    // 5. Build JSON (Manual building to avoid extra dependencies)
    StringBuilder json = new StringBuilder("[");
    for (int i = 0; i < performanceData.size(); i++) {
      CategoryPerformanceDTO item = performanceData.get(i);

      json.append("{")
          .append("\"categoryID\":\"").append(escapeJson(item.getCategoryID())).append("\",")
          .append("\"categoryName\":\"").append(escapeJson(item.getCategoryName())).append("\",")
          .append("\"period\":\"").append(escapeJson(item.getPeriod())).append("\",")
          .append("\"budgetedValue\":").append(item.getBudgetedValue()).append(",")
          .append("\"actualValue\":").append(item.getActualValue()).append(",")
          // FIX: Added quotes and changed to snake_case to match JS
          .append("\"transaction_type\":\"").append(escapeJson(item.getTransactionType())).append("\",")
          .append("\"threshold\":").append(item.getThreshold())
          .append("}");

      if (i < performanceData.size() - 1) {
        json.append(",");
      }
    }
    json.append("]");

    response.getWriter().write(json.toString());
  }

  /**
   * Simple helper to ensure period strings (MM/YYYY) are safe
   */
  private String escapeJson(String input) {
    if (input == null) return "";
    return input.replace("\"", "\\\"");
  }
}