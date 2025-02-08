package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.data.Saved_Search_OrderDAO;
import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.iData.iSaved_Search_OrderDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/apply-Saved_Search_Order")
public class ApplySaved_Search_Order extends HttpServlet {

  private iTransactionDAO transactionDAO;
  private iSaved_Search_OrderDAO saved_search_orderDAO;

  @Override
  public void init() throws ServletException {
    transactionDAO = new TransactionDAO();
    saved_search_orderDAO = new Saved_Search_OrderDAO();

  }
  public void init(iTransactionDAO transactionDAO, iSaved_Search_OrderDAO saved_search_orderDAO){
    this.transactionDAO = transactionDAO;
    this.saved_search_orderDAO = saved_search_orderDAO;
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    int primaryKey = -1;
    try{
      primaryKey = Integer.parseInt(req.getParameter("saved_search_orderid"));
    }catch (Exception e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    Saved_Search_Order_VM saved_search_order= new Saved_Search_Order_VM();

    try{
      saved_search_order.setSaved_Search_Order_ID(primaryKey);
      saved_search_order = saved_search_orderDAO.getSaved_Search_OrderByPrimaryKey(saved_search_order);
    } catch (Exception e){
      req.setAttribute("dbStatus",e.getMessage());
    }
    int result = 0;
    for (Saved_Search_Order_Line line : saved_search_order.getSaved_Search_Order_Lines()) {

      try {
        result += transactionDAO.bulkUpdateCategory(user.getUser_ID(), line.getCategory_ID(), line.getSearch_Phrase());
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    req.setAttribute("updates", result);

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Search Transactions");
    resp.sendRedirect("all-Transactions");


  }
}
