//package com.beck.beck_demos.budget_app.controllers;
//
//import com.beck.beck_demos.budget_app.data.CategoryDAO;
//import com.beck.beck_demos.budget_app.data.TransactionDAO;
//import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
//import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
//import com.beck.beck_demos.budget_app.models.Category;
//import com.beck.beck_demos.budget_app.models.Transaction;
//import com.beck.beck_demos.budget_app.models.User;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@WebServlet("/categorize_transaction")
//public class CategorizeTransactionServlet extends HttpServlet {
//  private static iCategoryDAO categoryDAO;
//  private iTransactionDAO transactionDAO;
//
//  @Override
//  public void init() throws ServletException {
//    categoryDAO = new CategoryDAO();
//    transactionDAO =  new TransactionDAO();
//  }
//  public void init(iCategoryDAO categoryDAO, iTransactionDAO transactionDAO) {
//    this.categoryDAO = categoryDAO;
//    this.transactionDAO = transactionDAO;
//  }
//
//  @Override
//  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    HttpSession session = req.getSession();
//    User user = (User)session.getAttribute("User_B");
//    if (user==null||!user.getRoles().contains("User")){
//      resp.sendRedirect("/budget_in");
//      return;
//    }
//    session.setAttribute("currentPage",req.getRequestURL());
//    req.setAttribute("pageTitle", "Categorize Somethin'");
//
//    int x = 0;
//    req.getRequestDispatcher("WEB-INF/Budget_App/ViewAllTransactions.jsp").forward(req, resp);
//  }
//
//  @Override
//  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//
//    HttpSession session = req.getSession();
//    int errors = 0;
//    User user = (User)session.getAttribute("User_B");
//    if (user==null||!user.getRoles().contains("User")){
//      resp.sendRedirect("/budget_in");
//      return;
//    }
//    Map<String,String> results = new HashMap<>();
//
//    String id =req.getParameter("t_id");
//    String category = req.getParameter("category");
//    Transaction old_t = new Transaction();
//    old_t.setUser_ID(user.getUser_ID());
//    try {
//      old_t.setTransaction_ID(Integer.parseInt(id));
//    } catch (Exception e) {
//      errors++;
//    }
//
//
//    Transaction new_t = new Transaction();
//    try {
//      new_t.setCategory_ID(category);
//    }
//    catch (Exception e) {
//      errors ++;
//    }
//    if (errors ==0) {
//      try {
//        int result = transactionDAO.update(old_t, new_t);
//      } catch (SQLException e) {
//        results.put("deError","Database Error");
//      }
//    }
//    session.setAttribute("currentPage",req.getRequestURL());
//
//
//    List<Transaction> transactions = null;
//    int transaction_count=0;
//
//    int page_size=20;
//
//    int page_number = 1;
//    int recordsPerPage = 5;
//    if(req.getParameter("page") != null) {
//      page_number = Integer.parseInt(req.getParameter("page"));
//    }
//
//    // page_size = req.get
//
//    int offset=(page_number-1)*page_size;
//
//
//    try {
//      transaction_count = transactionDAO.getTransactionCountByUser(user.getUser_ID(),"",0);
//
//      transactions = transactionDAO.getTransactionByUser(user.getUser_ID(),page_size,offset);
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
//    int total_pages = 1+(transaction_count/page_size);
//
//    //https://stackoverflow.com/questions/31410007/how-to-do-pagination-in-jsp
//
//    req.setAttribute("noOfPages", total_pages);
//    //fix current page
//    req.setAttribute("currentPage", page_number);
//    List<Category> allCategories = categoryDAO.getCategoryByUser(user.getUser_ID());
//    req.setAttribute("Categories", allCategories);
//
//    req.setAttribute("results",results);
//    req.setAttribute("Transactions", transactions);
//    req.setAttribute("pageTitle", "All Transactions");
//    req.getRequestDispatcher("WEB-INF/Budget_App/ViewAllTransactions.jsp").forward(req,resp);
//
//
//
//  }
//}