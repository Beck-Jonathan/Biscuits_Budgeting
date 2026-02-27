package com.beck.beck_demos.budget_app.controllers;

/******************
 Create the Servlet For Deleteing from the budget table
 Created By Jonathan Beck 2/26/2026
 ***************/

import com.beck.beck_demos.budget_app.data.BudgetDAO;
import com.beck.beck_demos.budget_app.models.Budget;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iBudgetDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Servlet implementation class AddbudgetServlet
 *
 * <p>This servlet handles the deletion of  {@link Budget} entries in the application.
 * It is mapped to the URL pattern <code>/deletebudget</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: handles the request for deleting abudget via {@link BudgetDAO}. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p> * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 2/26/2026</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.beck_demos.budget_app.models.Budget
 * @see com.beck.beck_demos.budget_app.data.BudgetDAO
 * @see jakarta.servlet.http.HttpServlet
 */
@WebServlet("/deleteBudget")
public class DeleteBudgetServlet extends HttpServlet {
  private iBudgetDAO budgetDAO;

  @Override
  public void init()  {
    budgetDAO = new BudgetDAO();

  }
  public void init(iBudgetDAO BudgetDAO){
    this.budgetDAO = BudgetDAO;

  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    Integer result=0;
    if (user==null||!user.getRoles().contains("User")){
      session.invalidate();
      result=-1;
      sendResponse(resp,result);
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete budget");
    String budgetID = req.getParameter("budgetid");

    try{
      result = budgetDAO.deletebudget(budgetID);
      sendResponse(resp,result);
      return;
    }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
        result=-2;
        sendResponse(resp,result);
        return;
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

