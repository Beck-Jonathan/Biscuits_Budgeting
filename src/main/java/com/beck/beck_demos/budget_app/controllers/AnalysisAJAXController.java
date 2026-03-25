package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.SubCategory_VM;
import com.beck.beck_demos.budget_app.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/AnalysisAJAX")
public class AnalysisAJAXController extends HttpServlet {
  private iTransactionDAO transactionDAO;

  @Override
  public void init() throws ServletException {
    transactionDAO = new TransactionDAO();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_B");

    if (user == null || !user.getRoles().contains("User")) {
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    // Parameters:
    // mode: 0 = Annual, 1 = Monthly
    // level: 0 = Sub-Category, 1 = Super-Category
    // year: The year to filter by for monthly view
    String mode = req.getParameter("mode");
    String level = req.getParameter("level");
    String yearStr = req.getParameter("year");
    String BankAccountID= req.getParameter("bankAccountSelect");
    if (BankAccountID == null ) {
      BankAccountID = "";
    }

    int year = (yearStr != null && !yearStr.isEmpty()) ? Integer.parseInt(yearStr) : 2026;

    List<List<SubCategory_VM>> breakdown;

    try {
      if ("1".equals(level)) {
        // SUPER CATEGORY LEVEL
        breakdown = "1".equals(mode)
            ? transactionDAO.getSuperMonthlyAnalysis(user.getUser_ID(),BankAccountID ,year)
            : transactionDAO.getSuperAnnualAnalysis(user.getUser_ID(),BankAccountID);
      } else {
        // SUB CATEGORY LEVEL (Default)
        breakdown = "1".equals(mode)
            ? transactionDAO.getMonthlyAnalysis(user.getUser_ID() ,BankAccountID, year)
            : transactionDAO.getAnnualAnalysis(user.getUser_ID() ,BankAccountID);
      }

      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");

      ObjectMapper mapper = new ObjectMapper();
      resp.getWriter().print(mapper.writeValueAsString(breakdown));

    } catch (Exception e) {
      e.printStackTrace();
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "AJAX Data Retrieval Failed");
    }
  }
}