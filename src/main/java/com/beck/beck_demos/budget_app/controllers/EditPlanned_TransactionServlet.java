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
import java.text.SimpleDateFormat;

@WebServlet("/updatePlannedTransaction")
public class EditPlanned_TransactionServlet extends HttpServlet {
  private iPlanned_TransactionDAO planned_transactionDAO;

  @Override
  public void init() {
    planned_transactionDAO = new Planned_TransactionDAO();
  }

  public void init(iPlanned_TransactionDAO planned_transactionDAO) {
    this.planned_transactionDAO = planned_transactionDAO;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level

//add roles here
    HttpSession session = req.getSession();
    String response = "0";
    User user = (User) session.getAttribute("User_B");
    if (user == null || !user.getRoles().contains("User")) {
      session.invalidate();
      response = "-1";
      sendResponse(resp, response);
      return;
    }
    Planned_Transaction oldTranasction = new Planned_Transaction();
    Planned_Transaction newTranasction = new Planned_Transaction();

    String _planned_transaction_ID = req.getParameter("inputplanned_transactionplanned_transaction_ID");
    if (_planned_transaction_ID != null) {
      _planned_transaction_ID = _planned_transaction_ID.trim();
    }

    String _subcategory_ID = req.getParameter("inputplanned_transactionsubcategory_ID");
    if (_subcategory_ID != null) {
      _subcategory_ID = _subcategory_ID.trim();
    }
    String _budget_id = req.getParameter("inputplanned_transactionbudget_id");
    if (_budget_id != null) {
      _budget_id = _budget_id.trim();
    }
    String _nickname = req.getParameter("inputplanned_transactionnickname");
    if (_nickname != null) {
      _nickname = _nickname.trim();
    }
    String _amount = req.getParameter("inputplanned_transactionamount");
    if (_amount != null) {
      _amount = _amount.trim();
    }
    String _start_date = req.getParameter("inputplanned_transactionstart_date");
    if (_start_date != null) {
      _start_date = _start_date.trim();
    }
    String _times_per_year = req.getParameter("inputplanned_transactiontimes_per_year");
    if (_times_per_year != null) {
      _times_per_year = _times_per_year.trim();
    }
    String _occurrences = req.getParameter("inputplanned_transactionoccurrences");
    if (_occurrences != null) {
      _occurrences = _occurrences.trim();
    }
    String _is_active = req.getParameter("inputplanned_transactionis_active");
    if (_is_active != null) {
      _is_active = _is_active.trim();
    }

    try {
      oldTranasction.setplanned_transaction_ID(_planned_transaction_ID);
    } catch (Exception e) {
      response = "-2";
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }

    try {
      oldTranasction.setuser_ID(user.getUser_ID());
    } catch (Exception e) {
      response = "-3";
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }
    try {
      newTranasction.setsubcategory_ID(_subcategory_ID);
    } catch (Exception e) {
      response = "-4";
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }
    try {
      newTranasction.setbudget_id(_budget_id);
    } catch (Exception e) {
      response = "-5";
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }
    try {
      newTranasction.setnickname(_nickname);
    } catch (Exception e) {
      response = "-6";
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }
    try {
      newTranasction.setamount(Double.valueOf(_amount));
    } catch (Exception e) {
      response = "-7";
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }
    try {
      java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(_start_date);
      newTranasction.setstart_date(utilDate);
    } catch (Exception e) {
      response = "-8";
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }
    try {
      newTranasction.settimes_per_year(Integer.valueOf(_times_per_year));
    } catch (Exception e) {
      response = "-9";
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }
    try {
      newTranasction.setoccurrences(Integer.valueOf(_occurrences));
    } catch (Exception e) {
      response = "-10";
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }
    try {
      newTranasction.setis_active(true);
    } catch (Exception e) {
      response = "-11";
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }

    int result = 0;

    try {
      result = planned_transactionDAO.update(oldTranasction, newTranasction);
    } catch (Exception ex) {
      response = "-12";
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }
    if (result > 0) {
      response = "1";
      resp.setStatus(200);
      sendResponse(resp, response);
    } else {
      response = "0";
      resp.setStatus(200);
      sendResponse(resp, response);

    }
  }

  private void sendResponse(HttpServletResponse response, String Result) {
    try {
      // 1. Set the status and headers before getting the writer
      response.setStatus(200);
      response.setContentType("text/plain");
      response.setCharacterEncoding("UTF-8");

      // 2. Use try-with-resources if you want to be safe,
      // though Tomcat generally manages the response writer for you.
      PrintWriter out = response.getWriter();
      out.print(Result);
      out.flush();

    } catch (IOException e) {
      // Log the error if the connection was closed before we could write
      System.err.println("Error writing response: " + e.getMessage());
    }
  }
}

