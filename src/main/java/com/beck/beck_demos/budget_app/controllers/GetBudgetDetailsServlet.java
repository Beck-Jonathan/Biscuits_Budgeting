package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.BudgetDAO;
import com.beck.beck_demos.budget_app.iData.iBudgetDAO;
import com.beck.beck_demos.budget_app.models.Budget;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

@WebServlet("/get-budget-details")
public class GetBudgetDetailsServlet extends HttpServlet {
  private iBudgetDAO budgetDAO;

  @Override
  public void init() {
    budgetDAO = new BudgetDAO();
  }

  public void init(iBudgetDAO budgetDAO) {
    this.budgetDAO = budgetDAO;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_B");

    // Security Check
    if (user == null || !user.getRoles().contains("User")) {
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    String budgetID = req.getParameter("budget_id");
    if (budgetID != null) {
      budgetID = budgetID.trim();
    }

    // Basic Validation
    if (budgetID == null || budgetID.isEmpty()) {
      sendError(resp, "-2"); // Missing ID
      return;
    }

    try {
      Budget budget = budgetDAO.getBudgetOverviewBy_ID(budgetID, user.getUser_ID());

      if (budget != null) {
        // Format date to yyyy-MM-dd for HTML5 date inputs
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = budget.getstart_date().toString();

        // Construct simple JSON response
        String json = String.format(
            "{\"amount\": %.2f, \"start_date\": \"%s\"}",
            budget.getlimit_amount(),
            formattedDate
        );

        sendJsonResponse(resp, json);
      } else {
        sendError(resp, "0"); // Budget not found
      }
    } catch (Exception e) {
      sendError(resp, "-12"); // Database/System error
    }
  }

  private void sendJsonResponse(HttpServletResponse resp, String json) throws IOException {
    resp.setStatus(200);
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    PrintWriter out = resp.getWriter();
    out.print(json);
    out.flush();
  }

  private void sendError(HttpServletResponse resp, String errorCode) throws IOException {
    resp.setStatus(200);
    resp.setContentType("text/plain");
    PrintWriter out = resp.getWriter();
    out.print(errorCode);
    out.flush();
  }
}