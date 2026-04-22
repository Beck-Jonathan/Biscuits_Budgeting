package com.beck.beck_demos.budget_app.controllers;

/******************
 Create the Servlet  For Viewing all of the  planned_transaction table
 Created By Jonathan Beck 3/31/2026
 ***************/

import com.beck.beck_demos.budget_app.data.BudgetDAO;
import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.data.Planned_TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iBudgetDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.iData.iPlanned_TransactionDAO;
import com.beck.beck_demos.budget_app.models.*;
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

/**
 * Servlet implementation class Allplanned_transactionServlet
 *
 * <p>This servlet handles the viewing of all {@link Planned_Transaction} entries in the application.
 * It is mapped to the URL pattern <code>/all-planned_transactions</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the table for viewing all planned_transactions from the database via
 * {@link Planned_TransactionDAO}. Allows earching and filtering by foreign keys. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>*
 * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 3/31/2026</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.beck_demos.budget_app.models.Planned_Transaction
 * @see com.beck.beck_demos.budget_app.data.Planned_TransactionDAO
 * @see jakarta.servlet.http.HttpServlet
 */
@WebServlet("/all-planned_transactions")
public class AllPlanned_TransactionsServlet extends HttpServlet {
  private iPlanned_TransactionDAO planned_transactionDAO;

  private iCategoryDAO subcategoryDAO;
  private iBudgetDAO budgetDAO;

  @Override
  public void init() {
    planned_transactionDAO = new Planned_TransactionDAO();

    subcategoryDAO = new CategoryDAO();
    budgetDAO = new BudgetDAO();
  }

  public void init(iPlanned_TransactionDAO planned_transactionDAO, iCategoryDAO subcategoryDAO, iBudgetDAO budgetDAO) {
    this.planned_transactionDAO = planned_transactionDAO;

    this.subcategoryDAO = subcategoryDAO;
    this.budgetDAO = budgetDAO;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_B");
    if (user == null || !user.getRoles().contains("User")) {
      resp.sendRedirect("budget_home");
      return;
    }

    int errors = 0;
    HashMap<String, String> results = new HashMap<>();
    String _user_id = req.getParameter("user_id");
    if (_user_id == null || _user_id.isEmpty()) {
      _user_id = "";
    }
    String _subcategory_id = req.getParameter("subcategory_id");
    if (_subcategory_id == null || _subcategory_id.isEmpty()) {
      _subcategory_id = "";
    }
    String _budget_id = req.getParameter("budget_id");
    if (_budget_id == null || _budget_id.isEmpty()) {
      _budget_id = null;
    }
    int planned_transaction_count = 0;
    int page_number = 1;
    int page_size = 20;
    try {
      page_number = Integer.parseInt(req.getParameter("page"));
    } catch (Exception e) {
      page_number = 1;
    }
    session.setAttribute("planned_transaction_page_number", page_number);
    int offset = (page_number - 1) * (page_size);
    String search_term = req.getParameter("search");
    if (search_term == null) {
      search_term = "";
    }
    if (!search_term.equals("") && (search_term.length() < 2 || search_term.length() > 100)) {
      errors++;
      results.put("searchError", "Invalid search term");
    }
    session.setAttribute("currentPage", req.getRequestURL());
    List<Planned_Transaction_VM> planned_transactions = null;
    try {

      List<SubCategory> allCategories = subcategoryDAO.getsubCategoryByUserForDropdown(user);

      List<Budget_VM> allBudgets = budgetDAO.getAllActiveBudgetsWithLines(user.getUser_ID());

      req.setAttribute("subcategorys", allCategories);
      req.setAttribute("budgets", allBudgets);
      //planned_transaction_count = planned_transactionDAO.getplanned_transactionCount(search_term,user_ID,subcategory_ID);
      planned_transactions = planned_transactionDAO.getAllplanned_transaction(offset, page_size, search_term, user.getUser_ID(), _subcategory_id, _budget_id);
    } catch (Exception e) {
      planned_transactions = new ArrayList<>();
    }
    int total_pages = (planned_transaction_count / page_size) + 1;
    req.setAttribute("noOfPages", total_pages);
    req.setAttribute("currentPage", page_number);
    req.setAttribute("planned_transactions", planned_transactions);
    req.setAttribute("pageTitle", "All Planned Transactions");
    req.setAttribute("count", planned_transaction_count);
    req.getRequestDispatcher("WEB-INF/Budget_App/AllPlanned_transactions.jsp").forward(req, resp);

  }
}
