package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.Category;
import com.beck.beck_demos.budget_app.models.Transaction;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/search_transaction")
public class SearchAndCategorizeServlet extends HttpServlet {
  private  iCategoryDAO categoryDAO;
  private iTransactionDAO transactionDAO;

  @Override
  public void init() throws ServletException {
    transactionDAO = new TransactionDAO();

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession();
    if (categoryDAO==null){
      categoryDAO = new CategoryDAO();
    }
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
    }
    List<Category> allCategories = categoryDAO.getCategoryByUser(user.getUser_ID());
    req.setAttribute("Categories", allCategories);
    if (user==null){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }
    session.setAttribute("currentPage",req.getRequestURL());
    List<Transaction> transactions = null;
    int transaction_count=0;
    String query = req.getParameter("query");
    session.setAttribute("search",query);
    if (query!=null && !query.equals("")) {
      try {
        transactions = transactionDAO.searchTransactionByUser(user.getUser_ID(), query);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    req.setAttribute("Transactions", transactions);

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Search Transactions");
    req.getRequestDispatcher("WEB-INF/Budget_App/search_categorize_transaction.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (categoryDAO==null){
      categoryDAO = new CategoryDAO();
    }

    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null){
     resp.sendError(HttpServletResponse.SC_FORBIDDEN);
     return;
     }
    session.setAttribute("currentPage",req.getRequestURL());
    int update = 0;
    int transaction_count=0;
    String query = (String) session.getAttribute("search");
    if(query==null||query.equals("")||query.length()<2){

    }
    else {
      String category = req.getParameter("category");
      try {
        update = transactionDAO.bulkUpdateCategory(user.getUser_ID(), category, query);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    req.setAttribute("updates", update);

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Search Transactions");
    resp.sendRedirect("all-Transactions");


  }
}