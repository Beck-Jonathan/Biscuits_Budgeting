package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.Planned_TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iPlanned_TransactionDAO;
import com.beck.beck_demos.budget_app.models.Planned_Transaction;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/delete-planned_transaction")
public class DeletePlanned_TransactionServlet extends HttpServlet {
  private iPlanned_TransactionDAO planned_transactionDAO;

  @Override
  public void init() {
    planned_transactionDAO = new Planned_TransactionDAO();

  }

  public void init(iPlanned_TransactionDAO Planned_transaction_DAO) {
    this.planned_transactionDAO = Planned_transaction_DAO;

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Integer response = 0;

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_B");
    if (user == null || !user.getRoles().contains("User")) {
      session.invalidate();
      response = -1;
      sendResponse(resp, response);
      return;
    }

    String Planned_Transaction_ID = req.getParameter("Planned_Transaction_ID");
    Planned_Transaction transaction = new Planned_Transaction();
    try {
      transaction.setplanned_transaction_ID(Planned_Transaction_ID);
    } catch (Exception e) {
      response = -2;
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }
    try {
      transaction.setuser_ID(user.getUser_ID());
    } catch (Exception e) {
      response = -3;
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }

    try {
      response = planned_transactionDAO.deletePlanned_transaction(transaction);
      resp.setStatus(200);
      sendResponse(resp, response);

    } catch (Exception ex) {
      response = -4;
      resp.setStatus(200);
      sendResponse(resp, response);
    }

  }

  private void sendResponse(HttpServletResponse response, Integer Result) {
    try {
      // 1. Set the status and headers before getting the writer
      response.setStatus(200);
      response.setContentType("text/plain");
      response.setCharacterEncoding("UTF-8");

      // 2. Use try-with-resources if you want to be safe,
      // though Tomcat generally manages the response writer for you.
      PrintWriter out = response.getWriter();
      out.print(Result.toString());
      out.flush();

    } catch (IOException e) {
      // Log the error if the connection was closed before we could write
      System.err.println("Error writing response: " + e.getMessage());
    }
  }
}


