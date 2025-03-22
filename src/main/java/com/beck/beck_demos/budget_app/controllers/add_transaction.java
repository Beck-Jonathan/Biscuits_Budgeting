package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.Transaction;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/add_transaction")
@MultipartConfig(

    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class add_transaction extends HttpServlet {
  private static final String UPLOAD_DIR = "uploads";
  private iTransactionDAO transactionDAO;

  @Override
  public void init() throws ServletException {
    transactionDAO = new TransactionDAO();
  }
  public void init(iTransactionDAO transactionDAO) {
    this.transactionDAO = transactionDAO;
  }

  @Override


  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String Role = "User";

    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
    }
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Budget Home");
    req.getRequestDispatcher("WEB-INF/Budget_App/add_transaction.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
    }
    String applicationPath = req.getServletContext().getRealPath("");
    String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
    File fileSaveDir = new File(uploadFilePath);
    if (!fileSaveDir.exists()) {
      fileSaveDir.mkdirs();
    }
    Part filePart = req.getPart("upload_transactions");
    Map<String, String> results = new HashMap<>();
    String fileName = filePart.getSubmittedFileName();
    File checkFile = new File(uploadFilePath + File.separator + fileName);
    if (checkFile.exists()) {
      checkFile.delete();
    }
    try {
      for (Part part : req.getParts()) {
        part.write(uploadFilePath + File.separator + fileName);

      }
    } catch (Exception ex){
      results.put("dbStatus",ex.getMessage());
      req.setAttribute("results", results);
      req.setAttribute("pageTitle", "Upload a file ");
      req.getRequestDispatcher("WEB-INF/Budget_App/add_transaction.jsp").forward(req, resp);
      return;
    }
    File uploadedFile = new File(uploadFilePath + File.separator + fileName);
    List<Transaction> transactions = null;

    try {
      transactions = transactionDAO.getTransactionFromFile(uploadedFile,"Altra");
    } catch (SQLException e) {
      results.put("dbError",e.getMessage());
      session.setAttribute("currentPage",req.getRequestURL());
      req.setAttribute("pageTitle", "Budget Home");
      req.getRequestDispatcher("WEB-INF/Budget_App/add_transaction.jsp").forward(req, resp);

      return;
    }
    int NewTrans = 0;
    int oldTrans = 0;
    int totalTrans = transactions.size();


    try {
      NewTrans = transactionDAO.addBatch(transactions, user.getUser_ID());
    } catch (Exception e) {
      results.put("dbStatus",e.getMessage());
    }

    oldTrans=totalTrans-NewTrans;
    int x = 0;

    uploadedFile.delete();
    results.put("AddedCount","You uploaded "+totalTrans+" transactions. "+NewTrans+" of them were new. "+ oldTrans+" of them were old, and not added to the database.");
    session.setAttribute("results",results);
    resp.sendRedirect("budget_home");

    //session.setAttribute("currentPage",req.getRequestURL());
    //req.setAttribute("pageTitle", "Budget Home");
    //req.getRequestDispatcher("WEB-INF/Budget_App/add_transaction.jsp").forward(req, resp);

  }
}
