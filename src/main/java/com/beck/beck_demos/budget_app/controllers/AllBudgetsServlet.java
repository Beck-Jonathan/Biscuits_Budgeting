package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.BudgetDAO;
import com.beck.beck_demos.budget_app.models.Budget;
import com.beck.beck_demos.budget_app.models.Budget_VM;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iBudgetDAO;
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
/**
 * Servlet implementation class AllbudgetServlet
 *
 * <p>This servlet handles the viewing of all {@link Budget} entries in the application.
 * It is mapped to the URL pattern <code>/all-budgets</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the table for viewing all budgets from the database via {@link BudgetDAO}. Allows earching and filtering by foreign keys. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>*
 * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
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
@WebServlet("/all-Budgets")
public class AllBudgetsServlet extends HttpServlet {
  private iBudgetDAO budgetDAO;

  @Override
  public void init()  {
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

    int errors = 0;
    HashMap<String,String> results = new HashMap<>();
    String _user_id = req.getParameter("user_id");
    if (_user_id==null || _user_id.isEmpty()){
      _user_id = "";
    }
    String _currency_code_id = req.getParameter("currency_code_id");
    if (_currency_code_id==null || _currency_code_id.isEmpty()){
      _currency_code_id = "";
    }
    int budget_count=0;
    int page_number=1;
    int page_size = 20;
    try {
      page_number = Integer.parseInt(req.getParameter("page"));
    } catch (Exception e){
      page_number=1;
    }
    session.setAttribute("budget_page_number",page_number);
    int offset=(page_number-1)*(page_size);
    String search_term = req.getParameter("search");
    if (search_term==null){
      search_term ="";
    }
    if (!search_term.equals("") && (search_term.length()<2||search_term.length()>100)){
      errors++;
      results.put("searchError","Invalid search term");
    }
    session.setAttribute("currentPage",req.getRequestURL());
    List<Budget_VM> budgets = null;
    try {

      List<String> allcurrency_codes = budgetDAO.getDistinctcurrency_codeForDropdown();
      req.setAttribute("currency_codes", allcurrency_codes);
      budget_count = budgetDAO.getbudgetCount(search_term,user.getUser_ID(),_currency_code_id);
      budgets =budgetDAO.getAllbudget(offset,page_size,search_term, user.getUser_ID(), _currency_code_id);
    } catch (Exception e) {
      budgets = new ArrayList<>();
    }
    int total_pages = (budget_count/page_size)+1;
    req.setAttribute("noOfPages", total_pages);
    req.setAttribute("currentPage", page_number);
    req.setAttribute("budgets", budgets);
    req.setAttribute("pageTitle", "All Budgets");
    req.setAttribute("count",budget_count);
    req.getRequestDispatcher("WEB-INF/Budget_App/all-budgets.jsp").forward(req,resp);

  }
}

