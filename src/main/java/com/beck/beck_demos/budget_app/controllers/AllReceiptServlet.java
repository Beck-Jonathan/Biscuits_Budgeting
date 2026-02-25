package com.beck.beck_demos.budget_app.controllers;

/******************
 Create the Servlet  For Viewing all of the  Receipt table
 Created By Jonathan Beck 10/7/2025
 ***************/

import com.beck.beck_demos.budget_app.data.ReceiptDAO;
import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.Receipt;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iReceiptDAO;
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
 * Servlet implementation class AllReceiptServlet
 *
 * <p>This servlet handles the viewing of all {@link Receipt} entries in the application.
 * It is mapped to the URL pattern <code>/all-Receipts</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the table for viewing all Receipts from the database via {@link ReceiptDAO}. Allows earching and filtering by foreign keys. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>*
 * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 10/7/2025</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.beck_demos.budget_app.models.Receipt
 * @see com.beck.beck_demos.budget_app.data.ReceiptDAO
 * @see jakarta.servlet.http.HttpServlet
 */
@WebServlet("/all-Receipts")
public class AllReceiptServlet extends HttpServlet {private iReceiptDAO receiptDAO;
  private iTransactionDAO transactionDAO;
  @Override
  public void init() {
    receiptDAO = new ReceiptDAO();
    transactionDAO = new TransactionDAO();
  }
  public void init(iReceiptDAO receiptDAO,iTransactionDAO transactionDAO){
    this.receiptDAO = receiptDAO;
    this.transactionDAO = transactionDAO;
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      session.invalidate();
      resp.sendRedirect("budget_home");
      return;
    }

    int errors = 0;
    HashMap<String,String> results = new HashMap<>();
    String _transaction_id = req.getParameter("transaction_id");
    if (_transaction_id==null || _transaction_id.isEmpty()){
      _transaction_id = "";
    }
    int receipt_count=0;
    int page_number=1;
    int page_size = 20;
    try {
      page_number = Integer.parseInt(req.getParameter("page"));
    } catch (Exception e){
      page_number=1;
    }
    session.setAttribute("receipt_page_number",page_number);
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
    List<Receipt> receipts = null;
    try {
      receipt_count = receiptDAO.getReceiptCount(search_term, _transaction_id, user.getUser_ID());
      receipts =receiptDAO.getAllReceipt(offset,page_size,search_term, _transaction_id, user.getUser_ID());
    } catch (Exception e) {
      receipts = new ArrayList<>();
    }
    int total_pages = (receipt_count/page_size)+1;
    req.setAttribute("noOfPages", total_pages);
    req.setAttribute("currentPage", page_number);req.setAttribute("Receipts", receipts);
    req.setAttribute("pageTitle", "All Receipts");
    req.getRequestDispatcher("WEB-INF/Budget_App/all-Receipts.jsp").forward(req,resp);

  }
}
