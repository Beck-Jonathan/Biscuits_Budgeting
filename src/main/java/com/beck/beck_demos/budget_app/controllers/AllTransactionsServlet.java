package com.beck.beck_demos.budget_app.controllers; /******************
 Create the Servlet  For Viewing all of the  Transaction table
 Created By Jonathan Beck 7/22/2024
 ***************/

import com.beck.beck_demos.budget_app.data.Bank_AccountDAO;
import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iBank_AccountDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.Transaction_VM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/all-Transactions")
public class AllTransactionsServlet extends HttpServlet {
  private iCategoryDAO categoryDAO;
  private iTransactionDAO transactionDAO;
  private iBank_AccountDAO bankAccountDAO;

  @Override
  public void init() throws ServletException {
    transactionDAO = new TransactionDAO();
    categoryDAO = new CategoryDAO();
    bankAccountDAO = new Bank_AccountDAO();
  }
  public void init (iTransactionDAO transactionDAO, iCategoryDAO categoryDAO,iBank_AccountDAO bankAccountDAO) {
    this.transactionDAO = transactionDAO;
    this.categoryDAO = categoryDAO;
    this.bankAccountDAO = bankAccountDAO;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_B");
    if (user == null || !user.getRoles().contains("User")) {
      resp.sendRedirect("budget_home");
      return;
    }

    // 1. Get Filters
    String category = req.getParameter("category") == null ? "" : req.getParameter("category");
    String bankAccountID = req.getParameter("bankAccountID") == null ? "" : req.getParameter("bankAccountID");
    boolean findErrors = "true".equals(req.getParameter("showErrors"));

    int year = 0;
    try {
      year = Integer.parseInt(req.getParameter("year"));
    } catch (Exception e) {
      year = 0;
    }
    int month = 0;
    try {
      month = Integer.parseInt(req.getParameter("month"));
    } catch (Exception e) {
      month = 0;
    }

    // 2. Get Sort & Direction
    String sort = req.getParameter("sort");
    if (sort == null || sort.isEmpty()) sort = "Date";

    int direction = 0;
    try {
      direction = Integer.parseInt(req.getParameter("direction"));
    } catch (Exception e) { direction = 0; }

    // Handle the "Reverse" toggle specifically
    if ("true".equals(req.getParameter("reverse"))) {
      direction = (direction == 0) ? 1 : 0;
    }

    // 3. Pagination
    int page_size = 20;
    int page_number = 1;
    try {
      page_number = Integer.parseInt(req.getParameter("page"));
    } catch (Exception e) { page_number = 1; }

    int offset = (page_number - 1) * page_size;

    // 4. Data Fetching
    try {
      int transaction_count = transactionDAO.getTransactionCountByUser(user.getUser_ID(), category, bankAccountID, year, month, findErrors);
      List<Transaction_VM> transactions = transactionDAO.getTransactionByUser(user.getUser_ID(), category, bankAccountID, year, month, page_size, offset, sort, direction, findErrors);

      req.setAttribute("Transactions", transactions);
      req.setAttribute("Categories", categoryDAO.getsubCategoryByUser(user.getUser_ID()));
      req.setAttribute("BankAccounts", bankAccountDAO.getDistinctBank_AccountForDropdown(user.getUser_ID()));
      req.setAttribute("transaction_count", transaction_count);
      req.setAttribute("noOfPages", (int) Math.ceil((double) transaction_count / page_size));
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    // 5. Attributes for JSP
    req.setAttribute("currentPage", page_number);
    req.setAttribute("category", category);
    req.setAttribute("bankAccountID", bankAccountID);
    req.setAttribute("showErrors", findErrors);
    req.setAttribute("sort", sort);
    req.setAttribute("direction", direction);
    req.setAttribute("year", year);
    req.setAttribute("pageTitle", "All Transactions");

    req.getRequestDispatcher("WEB-INF/Budget_App/all_Transactions.jsp").forward(req, resp);
  }
}
