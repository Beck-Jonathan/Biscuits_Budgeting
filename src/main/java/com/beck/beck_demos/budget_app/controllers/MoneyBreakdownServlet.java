//package com.beck.beck_demos.budget_app.controllers;
//
//import com.beck.beck_demos.budget_app.data.TransactionDAO;
//import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
//import com.beck.beck_demos.budget_app.models.SubCategory_VM;
//import com.beck.beck_demos.budget_app.models.User;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@WebServlet("/MoneyBreakdown")
//public class MoneyBreakdownServlet extends HttpServlet {
//  private iTransactionDAO transactionDAO;
//
//
//
//  @Override
//  public void init() throws ServletException {
//    transactionDAO = new TransactionDAO();
//  }
//  public void init(iTransactionDAO transactionDAO){
//    this.transactionDAO = transactionDAO;
//  }
//
//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    HttpSession session = req.getSession();
//
//    // 1. Authentication Check
//    User user = (User) session.getAttribute("User_B");
//    if (user == null || !user.getRoles().contains("User")) {
//      resp.sendRedirect("budget_home");
//      return;
//    }
//
//    try {
//      // 2. Data Retrieval
//      // The DAO now returns a List of Lists, where each inner list is a year.
//      List<List<SubCategory_VM>> breakdown = transactionDAO.getAnalysis(user.getUser_ID());
//
//      if (breakdown == null || breakdown.isEmpty()) {
//        req.setAttribute("dbError", "No transaction data found to analyze.");
//        req.setAttribute("pageTitle", "Pie Chart");
//        req.getRequestDispatcher("WEB-INF/Budget_App/PieChart.jsp").forward(req, resp);
//        return;
//      }
//
//      // 3. Metadata Extraction
//      // Since the SQL handles 'total in/out' sorting, we just extract the headers
//      List<Integer> allYears = new ArrayList<>();
//      List<String> allCategories = new ArrayList<>();
//
//      for (List<SubCategory_VM> yearList : breakdown) {
//        if (!yearList.isEmpty()) {
//          allYears.add(yearList.get(0).getYear());
//        }
//      }
//
//      // Get category names from the first year (assuming all years have the same categories due to the CROSS JOIN)
//      for (SubCategory_VM cat : breakdown.get(0)) {
//        allCategories.add(cat.getCategory_ID());
//      }
//
//      // 4. Set Session/Request Attributes
//      session.setAttribute("Categories", allCategories);
//      session.setAttribute("yearRange", allYears);
//      req.setAttribute("Breakdown", breakdown); // Using request scope for the data itself is cleaner
//
//      req.setAttribute("pageTitle", "Financial Analysis");
//      session.setAttribute("currentPage", req.getRequestURL().toString());
//
//      req.getRequestDispatcher("WEB-INF/Budget_App/PieChart.jsp").forward(req, resp);
//
//    } catch (Exception e) {
//      // Log the error (crucial for debugging)
//      e.printStackTrace();
//      resp.sendRedirect("budget_home?error=analysis_failed");
//    }
//  }
//
//
//
//}
//
