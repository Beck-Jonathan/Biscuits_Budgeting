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
import java.sql.SQLException;

@WebServlet("/update-planned-status")
public class ActivatePlanned_TransactionServlet extends HttpServlet {
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
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_B");

    // 1. Authorization Check (-1)
    if (user == null || !user.getRoles().contains("User")) {
      sendResponse(resp, -1);
      return;
    }

    String planned_transactionID = req.getParameter("planned_transactionid");
    String modeStr = req.getParameter("mode");

    // Input Validation (-2, -3)
    Planned_Transaction toChange = new Planned_Transaction();
    try {
      toChange.setplanned_transaction_ID(planned_transactionID);
      toChange.setuser_ID(user.getUser_ID());
    } catch (Exception e) {
      sendResponse(resp, -2); // Mapping to Invalid ID/Format
      return;
    }

    // Mode Logic
    try {
      int mode = Integer.parseInt(modeStr);
      int result;

      if (mode == 0) {
        // Deactivate (-4)
        result = planned_transactionDAO.deactivatePlanned_transaction(toChange);
      } else if (mode == 1) {
        // Reactivate (-5)
        result = planned_transactionDAO.reactivatePlanned_transaction(toChange);
      } else {
        result = -6; // Invalid Mode
      }

      sendResponse(resp, result);

    } catch (NumberFormatException nfe) {
      sendResponse(resp, -2);

    } catch (SQLException ex) {
      // General failure fallback
      sendResponse(resp, -4);
    } catch (Exception ex) {
      // General failure fallback
      sendResponse(resp, 0);
    }
  }

  private void sendResponse(HttpServletResponse response, Integer result) {
    try {
      response.setStatus(200);
      response.setContentType("text/plain");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      out.print(result.toString());
      out.flush();
    } catch (IOException e) {
      System.err.println("Error writing response: " + e.getMessage());
    }
  }
}