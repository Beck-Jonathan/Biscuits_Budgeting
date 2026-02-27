package com.beck.beck_demos.budget_app.controllers;


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
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet Viuw/Edit from the budget table
 Created By Jonathan Beck 2/26/2026
 ***************/
/**
 * Servlet implementation class EditbudgetServlet
 *
 * <p>This servlet handles the editing of {@link Budget} entries in the application.
 * It is mapped to the URL pattern <code>/editbudget</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the table for viewing a single budgets. Allows editing of appropriate fields. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>
 * <p><strong>HTTP POST</strong>: Processes form submissions for editing a budget. Validates input,
 * attempts update of the the database via {@link BudgetDAO}, and forwards back to the form view with
 * success or error messages as necessary. Successful updates redirect to the list of all budget.</p
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

@WebServlet("/editBudget")
public class EditBudgetServlet extends HttpServlet{
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

    String mode = req.getParameter("mode");
    String primaryKey = "";
    try{
      primaryKey = req.getParameter("budgetid");
    }catch (Exception e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    Budget budget= new Budget();
    try{
      budget.setbudget_id(primaryKey);
    } catch (Exception e){
      req.setAttribute("dbStatus",e.getMessage());
      resp.sendRedirect("all-budgets");
      return;
    }
    try{
      budget=budgetDAO.getBudgetByPrimaryKey(budget);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
      budget= null;
    }
    if (budget==null || budget.getbudget_id()==null){
      resp.sendRedirect("all-budgets");
      return;
    }
    session.setAttribute("Budget",budget);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit budget");

    List<String> allcurrency_codes = null;
    try {

      allcurrency_codes = budgetDAO.getDistinctcurrency_codeForDropdown();
      req.setAttribute("currency_codes", allcurrency_codes);
    } catch (Exception e){
      resp.sendRedirect("all-budgets");
    }
    req.getRequestDispatcher("WEB-INF/Budget_App/Editbudget.jsp").forward(req, resp);
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

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);

    List<String> allcurrency_codes = null;
//to set the drop downs
    try {

      allcurrency_codes = budgetDAO.getDistinctcurrency_codeForDropdown();
      req.setAttribute("currency_codes", allcurrency_codes);
    } catch (Exception e){
      resp.sendRedirect("all-budgets");
      return;
    }
//to get the old budget
    Budget _oldbudget= (Budget)session.getAttribute("Budget");
    if (_oldbudget==null){
      resp.sendRedirect("all-budgets");
      return;
    }
//to get the new budget's info

    String _name = req.getParameter("inputBudgetname");
    if (_name!=null){
      _name=_name.trim();
    }
    String _details = req.getParameter("inputBudgetdetails");
    if (_details!=null){
      _details=_details.trim();
    }
    String _start_date = req.getParameter("inputBudgetstart_date");
    if (_start_date!=null){
      _start_date=_start_date.trim();
    }
    String _limit_amount = req.getParameter("inputBudgetlimit_amount");
    if (_limit_amount!=null){
      _limit_amount=_limit_amount.trim();
    }
    String _currency_code_id = req.getParameter("inputBudgetcurrency_code_id");
    if (_currency_code_id!=null){
      _currency_code_id=_currency_code_id.trim();
    }
    String _is_active = req.getParameter("inputBudgetis_active");
    if (_is_active!=null){
      _is_active=_is_active.trim();
    }


    results.put("name",_name);
    results.put("details",_details);
    results.put("start_date",_start_date);
    results.put("limit_amount",_limit_amount);
    results.put("currency_code_id",_currency_code_id);
    results.put("is_active",_is_active);

    Budget _newbudget = new Budget();
    int errors =0;

    try {
      _newbudget.setname(_name);
    } catch(Exception e) {results.put("Budgetnameerror", e.getMessage());
      errors++;
    }
    try {
      _newbudget.setdetails(_details);
    } catch(Exception e) {results.put("Budgetdetailserror", e.getMessage());
      errors++;
    }
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate date = LocalDate.parse(_start_date, formatter);

      // 3. Pass it to your updated setter
      _newbudget.setstart_date(date);
    } catch(Exception e) {results.put("Budgetstart_dateerror", e.getMessage());
      errors++;
    }
    try {
      _newbudget.setlimit_amount(Double.valueOf(_limit_amount));
    } catch(Exception e) {results.put("Budgetlimit_amounterror", e.getMessage());
      errors++;
    }
    try {
      _newbudget.setcurrency_code_id(_currency_code_id);
    } catch(Exception e) {results.put("Budgetcurrency_code_iderror", e.getMessage());
      errors++;
    }
    try {
      _newbudget.setis_active(Boolean.parseBoolean(_is_active));
    } catch(Exception e) {results.put("Budgetis_activeerror", e.getMessage());
      errors++;
    }

//to update the database
    int result=0;
    if (errors==0){
      try{
        result=budgetDAO.update(_oldbudget,_newbudget);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Budget updated");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Budgets");
        return;
      } else {
        results.put("dbStatus","Budget Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit budget");
    req.getRequestDispatcher("WEB-INF/Budget_App/Editbudget.jsp").forward(req, resp);
  }
}

