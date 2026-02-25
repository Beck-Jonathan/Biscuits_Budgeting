package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.Transaction_CommentDAO;
import com.beck.beck_demos.budget_app.models.Transaction_Comment;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iTransaction_CommentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class EditTransaction_CommentServlet extends HttpServlet{

  private iTransaction_CommentDAO transaction_commentDAO;
  public void init() {
    transaction_commentDAO = new Transaction_CommentDAO();
  }
  public void init(iTransaction_CommentDAO transaction_commentDAO){
    this.transaction_commentDAO = transaction_commentDAO;

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
      resp.sendRedirect("budget_home");
      return;
    }

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);

//to get the old Transaction_Comment
    Transaction_Comment _oldTransaction_Comment= new Transaction_Comment();
//to get the new event's info

    String _Transaction_ID = req.getParameter("inputtransaction_commentTransaction_ID");
    if (_Transaction_ID!=null){
      _Transaction_ID=_Transaction_ID.trim();
    }
    String _Transaction_Comment_ID = req.getParameter("inputtransaction_commentTransaction_Comment_ID");
    if (_Transaction_Comment_ID!=null){
      _Transaction_Comment_ID=_Transaction_Comment_ID.trim();
    }
    String _Content = req.getParameter("inputtransaction_commentContent");
    if (_Content!=null){
      _Content=_Content.trim();
    }
    String _oldContent = req.getParameter("inputtransaction_commentOldContent");
    if (_oldContent!=null){
      _oldContent=_oldContent.trim();
    }


    results.put("Transaction_ID",_Transaction_ID);
    results.put("Transaction_Comment_ID",_Transaction_Comment_ID);
    results.put("Content",_Content);
    results.put("oldContent",_oldContent);

    Transaction_Comment _newTransaction_Comment = new Transaction_Comment();
    int errors =0;
    try {
      _oldTransaction_Comment.setUser_ID(user.getUser_ID());
    } catch(Exception e) {results.put("transaction_commentUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      _oldTransaction_Comment.setTransaction_ID(_Transaction_ID);
    } catch(Exception e) {results.put("transaction_commentTransaction_IDerror", e.getMessage());
      errors++;
    }
    try {
      _oldTransaction_Comment.setTransaction_Comment_ID(Integer.valueOf(_Transaction_Comment_ID));
    } catch(Exception e) {results.put("transaction_commentTransaction_Comment_IDerror", e.getMessage());
      errors++;
    }
    try {
      _oldTransaction_Comment.setContent(_oldContent);
      _newTransaction_Comment.setContent(_Content);
    } catch(Exception e) {results.put("transaction_commentContenterror", e.getMessage());
      errors++;
    }

//to update the database
    int result=0;
    if (errors==0){
      try{
        result=transaction_commentDAO.update(_oldTransaction_Comment,_newTransaction_Comment);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Transaction_Comment updated");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Transaction_Comments");
        return;
      } else {
        results.put("dbStatus","Transaction_Comment Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Transaction_Comment ");
    req.getRequestDispatcher("WEB-INF/budget_app/EditTransaction_Comment.jsp").forward(req, resp);
  }
}

