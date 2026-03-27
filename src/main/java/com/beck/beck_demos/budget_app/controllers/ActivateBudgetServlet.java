package com.beck.beck_demos.budget_app.controllers; /******************
 Create the Servlet For Deleteing from the budget table
 Created By Jonathan Beck 3/26/2026
 ***************/

import com.beck.beck_demos.budget_app.data.BudgetDAO;
import com.beck.beck_demos.budget_app.iData.iBudgetDAO;
import com.beck.beck_demos.budget_app.models.Budget;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class DeletebudgetServlet
 *
 * <p>This servlet handles the deletion of  {@link Budget} entries in the application.
 * It is mapped to the URL pattern <code>/deletebudget</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: handles the request for deleting abudget via {@link BudgetDAO}. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p> * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 3/26/2026</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.beck_demos.budget_app.models.Budget
 * @see com.beck.beck_demos.budget_app.data.BudgetDAO
 * @see jakarta.servlet.http.HttpServlet
 */
@WebServlet("/activateBudget")
public class ActivateBudgetServlet extends HttpServlet {
  private iBudgetDAO budgetDAO;

  @Override
  public void init() {
    budgetDAO = new BudgetDAO();

  }

  public void init(iBudgetDAO budgetDAO) {
    this.budgetDAO = budgetDAO;

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    HttpSession session = req.getSession();
    String Role = "User";
    Integer result = 0;
    User user = (User) session.getAttribute("User_B");
    if (user == null || !user.getRoles().contains("User")) {
      result = -1;
      sendResponse(resp, result);
      return;
    }

    session.setAttribute("currentPage", req.getRequestURL());
    req.setAttribute("pageTitle", "Delete budget");
    String budgetID = req.getParameter("budgetid");
    int mode = -1;
    try {
      mode = Integer.valueOf(req.getParameter("mode"));
      if (mode != 0 && mode != 1) {
        throw new IllegalArgumentException("Invalid mode");
      }
    } catch (Exception e) {
      result = -2;
      sendResponse(resp, result);
      return;
    }
    Budget budget = new Budget();
    try {
      budget.setbudget_id(budgetID);
    } catch (Exception e) {
      result = -3;
      sendResponse(resp, result);
      return;
    }
    try {
      budget.setuser_id(user.getUser_ID());
    } catch (Exception e) {
      result = -4;
      sendResponse(resp, result);
      return;
    }
    if (mode == 0) {
      try {
        result = budgetDAO.deactivateBudget(budget);
        sendResponse(resp, result);
        return;
      } catch (Exception ex) {
        result = -5;
        sendResponse(resp, result);
        return;
      }
    }

    try {
      result = budgetDAO.activateBudget(budget);
    } catch (Exception ex) {
      result = -6;
      sendResponse(resp, result);
      return;
    }

    sendResponse(resp, result);

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

