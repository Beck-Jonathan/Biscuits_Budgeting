package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.BudgetDAO;
import com.beck.beck_demos.budget_app.iData.iBudgetDAO;
import com.beck.beck_demos.budget_app.models.Budget_Line_ItemVM;
import com.beck.beck_demos.budget_app.models.Budget_VM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/BudgetAJAX")
public class BudgetAJAXServlet extends HttpServlet {
  private iBudgetDAO budgetDAO;

  @Override
  public void init() {
    budgetDAO = new BudgetDAO();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_B");

    if (user == null || !user.getRoles().contains("User")) {
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    String action = req.getParameter("action");
    if ("getActiveBudgets".equals(action)) {
      try {
        // Calling your DAO to get the List of View Models
        List<Budget_VM> activeBudgets = budgetDAO.getAllActiveBudgetsWithLines(user.getUser_ID());

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(convertToManualJson(activeBudgets));
        out.flush();
      } catch (Exception e) {
        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error: " + e.getMessage());
      }
    }
  }

  private String convertToManualJson(List<Budget_VM> budgets) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < budgets.size(); i++) {
      Budget_VM b = budgets.get(i);
      sb.append("{");
      sb.append("\"budget_id\":\"").append(b.getbudget_id()).append("\",");
      sb.append("\"name\":\"").append(escapeJson(b.getname())).append("\",");
      sb.append("\"details\":\"").append(escapeJson(b.getdetails())).append("\",");
      sb.append("\"limit_amount\":").append(b.getlimit_amount()).append(",");
      sb.append("\"totalSpent\":").append(b.getTotalSpent()).append(",");

      sb.append("\"lines\":[");
      List<Budget_Line_ItemVM> lines = b.getLines();
      if (lines != null) {
        for (int j = 0; j < lines.size(); j++) {
          Budget_Line_ItemVM line = lines.get(j);
          sb.append("{");
          sb.append("\"name\":\"").append(escapeJson(line.getname())).append("\",");
          sb.append("\"amount\":").append(line.getamount()).append(",");
          // Accessing the sub-category color via the VM's category object
          String color = (line.getCategory() != null) ? line.getCategory().getcolor_id() : "#cccccc";
          sb.append("\"color_id\":\"").append(color).append("\"");
          sb.append("}");
          if (j < lines.size() - 1) sb.append(",");
        }
      }
      sb.append("]");

      sb.append("}");
      if (i < budgets.size() - 1) sb.append(",");
    }
    sb.append("]");
    return sb.toString();
  }

  private String escapeJson(String str) {
    if (str == null) return "";
    return str.replace("\"", "\\\"").replace("\n", " ").replace("\r", "");
  }
}