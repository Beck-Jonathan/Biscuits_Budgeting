package com.beck.beck_demos.budget_app.controllers;
/******************
 Create the Servlet For Deleteing from the Transaction_Comment table
 Created By Jonathan Beck 2/27/2025
 ***************/

import com.beck.beck_demos.budget_app.data.Transaction_CommentDAO;
import com.beck.beck_demos.budget_app.models.Transaction_Comment;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iTransaction_CommentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/deletetransaction_comment")

public class DeleteTransaction_CommentServlet extends HttpServlet {
  private iTransaction_CommentDAO  transaction_commentDAO;
  @Override
  public void init() {
    transaction_commentDAO = new Transaction_CommentDAO();
  }
  public void init(iTransaction_CommentDAO transaction_commentDAO) {
    this.transaction_commentDAO = transaction_commentDAO;
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete Transaction_Comment");
    int Transaction_CommentID=-1;
    String Transaction_ID="";
    int errors = 0;
    try {
      Transaction_CommentID = Integer.valueOf(req.getParameter("transaction_commentid"));
    } catch (Exception e) {
      errors ++;
    }
    try {
      Transaction_ID = req.getParameter("transaction_id");
    } catch(Exception e) {
      errors ++;
    }
    Transaction_Comment toDelete = new Transaction_Comment();
    try {
      toDelete.setUser_ID(user.getUser_ID());
    } catch (Exception e) {
      errors ++;
    }
    try {
      toDelete.setTransaction_Comment_ID(Transaction_CommentID);
    } catch (Exception e) {
      errors ++;
    }
    try {
      toDelete.setTransaction_ID(Transaction_ID);
    } catch (Exception e) {
      errors ++;
    }

    int result = 0;
    if (errors ==0){
      try{
        result = transaction_commentDAO.deleteTransaction_Comment(toDelete);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }

    List<Transaction_Comment> transaction_comments = null;
    req.setAttribute("result",result);
    req.setAttribute("results",results);
    req.setAttribute("Transaction_Comments", transaction_comments);
    req.setAttribute("pageTitle", "All Transaction_Comment");
    resp.sendRedirect("editTransaction?transactionid="+Transaction_ID);  }
}
