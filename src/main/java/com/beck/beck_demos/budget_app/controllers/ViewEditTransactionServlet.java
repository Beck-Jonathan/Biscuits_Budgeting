package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet Viuw/Edit from the Transaction table
 Created By Jonathan Beck 1/16/2025
 ***************/

@WebServlet("/editTransaction")
public class ViewEditTransactionServlet extends HttpServlet{


  private iTransactionDAO transactionDAO;

  private iCategoryDAO categoryDAO;
  @Override
  public void init() {
    transactionDAO = new TransactionDAO();
      categoryDAO = new CategoryDAO();

  }
  public void init(iTransactionDAO transactionDAO, iCategoryDAO categoryDAO){
    this.transactionDAO = transactionDAO;
    this.categoryDAO = categoryDAO;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession();


    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("budget_home");
      return;
    }
//To restrict this page based on privilege level

    String mode = req.getParameter("mode");
    String primaryKey = "";
    try{
      primaryKey = req.getParameter("transactionid");
    }catch (Exception e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    Transaction_VM transaction= new Transaction_VM();
    try{
      transaction.setTransaction_ID(primaryKey);
      transaction.setUser_ID(user.getUser_ID());
    } catch (Exception e){
      req.setAttribute("dbStatus",e.getMessage());
    }
    try{
      transaction=transactionDAO.getTransactionByPrimaryKey(transaction);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    // tp find next available comment id
    int comment_id=1;
    if (transaction.getTransaction_Comments()!=null&& !transaction.getTransaction_Comments().isEmpty()) {
      for (Transaction_Comment comment : transaction.getTransaction_Comments()) {
        if (comment.getTransaction_Comment_ID() >= comment_id) {
          comment_id = comment.getTransaction_Comment_ID() + 1;
        }
      }
    }

    session.setAttribute("commentID",comment_id);
    session.setAttribute("transaction",transaction);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit Transaction");
    List<SubCategory_VM> allCategories = null;
    try{
      allCategories = categoryDAO.getsubCategoryByUser(user.getUser_ID());
    }catch(Exception e){
      allCategories = new ArrayList<SubCategory_VM>();
    }

    req.setAttribute("Categorys", allCategories);
    req.getRequestDispatcher("WEB-INF/Budget_App/EditTransaction.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("budget_home");
      return;
    }

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);




//to get the old Transaction
    Transaction_VM _oldTransaction= (Transaction_VM)session.getAttribute("transaction");
//to get the new event's info

    String _Category_ID = req.getParameter("inputtransactionCategory_ID");
    if (_Category_ID!=null){
      _Category_ID=_Category_ID.trim();
    }
    String isLocked = req.getParameter(("inputtransactionIs_Locked"));
    if (isLocked == null || isLocked.isEmpty()) {
      isLocked = "false";
    }


    results.put("Category_ID",_Category_ID);
    results.put("Locked", isLocked);

    Transaction _newTransaction = new Transaction();
    int errors =0;
    try {
      _newTransaction.setUser_ID((user.getUser_ID()));
    } catch(Exception e) {results.put("transactionUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newTransaction.setCategory_ID(_Category_ID);
    } catch(Exception e) {results.put("transactionCategory_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newTransaction.setIs_Locked(Boolean.parseBoolean(isLocked));
    } catch (Exception e) {
      results.put("transactionCategory_IDerror", e.getMessage());
      errors++;
    }

//to update the database
    int result=0;
    if (errors==0){
      try{
        result=transactionDAO.update(_oldTransaction,_newTransaction);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Transaction updated");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Transactions");
        return;
      } else {
        results.put("dbStatus","Transaction Not Updated");
      }
    }
    List<SubCategory_VM> allCategories = null;
    try{
      allCategories = categoryDAO.getsubCategoryByUser(user.getUser_ID());
    }catch(Exception e){
      allCategories = new ArrayList<>();
    }

    req.setAttribute("Categorys", allCategories);
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit Transaction");
    req.getRequestDispatcher("WEB-INF/Budget_App/EditTransaction.jsp").forward(req, resp);
  }
}

