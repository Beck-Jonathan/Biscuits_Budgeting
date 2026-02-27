package com.beck.beck_demos.budget_app.controllers;


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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
/******************
 Create the Servlet  For adding to The  budget table
 Created By Jonathan Beck 2/26/2026
 ***************/
/**
 * Servlet implementation class AddbudgetServlet
 *
 * <p>This servlet handles the addition of new {@link Budget} entries in the application.
 * It is mapped to the URL pattern <code>/addbudget</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the form for adding a new budget. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>
 * <p><strong>HTTP POST</strong>: Processes form submissions for adding a new budget. Validates input,
 * attempts insertion into the database via {@link BudgetDAO}, and forwards back to the form view with
 * success or error messages as necessary. Successful insertions redirect to the list of all budget.</p
 > * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
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

@WebServlet("/addbudget")
public class AddBudgetServlet extends HttpServlet{
  private iBudgetDAO budgetDAO;


  @Override
  public void init() {
    budgetDAO = new BudgetDAO();

  }
  public void init(iBudgetDAO budgetDAO){
    this.budgetDAO = budgetDAO;

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


    List<String> allcurrency_codes = null;
    try{

      allcurrency_codes = budgetDAO.getDistinctcurrency_codeForDropdown();
    } catch (Exception e){

      allcurrency_codes= new ArrayList<>();
    }

    req.setAttribute("currency_codes", allcurrency_codes);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Budget");
    req.getRequestDispatcher("WEB-INF/Budget_App/Addbudget.jsp").forward(req, resp);
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


    String _name = req.getParameter("inputbudgetname");
    if (_name!=null) {
      _name=_name.trim();
    }
    String _details = req.getParameter("inputbudgetdetails");
    if (_details!=null) {
      _details=_details.trim();
    }
    String _start_date = req.getParameter("inputbudgetstart_date");
    if (_start_date!=null) {
      _start_date=_start_date.trim();
    }
    String _limit_amount = req.getParameter("inputbudgetlimit_amount");
    if (_limit_amount!=null) {
      _limit_amount=_limit_amount.trim();
    }
    String _currency_code_id = req.getParameter("inputbudgetcurrency_code_id");
    if (_currency_code_id!=null) {
      _currency_code_id=_currency_code_id.trim();
    }
//    String _is_active = req.getParameter("inputbudgetis_active");
//    if (_is_active!=null) {
//      _is_active=_is_active.trim();
//    }

    Map<String, String> results = new HashMap<>();

    results.put("name",_name);
    results.put("details",_details);
    results.put("start_date",_start_date);
    results.put("limit_amount",_limit_amount);
    results.put("currency_code_id",_currency_code_id);
    //results.put("is_active",_is_active);

    Budget budget = new Budget();
    int errors =0;
    try {
      budget.setuser_id(user.getUser_ID());
    } catch(Exception e) {results.put("budgetuser_iderror", e.getMessage());
      errors++;
    }
    try {
      budget.setname(_name);
    } catch(Exception e) {results.put("budgetnameerror", e.getMessage());
      errors++;
    }
    try {
      budget.setdetails(_details);
    } catch(Exception e) {results.put("budgetdetailserror", e.getMessage());
      errors++;
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate date = LocalDate.parse(_start_date, formatter);

      // 3. Pass it to your updated setter
      budget.setstart_date(date);
    } catch(Exception e) {results.put("budgetstart_dateerror", e.getMessage());
      errors++;
    }
    try {
      budget.setlimit_amount(Double.valueOf(_limit_amount));
    } catch(Exception e) {results.put("budgetlimit_amounterror", e.getMessage());
      errors++;
    }
    try {
      budget.setcurrency_code_id(_currency_code_id);
    } catch(Exception e) {results.put("budgetcurrency_code_iderror", e.getMessage());
      errors++;
    }
//    try {
//      budget.setis_active(Boolean.parseBoolean(_is_active));
//    } catch(Exception e) {results.put("budgetis_activeerror", e.getMessage());
//      errors++;
//    }
    int result=0;
    if (errors==0){
      try{
        result=budgetDAO.add(budget);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","budget Added");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Budgets");
        return;
      } else {
        results.put("dbStatus","budget Not Added");

      }
    }

    List<String> allcurrency_codes = null;
    try{

      allcurrency_codes = budgetDAO.getDistinctcurrency_codeForDropdown();
    } catch (Exception e){

      allcurrency_codes= new ArrayList<>();
    }

    req.setAttribute("currency_codes", allcurrency_codes);
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Add Budget");
    req.getRequestDispatcher("WEB-INF/Budget_App/Addbudget.jsp").forward(req, resp);

  }
}

