package com.beck.beck_demos.budget_app.controllers;


import com.beck.beck_demos.budget_app.data.AWSDAO;
import com.beck.beck_demos.budget_app.data.ReceiptDAO;
import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iAWSDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.iData.iUserDAO;
import com.beck.beck_demos.budget_app.models.Receipt;
import com.beck.beck_demos.budget_app.models.Transaction;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iReceiptDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletDiskFileUpload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  Receipt table
 Created By Jonathan Beck 10/8/2025
 ***************/
/**
 * Servlet implementation class AddReceiptServlet
 *
 * <p>This servlet handles the addition of new {@link Receipt} entries in the application.
 * It is mapped to the URL pattern <code>/addReceipt</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the form for adding a new Receipt. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>
 * <p><strong>HTTP POST</strong>: Processes form submissions for adding a new Receipt. Validates input,
 * attempts insertion into the database via {@link ReceiptDAO}, and forwards back to the form view with
 * success or error messages as necessary. Successful insertions redirect to the list of all Receipt.</p
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

@WebServlet("/addReceipt")
@MultipartConfig(

    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AddReceiptServlet extends HttpServlet{
  private static final String UPLOAD_DIR = "images\\budget\\";
  private static final String bucketName = "budgetreceipts";
  private iReceiptDAO receiptDAO;
  private iTransactionDAO transactionDAO;
  private iAWSDAO awsdao;
  @Override
  public void init() {
    receiptDAO = new ReceiptDAO();
    transactionDAO = new TransactionDAO();
    awsdao = new AWSDAO();
  }
  public void init(iReceiptDAO receiptDAO,iTransactionDAO transactionDAO, iAWSDAO awsdao){
    this.receiptDAO = receiptDAO;
    this.transactionDAO = transactionDAO;
    this.awsdao = awsdao;
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
    String transactionID = req.getParameter("transactionID");
    if (transactionID!=null) {
      transactionID=transactionID.trim();
    }
    else {
      transactionID="";
    }
    req.setAttribute("_transaction_ID",transactionID);

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Receipt");
    List<Transaction> allTransactions = null;
    try {
      allTransactions = transactionDAO.getDistinctTransactionForDropdown(user.getUser_ID());
    }catch (Exception e){
        allTransactions= new ArrayList<>();
      }
      req.setAttribute("Transactions", allTransactions);
      req.getRequestDispatcher("WEB-INF/Budget_App/AddReceipt.jsp").forward(req, resp);
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
    String _Receipt_ID = "";
    String _Transaction_ID = "";
    String _Image_Link = "";
    String _Name = "";
    String _Description = "";

    Receipt receipt = new Receipt();


    int errors =0;
    List<File> files = new ArrayList<>();
    JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload();
    List<DiskFileItem> items;
    try {
      items = upload.parseRequest(req);
    } catch (Exception e) {
      errors++;
      results.put("pictureUploadError", e.getMessage());
      items = new ArrayList<>();
    }
    String applicationPath = req.getServletContext().getRealPath("");
    try {
      files = awsdao.saveUploadedFiles(req, applicationPath, items,UPLOAD_DIR);
    } catch (Exception e) {
      results.put("fileSaveError", e.getMessage());
    }
    for (DiskFileItem x : items) {

      String variable = x.getFieldName();
      String value = x.getString();
      switch (variable) {
        case "inputreceiptReceipt_ID":
          _Receipt_ID = value;
          break;
        case "inputreceiptTransaction_ID":
          _Transaction_ID = value;
          break;
        case "inputreceiptImage_Link":
          _Image_Link = value;
          break;
        case "inputreceiptName":
          _Name = value;
          break;
        case "inputreceiptDescription":
          _Description = value;
          break;
      }
    }
    results.put("Receipt_ID",_Receipt_ID);
    results.put("Transaction_ID",_Transaction_ID);
    results.put("Image_Link",_Image_Link);
    results.put("Name",_Name);
    results.put("Description",_Description);
    try {
      receipt.setTransaction_ID(_Transaction_ID);
    } catch(Exception e) {results.put("receiptTransaction_IDerror", e.getMessage());
      errors++;
    }

    try {
      receipt.setName(_Name);
    } catch(Exception e) {results.put("receiptNameerror", e.getMessage());
      errors++;
    }
    try {
      receipt.setDescription(_Description);
    } catch(Exception e) {results.put("receiptDescriptionerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){

      try{
        Map<File, String> uploadedUrls;
        uploadedUrls = awsdao.uploadToS3(files, bucketName, user.getUser_ID());
        receipt.setImage_Link(uploadedUrls.get(files.get(0)));
        result=receiptDAO.add(receipt);

      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Receipt Added");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Receipts");
        return;
      } else {
        results.put("dbStatus","Receipt Not Added");

      }
    }
    List<Transaction> allTransactions = null;
    try {
      allTransactions = transactionDAO.getDistinctTransactionForDropdown(user.getUser_ID());
    }catch (Exception e){
        allTransactions= new ArrayList<>();
      }
      req.setAttribute("Transactions", allTransactions);
      req.setAttribute("results", results);
      req.setAttribute("pageTitle", "Add Receipt");
      req.getRequestDispatcher("WEB-INF/Budget_App/AddReceipt.jsp").forward(req, resp);

  }
}


