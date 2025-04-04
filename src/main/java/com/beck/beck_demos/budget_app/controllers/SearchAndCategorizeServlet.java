package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.Category;
import com.beck.beck_demos.budget_app.models.Transaction;
import com.beck.beck_demos.budget_app.models.Transaction_VM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/search_transaction")
public class SearchAndCategorizeServlet extends HttpServlet {
  private  iCategoryDAO categoryDAO;
  private iTransactionDAO transactionDAO;

  @Override
  public void init() throws ServletException {
    transactionDAO = new TransactionDAO();

  }

  public void init(iTransactionDAO transactionDAO, iCategoryDAO categoryDAO) {
    this.transactionDAO = transactionDAO;
    this.categoryDAO = categoryDAO;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession();
    Map<String, String> results = new HashMap<>();

    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
    }
    List<Category> allCategories = categoryDAO.getCategoryByUser(user.getUser_ID());
    req.setAttribute("Categories", allCategories);

    session.setAttribute("currentPage",req.getRequestURL());
    List<Transaction_VM> transactions = null;
    int transaction_count=0;
    int errors = 0;
    String query = req.getParameter("query");
    session.setAttribute("search",query);
    if(query==null||query.length()<2){
      results.put("inputError","Invalid query");
      errors++;
    }
    if (errors==0) {
      try {
        transactions = transactionDAO.searchTransactionByUser(user.getUser_ID(), query);
      } catch (SQLException e) {
        results.put("dbStatus", "Unable To Search");
      }
    }

    req.setAttribute("Transactions", transactions);
    req.setAttribute("results",results);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Search Transactions");
    req.getRequestDispatcher("WEB-INF/Budget_App/search_categorize_transaction.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null ||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
     }
    Map<String, String> results = new HashMap<>();
    session.setAttribute("currentPage",req.getRequestURL());
    Integer update = 0;
    int transaction_count=0;
    int errors = 0;
    String query = (String) session.getAttribute("search");
    if(query==null||query.length()<2){
      results.put("inputError","Invalid query");
      errors ++;
    }
    String category = req.getParameter("category");
    try {
      Transaction t = new Transaction();
      t.setCategory_ID(category);
    } catch (Exception e){
      results.put("categoryError","Invalid category");
      errors++;
    }

      if (errors==0){

      try {
        update = transactionDAO.bulkUpdateCategory(user.getUser_ID(), category, query);
        results.put("updateCount", update.toString());
      } catch (SQLException e) {
        results.put("dbStatus", "Unable To Categorize");
        results.put("updateCount", "0");
      }

    }
      else {
        req.setAttribute("results", results);
        req.setAttribute("pageTitle", "Search Transactions");
        req.getRequestDispatcher("WEB-INF/Budget_App/search_categorize_transaction.jsp").forward(req, resp);
        return;

      }

    req.setAttribute("results", results);

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Search Transactions");
    resp.sendRedirect("all-Transactions");


  }
}