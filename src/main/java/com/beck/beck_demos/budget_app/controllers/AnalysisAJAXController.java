package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.SubCategory_VM;
import com.beck.beck_demos.budget_app.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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

    // 1. Auth Check
    if (user == null || !user.getRoles().contains("User")) {
      sendResponse(resp, -1);
      return;
    }

    // 2. Parameters
    String mode = req.getParameter("mode");
    String level = req.getParameter("level");
    String yearStr = req.getParameter("year");
    String BankAccountID = req.getParameter("bankAccountSelect");
    if (BankAccountID == null) BankAccountID = "";

    String monthsBackStr = req.getParameter("monthsBack");
    String monthsForwardStr = req.getParameter("monthsForward");
    String retirementOffsetStr = req.getParameter("retirementOffset");

    int monthsBack = 24;
    int monthsForward = 840;
    int retirementOffset = 360;

    try {
      if (monthsBackStr != null) monthsBack = Math.max(6, Math.min(60, Integer.parseInt(monthsBackStr)));
      if (monthsForwardStr != null) monthsForward = Math.max(1, Math.min(1200, Integer.parseInt(monthsForwardStr)));
      if (retirementOffsetStr != null)
        retirementOffset = Math.max(0, Math.min(1200, Integer.parseInt(retirementOffsetStr)));
    } catch (NumberFormatException e) {
      // Defaults kept
    }

    LocalDate start = LocalDate.now();
    int year = (yearStr != null && !yearStr.isEmpty()) ? Integer.parseInt(yearStr) : 2026;

    // 3. Logic and Execution
    try {
      List<List<SubCategory_VM>> breakdown;

      if ("2".equals(mode)) {
        breakdown = transactionDAO.getForecastAnalysis(user.getUser_ID(), start, monthsBack, monthsForward, retirementOffset);
      } else if ("1".equals(level)) {
        breakdown = "1".equals(mode)
            ? transactionDAO.getSuperMonthlyAnalysis(user.getUser_ID(), BankAccountID, year)
            : transactionDAO.getSuperAnnualAnalysis(user.getUser_ID(), BankAccountID);
      } else {
        breakdown = "1".equals(mode)
            ? transactionDAO.getMonthlyAnalysis(user.getUser_ID(), BankAccountID, year)
            : transactionDAO.getAnnualAnalysis(user.getUser_ID(), BankAccountID);
      }

      // Success path
      resp.setContentType("application/json");
      resp.setCharacterEncoding("UTF-8");
      ObjectMapper mapper = new ObjectMapper();
      resp.getWriter().print(mapper.writeValueAsString(breakdown));

    } catch (Exception e) {
      e.printStackTrace();
      sendResponse(resp, -3);
    }
  }

  private void sendResponse(HttpServletResponse response, Integer Result) {
    try {
      response.setStatus(200);
      response.setContentType("text/plain");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      out.print(Result.toString());
      out.flush();
    } catch (IOException e) {
      System.err.println("Error writing response: " + e.getMessage());
    }
  }
}