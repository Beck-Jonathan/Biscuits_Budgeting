package com.beck.beck_demos.budget_app.controllers;

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

/******************
 Create the Servlet  For adding to The  Transaction_Comment table
 Created By Jonathan Beck 2/25/2025
 ***************/

@WebServlet("/addTransaction_Comment")
public class AddTransaction_CommentServlet extends HttpServlet{
  private iTransaction_CommentDAO transaction_commentDAO;

  @Override
  public void init() {
    transaction_commentDAO = new Transaction_CommentDAO();

  }
  public void init(iTransaction_CommentDAO transaction_commentDAO){
    this.transaction_commentDAO = transaction_commentDAO;

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
    req.setAttribute("pageTitle", "Add Transaction_Comment");

    req.getRequestDispatcher("WEB-INF/Budget_App/AddTransaction_Comment.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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



    String _Transaction_ID = req.getParameter("inputtransaction_commentTransaction_ID");
    if (_Transaction_ID!=null) {
      _Transaction_ID=_Transaction_ID.trim();
    }
    String _Transaction_Comment_ID = req.getParameter("inputtransaction_commentTransaction_Comment_ID");
    if (_Transaction_Comment_ID!=null) {
      _Transaction_Comment_ID=_Transaction_Comment_ID.trim();
    }
    String _Content = req.getParameter("inputtransaction_commentContent");
    if (_Content!=null) {
      _Content=_Content.trim();
    }

    Map<String, String> results = new HashMap<>();

    results.put("Transaction_ID",_Transaction_ID);
    results.put("Transaction_Comment_ID",_Transaction_Comment_ID);
    results.put("Content",_Content);

    Transaction_Comment transaction_comment = new Transaction_Comment();
    int errors =0;
    try {
      transaction_comment.setUser_ID(user.getUser_ID());
    } catch(Exception e) {results.put("transaction_commentUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      transaction_comment.setTransaction_ID(_Transaction_ID);
    } catch(Exception e) {results.put("transaction_commentTransaction_IDerror", e.getMessage());
      errors++;
    }
    try {
      transaction_comment.setTransaction_Comment_ID(Integer.valueOf(_Transaction_Comment_ID));
    } catch(Exception e) {results.put("transaction_commentTransaction_Comment_IDerror", e.getMessage());
      errors++;
    }
    try {
      transaction_comment.setContent(_Content);
    } catch(Exception e) {results.put("transaction_commentContenterror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=transaction_commentDAO.add(transaction_comment);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Transaction Comment Added");
        req.setAttribute("results",results);
        resp.sendRedirect("editTransaction?transactionid="+_Transaction_ID);
        return;
      } else {
        results.put("dbStatus","Transaction_Comment Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Transaction_Comment ");
    resp.sendRedirect("editTransaction?transactionid="+_Transaction_ID);
  }
}
