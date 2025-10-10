package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/UTS")
public class EditTransactionServlet extends HttpServlet {

  private iTransactionDAO transactionDAO;

  @Override
  public void init() throws ServletException {
    transactionDAO = new TransactionDAO();
  }
  public void init (iTransactionDAO transactionDAO) {
    this.transactionDAO = transactionDAO;
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
    int errors=0;
    String Transaction_id =req.getParameter("t_id");


    String category = req.getParameter("category");
    //Transaction old_t= (Transaction)session.getAttribute("transaction");
    Transaction old_t = new Transaction();
    old_t.setUser_ID(user.getUser_ID());
    if (Transaction_id!=null&&!Transaction_id.equals("")) {
      old_t.setTransaction_ID(Transaction_id);
    }
    Transaction new_t = new Transaction();
    try {
      new_t.setCategory_ID(category);
    } catch(Exception e) {results.put("transactionCategory_IDerror", e.getMessage());
      errors++;
    }
    String status="";
    int result=0;
    if (errors==0) {
      try {
        result = transactionDAO.update_category(old_t, new_t);

      } catch (SQLException e) {
        status = "error";
      }
    }

    if (result==1){
      status="success";
    }
    else {
      status="error";
    }
    resp.setContentType("text/plain");
    resp.getWriter().write(status);





  }
}