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
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

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

    User user = (User) session.getAttribute("User_B");
    if (user == null || !user.getRoles().contains("User")) {
      resp.sendRedirect("budget_home");
      return;
    }
    session.setAttribute("currentPage", req.getRequestURL());
    req.setAttribute("pageTitle", "Budget Home");
    req.getRequestDispatcher("WEB-INF/Budget_App/add_transaction.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_B");
    if (user == null || !user.getRoles().contains("User")) {
      resp.sendRedirect("budget_home");
      return;
    }

    Map<String, String> results = new HashMap<>();
    Part filePart = req.getPart("upload_transactions");

    if (filePart == null || filePart.getSize() == 0) {
      results.put("FileEmptyError", "Please select a file.");
      req.setAttribute("results", results);
      req.getRequestDispatcher("WEB-INF/Budget_App/add_transaction.jsp").forward(req, resp);
      return;
    }

    // 1. Use the System Temp Directory instead of getRealPath
    // This works on Windows, Linux, and inside .war deployments
    String tempDir = System.getProperty("java.io.tmpdir");
    String fileName = java.nio.file.Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
    java.nio.file.Path targetFilePath = java.nio.file.Paths.get(tempDir).resolve(fileName);

    // 2. Perform the copy to the server's temp storage
    try (InputStream input = filePart.getInputStream()) {
      java.nio.file.Files.copy(input, targetFilePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ex) {
      results.put("dbStatus", "Server storage error: " + ex.getMessage());
      req.setAttribute("results", results);
      req.getRequestDispatcher("WEB-INF/Budget_App/add_transaction.jsp").forward(req, resp);
      return;
    }

    // 3. Process the file
    File uploadedFile = targetFilePath.toFile();
    try {
      List<Transaction> transactions = transactionDAO.getTransactionFromFile(uploadedFile, "Altra");

      int totalTrans = transactions.size();
      int newTrans = transactionDAO.addBatch(transactions, user.getUser_ID());
      int oldTrans = totalTrans - newTrans;

      results.put("AddedCount", String.format("Uploaded %d transactions. %d new, %d duplicates.",
          totalTrans, newTrans, oldTrans));

    } catch (SQLException e) {
      results.put("dbError", "Database Error: " + e.getMessage());
    } finally {
      // 4. CLEANUP: This is critical for server health
      // We delete the file immediately after the DAO is done with it.
      if (uploadedFile.exists()) {
        boolean deleted = uploadedFile.delete();
        if (!deleted) {
          // If the OS won't let us delete it now, mark it for deletion when the server restarts
          uploadedFile.deleteOnExit();
        }
      }
    }

    session.setAttribute("results", results);
    resp.sendRedirect("budget_home");
  }
}