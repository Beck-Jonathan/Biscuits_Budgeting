package com.beck.beck_demos.budget_app.controllers;


import com.beck.beck_demos.budget_app.data.ReceiptDAO;
import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.Receipt;
import com.beck.beck_demos.budget_app.models.Transaction;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iReceiptDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet Viuw/Edit from the Receipt table
 Created By Jonathan Beck 10/8/2025
 ***************/
/**
 * Servlet implementation class EditReceiptServlet
 *
 * <p>This servlet handles the editing of {@link Receipt} entries in the application.
 * It is mapped to the URL pattern <code>/editReceipt</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the table for viewing a single Receipts. Allows editing of appropriate fields. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>
 * <p><strong>HTTP POST</strong>: Processes form submissions for editing a Receipt. Validates input,
 * attempts update of the the database via {@link ReceiptDAO}, and forwards back to the form view with
 * success or error messages as necessary. Successful updates redirect to the list of all Receipt.</p
 > * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 10/8/2025</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.beck_demos.budget_app.models.Receipt
 * @see com.beck.beck_demos.budget_app.data.ReceiptDAO
 * @see jakarta.servlet.http.HttpServlet
 */

@WebServlet("/editReceipt")
public class EditReceiptServlet extends HttpServlet{
  private iReceiptDAO receiptDAO;
  private iTransactionDAO transactionDAO;
  @Override
  public void init()  {
    receiptDAO = new ReceiptDAO();
    transactionDAO = new TransactionDAO();
  }
  public void init(iReceiptDAO receiptDAO,iTransactionDAO transactionDAO){
    this.receiptDAO = receiptDAO;
    this.transactionDAO = transactionDAO;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
      primaryKey = req.getParameter("receiptid");
    }catch (Exception e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    Receipt receipt= new Receipt();
    try{
      receipt.setReceipt_ID(primaryKey);
    } catch (Exception e){
      req.setAttribute("dbStatus",e.getMessage());
      resp.sendRedirect("all-receipts");
      return;
    }
    try{
      receipt=receiptDAO.getReceiptByPrimaryKey(receipt);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
      receipt= null;
    }
    if (receipt==null || receipt.getReceipt_ID()==null){
      resp.sendRedirect("all-Receipts");
      return;
    }
    session.setAttribute("receipt",receipt);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit Receipt");
    List<Transaction> allTransactions = null;
    try {
      allTransactions = transactionDAO.getDistinctTransactionForDropdown(user.getUser_ID());
      req.setAttribute("Transactions", allTransactions);
    } catch (Exception e){
      resp.sendRedirect("all-Receipts");
    }
    req.getRequestDispatcher("WEB-INF/Budget_App/EditReceipt.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
//to set the drop downs
    List<Transaction> allTransactions = null;
    try {
      allTransactions = transactionDAO.getDistinctTransactionForDropdown(user.getUser_ID());
      req.setAttribute("Transactions", allTransactions);
    } catch (Exception e){
      resp.sendRedirect("all-Receipts");
      return;
    }
//to get the old Receipt
    Receipt _oldReceipt= (Receipt)session.getAttribute("receipt");
    if (_oldReceipt==null){
      resp.sendRedirect("all-receipts");
      return;
    }
//to get the new event's info
    String _Receipt_ID = req.getParameter("inputreceiptReceipt_ID");
    if (_Receipt_ID!=null){
      _Receipt_ID=_Receipt_ID.trim();
    }
    String _Transaction_ID = req.getParameter("inputreceiptTransaction_ID");
    if (_Transaction_ID!=null){
      _Transaction_ID=_Transaction_ID.trim();
    }
    String _Image_Link = req.getParameter("inputreceiptImage_Link");
    if (_Image_Link!=null){
      _Image_Link=_Image_Link.trim();
    }
    String _Name = req.getParameter("inputreceiptName");
    if (_Name!=null){
      _Name=_Name.trim();
    }
    String _Description = req.getParameter("inputreceiptDescription");
    if (_Description!=null){
      _Description=_Description.trim();
    }
    results.put("Receipt_ID",_Receipt_ID);
    results.put("Transaction_ID",_Transaction_ID);
    results.put("Image_Link",_Image_Link);
    results.put("Name",_Name);
    results.put("Description",_Description);
    Receipt _newReceipt = new Receipt();
    int errors =0;

    try {
      _newReceipt.setTransaction_ID(_Transaction_ID);
    } catch(Exception e) {results.put("receiptTransaction_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newReceipt.setImage_Link(_Image_Link);
    } catch(Exception e) {results.put("receiptImage_Linkerror", e.getMessage());
      errors++;
    }
    try {
      _newReceipt.setName(_Name);
    } catch(Exception e) {results.put("receiptNameerror", e.getMessage());
      errors++;
    }
    try {
      _newReceipt.setDescription(_Description);
    } catch(Exception e) {results.put("receiptDescriptionerror", e.getMessage());
      errors++;
    }
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=receiptDAO.update(_oldReceipt,_newReceipt);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Receipt updated");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Receipts");
        return;
      } else {
        results.put("dbStatus","Receipt Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit Receipt");
    req.getRequestDispatcher("WEB-INF/Budget_App/EditReceipt.jsp").forward(req, resp);
  }
}

