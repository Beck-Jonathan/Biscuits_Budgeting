package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.Bank_AccountDAO;
import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iBank_AccountDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.Bank_Account;
import com.beck.beck_demos.budget_app.models.SubCategory_VM;
import com.beck.beck_demos.budget_app.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PieChart")
public class PieChartController extends HttpServlet {
  private iTransactionDAO transactionDAO;
  iBank_AccountDAO bankAccountDAO;

  @Override
  public void init() throws ServletException {
    transactionDAO = new TransactionDAO();
    bankAccountDAO = new Bank_AccountDAO();
  }

  public void init(iTransactionDAO transactionDAO, iBank_AccountDAO bankAccountDAO) {
    this.transactionDAO = transactionDAO;
    this.bankAccountDAO = bankAccountDAO;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_B");

    if (user == null || !user.getRoles().contains("User")) {
      resp.sendRedirect("budget_home");
      return;
    }
    List<Bank_Account> bankAccounts = null;
    try {
      // Initial load: Default to Annual Sub-Category view
      List<List<SubCategory_VM>> breakdown = transactionDAO.getAnnualAnalysis(user.getUser_ID(),"");
      bankAccounts = bankAccountDAO.getDistinctBank_AccountForDropdown(user.getUser_ID());

      if (breakdown.isEmpty()) {
        req.setAttribute("dbError", "No financial data found.");
      } else {
        List<Integer> yearRange = new ArrayList<>();
        List<String> categoryNames = new ArrayList<>();

        // Extract Metadata for sidebar and dropdowns
        for (List<SubCategory_VM> yearList : breakdown) {
          yearRange.add(yearList.get(0).getYear());
        }
        for (SubCategory_VM cat : breakdown.get(0)) {
          categoryNames.add(cat.getCategory_Name());
        }

        ObjectMapper mapper = new ObjectMapper();
        req.setAttribute("jsonBreakdown", mapper.writeValueAsString(breakdown));
        req.setAttribute("Breakdown", breakdown); // For JSP checkbox loop
        req.setAttribute("BankAccounts", bankAccounts);
        session.setAttribute("yearRange", yearRange);
        session.setAttribute("Categories", categoryNames);
      }

      req.setAttribute("pageTitle", "Financial Analysis");
      session.setAttribute("currentPage", req.getRequestURL().toString());
      req.getRequestDispatcher("WEB-INF/Budget_App/PieChart.jsp").forward(req, resp);

    } catch (Exception e) {
      e.printStackTrace();
      resp.sendRedirect("budget_home?error=analysis_init_fail");
    }
  }
}