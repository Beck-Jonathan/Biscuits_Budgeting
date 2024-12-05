package com.beck.beck_demos.budget_app.controllers; /******************
 Create the Servlet  For Viewing all of the  Transaction table
 Created By Jonathan Beck 7/22/2024
 ***************/

import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
import com.beck.beck_demos.budget_app.models.Category;
import com.beck.beck_demos.budget_app.models.Transaction;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/Export")
public class ExportServlet extends HttpServlet {

  private iTransactionDAO transactionDAO;
  private static final String UPLOAD_DIR = "uploads";
  private static final long serialVersionUID = 1L;
  private ServletFileUpload uploader = null;

  @Override
  public void init(ServletConfig config) throws ServletException {
    transactionDAO =  new TransactionDAO();
  }

  @Override
  public void init() throws ServletException{
    DiskFileItemFactory fileFactory = new DiskFileItemFactory();
    File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
    fileFactory.setRepository(filesDir);
    this.uploader = new ServletFileUpload(fileFactory);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String applicationPath = req.getServletContext().getRealPath("");
    String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null){
     resp.sendError(HttpServletResponse.SC_FORBIDDEN);
     return;
     }
    session.setAttribute("currentPage",req.getRequestURL());
    List<Transaction> transactions = null;

    try {
      transactions = transactionDAO.getTransactionForExportByUser(user.getUser_ID());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    PrintWriter writer = new PrintWriter(uploadFilePath+"/the-file-name.txt", StandardCharsets.UTF_8);
    writer.print("T_ID\tU_ID\tCategory\tAccount\tPost_Date\tCheck#\tDescription\tAmount\tType\tStatus");
    for (Transaction t : transactions){
      writer.print(t.getTransaction_ID()+"\t");
      writer.print(t.getUser_ID()+"\t");
      writer.print(t.getCategory_ID()+"\t");
      writer.print(t.getAccount_Num()+"\t");
      writer.print(t.getPost_Date()+"\t");
      writer.print(t.getCheck_No()+"\t");
      writer.print(t.getDescription()+"\t");
      writer.print(t.getAmount()+"\t");
      writer.print(t.getType()+"\t");
      writer.print(t.getStatus()+"\t");
      writer.print("\n");
    }


    writer.close();


    //https://www.geeksforgeeks.org/jsp-file-downloading/
    String filename = "the-file-name.txt";
    //String filepath = "c:\\Table_Gen\\";
    // Set response to download file
    resp.setContentType("APPLICATION/OCTET-STREAM");
    resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

    // Input stream for file reading
    java.io.FileInputStream fileInputStream = new java.io.FileInputStream(uploadFilePath +File.separator+ filename);

    // Read file content and stream it to the response output stream

    int i;
    while ((i=fileInputStream.read()) != -1) {
      resp.getOutputStream().write(i);
    }

    // Close the FileInputStream to release resources
    fileInputStream.close();
    File myObj = new File(uploadFilePath+File.separator+filename);
    myObj.delete();




    req.setAttribute("Transactions", transactions);
    req.setAttribute("pageTitle", "All Transactions");
    //resp.sendRedirect("budget_bome");

  }
}
