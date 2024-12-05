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

  @Override


  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String Role = "User";

    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
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

    String applicationPath = req.getServletContext().getRealPath("");
    String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
    File fileSaveDir = new File(uploadFilePath);
    if (!fileSaveDir.exists()) {
      fileSaveDir.mkdirs();
    }
    Part filePart = req.getPart("upload_transactions");
    Map<String, String> results = new HashMap<>();
    String fileName = filePart.getSubmittedFileName();
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
      throw new RuntimeException(e);
    }
    Integer NewTrans = 0;
    Integer oldTrans = 0;
    int totalTrans = transactions.size();
    ArrayList<Boolean> transExist = new ArrayList<>(totalTrans);
    for (int i=0;i<totalTrans;i++){
      transExist.add(false);
    }
    int index=0;
    for (Transaction t : transactions) {
      t.setUser_ID(user.getUser_ID());
    }
    try {
      transactionDAO.addBatch(transactions,transExist);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    uploadedFile.delete();
    resp.sendRedirect("budget_bome");

    //session.setAttribute("currentPage",req.getRequestURL());
    //req.setAttribute("pageTitle", "Budget Home");
    //req.getRequestDispatcher("WEB-INF/Budget_App/add_transaction.jsp").forward(req, resp);

  }
}
