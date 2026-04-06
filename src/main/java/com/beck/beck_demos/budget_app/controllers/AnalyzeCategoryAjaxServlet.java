package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.ProjectionAnalysisDTO;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/******************
 Create the Servlet  For adding to The  Bank_Account table
 Created By Jonathan Beck 1/22/2025
 ***************/
@WebServlet("/AnalyzeCategoryAJAX")
public class AnalyzeCategoryAjaxServlet extends HttpServlet {
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

    String subcatId = request.getParameter("subcatId");
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute("User_B");

    // 1. Auth Guard
    if (user == null || user.getRoles() == null || !user.getRoles().contains("User")) {
      response.getWriter().write("-1");
      return;
    }

    // 2. Data Fetch
    List<ProjectionAnalysisDTO> data;
    try {
      // 24 months history, 36 months projection
      data = categoryDAO.getProjectionData(user.getUser_ID(), subcatId, 24, 36);
    } catch (SQLException e) {
      response.getWriter().write("-2");
      return;
    }

    // 3. Safety Check for empty data
    if (data == null || data.isEmpty()) {
      response.getWriter().write("[]");
      return;
    }

    // 4. Setup Date Clamping (2000 - 2050)
    Calendar cal = Calendar.getInstance();
    cal.set(2000, Calendar.JANUARY, 1);
    Date lowerLimit = cal.getTime();
    cal.set(2050, Calendar.DECEMBER, 31);
    Date upperLimit = cal.getTime();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    // 5. Build JSON
    StringBuilder json = new StringBuilder("[");
    for (int i = 0; i < data.size(); i++) {
      ProjectionAnalysisDTO row = data.get(i);

      // Apply Date Clamping logic
      String monthDate = row.getMonthDate();
      try {
        Date d = df.parse(monthDate);
        if (d.before(lowerLimit)) monthDate = df.format(lowerLimit);
        if (d.after(upperLimit)) monthDate = df.format(upperLimit);
      } catch (Exception e) {
        // Keep original or set to a safe default if parsing fails
      }

      json.append("{")
          .append("\"monthDate\":\"").append(monthDate).append("\",")
          .append("\"historicalTruth\":").append(formatVal(row.getHistoricalTruth())).append(",")
          .append("\"regression\":").append(formatVal(row.getRegression())).append(",")
          .append("\"avgStrict\":").append(formatVal(row.getAvgStrict())).append(",")
          .append("\"lvcf\":").append(formatVal(row.getLvcf())).append(",")
          .append("\"inflation\":").append(formatVal(row.getInflation())).append(",")
          .append("\"alphaSpike\":").append(formatVal(row.getAlphaSpike())).append(",")
          .append("\"zeroSum\":").append(formatVal(row.getZeroSum()))
          .append("}");

      if (i < data.size() - 1) {
        json.append(",");
      }
    }
    json.append("]");

    response.getWriter().write(json.toString());
  }

  private String formatVal(Double val) {
    // Avoid appending "null" as a string if possible, or ensure JS handles it.
    // JSON 'null' (no quotes) is correct for numeric fields.
    return (val == null) ? "null" : val.toString();
  }
}