package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.MortgageDAO;
import com.beck.beck_demos.budget_app.models.Mortgage;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iMortgageDAO;
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
 Create the Servlet  For adding to The  Mortgage table
 Created By Jonathan Beck 10/3/2025
 ***************/
/**
 * Servlet implementation class AddMortgageServlet
 *
 * <p>This servlet handles the addition of new {@link Mortgage} entries in the application.
 * It is mapped to the URL pattern <code>/addMortgage</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the form for adding a new Mortgage. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>
 * <p><strong>HTTP POST</strong>: Processes form submissions for adding a new Mortgage. Validates input,
 * attempts insertion into the database via {@link MortgageDAO}, and forwards back to the form view with
 * success or error messages as necessary. Successful insertions redirect to the list of all Mortgage.</p
 > * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 10/3/2025</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.beck_demos.budget_app.models.Mortgage
 * @see com.beck.beck_demos.budget_app.data.MortgageDAO
 * @see jakarta.servlet.http.HttpServlet
 */

@WebServlet("/addMortgage")
public class AddMortgageServlet extends HttpServlet{
  private iMortgageDAO mortgageDAO;

  @Override
  public void init() {
    mortgageDAO = new MortgageDAO();
  }
  public void init(iMortgageDAO mortgageDAO){
    this.mortgageDAO = mortgageDAO;

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
      session.invalidate();
      resp.sendRedirect("budget_home");
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Mortgage");

    req.getRequestDispatcher("WEB-INF/Budget_App/AddMortgage.jsp").forward(req, resp);
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
      session.invalidate();
      resp.sendRedirect("budget_home");
      return;
    }



    String _Present_Value = req.getParameter("inputmortgagePresent_Value");
    if (_Present_Value!=null) {
      _Present_Value=_Present_Value.trim();
    }
    String _Future_Value = req.getParameter("inputmortgageFuture_Value");
    if (_Future_Value!=null) {
      _Future_Value=_Future_Value.trim();
    }
    String _Interest_Rate = req.getParameter("inputmortgageInterest_Rate");
    if (_Interest_Rate!=null) {
      _Interest_Rate=_Interest_Rate.trim();
    }
    String _Monthly_Payment = req.getParameter("inputmortgageMonthly_Payment");
    if (_Monthly_Payment!=null) {
      _Monthly_Payment=_Monthly_Payment.trim();
    }
    String _Extra_Payment = req.getParameter("inputmortgageExtra_Payment");
    if (_Extra_Payment!=null) {
      _Extra_Payment=_Extra_Payment.trim();
    }
    String _Remaining_Term = req.getParameter("inputmortgageRemaining_Term");
    if (_Remaining_Term!=null) {
      _Remaining_Term=_Remaining_Term.trim();
    }
    Map<String, String> results = new HashMap<>();

    results.put("Present_Value",_Present_Value);
    results.put("Future_Value",_Future_Value);
    results.put("Interest_Rate",_Interest_Rate);
    results.put("Monthly_Payment",_Monthly_Payment);
    results.put("Extra_Payment",_Extra_Payment);
    results.put("Remaining_Term",_Remaining_Term);
    Mortgage mortgage = new Mortgage();
    int errors =0;
    try {
      mortgage.setUser_ID(user.getUser_ID());
    } catch(Exception e) {results.put("mortgageUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      mortgage.setPresent_Value(Double.parseDouble(_Present_Value));
    } catch(Exception e) {results.put("mortgagePresent_Valueerror", e.getMessage());
      errors++;
    }
    try {
      mortgage.setFuture_Value(Double.valueOf(_Future_Value));
    } catch(Exception e) {results.put("mortgageFuture_Valueerror", e.getMessage());
      errors++;
    }
    try {
      mortgage.setInterest_Rate(Double.valueOf(_Interest_Rate));
    } catch(Exception e) {results.put("mortgageInterest_Rateerror", e.getMessage());
      errors++;
    }
    try {
      mortgage.setMonthly_Payment(Double.valueOf(_Monthly_Payment));
    } catch(Exception e) {results.put("mortgageMonthly_Paymenterror", e.getMessage());
      errors++;
    }
    try {
      mortgage.setExtra_Payment(Double.valueOf(_Extra_Payment));
    } catch(Exception e) {results.put("mortgageExtra_Paymenterror", e.getMessage());
      errors++;
    }
    try {
      mortgage.setRemaining_Term(Integer.valueOf(_Remaining_Term));
    } catch(Exception e) {results.put("mortgageRemaining_Termerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=mortgageDAO.add(mortgage);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Mortgage Added");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Mortgages");
        return;
      } else {
        results.put("dbStatus","Mortgage Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Add Mortgage");
    req.getRequestDispatcher("WEB-INF/budget_app/AddMortgage.jsp").forward(req, resp);

  }
}

