package com.beck.beck_demos.budget_app.controllers;

/******************
 Create the Servlet For Deleteing from the Receipt table
 Created By Jonathan Beck 10/8/2025
 ***************/

import com.beck.beck_demos.budget_app.data.AWSDAO;
import com.beck.beck_demos.budget_app.data.ReceiptDAO;
import com.beck.beck_demos.budget_app.iData.iAWSDAO;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Servlet implementation class AddReceiptServlet
 *
 * <p>This servlet handles the deletion of  {@link Receipt} entries in the application.
 * It is mapped to the URL pattern <code>/deleteReceipt</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: handles the request for deleting aReceipt via {@link ReceiptDAO}. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p> * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
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
@WebServlet("/deletereceipt")
public class DeleteReceiptServlet extends HttpServlet {
  private iReceiptDAO receiptDAO;
  private iAWSDAO awsdao;
  ;
  @Override
  public void init()  {
    receiptDAO = new ReceiptDAO();
   awsdao = new AWSDAO();
  }
  public void init(iReceiptDAO receiptDAO, iAWSDAO awsdao){
    this.receiptDAO = receiptDAO;
    this.awsdao = awsdao;
  }
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      session.invalidate();
      resp.setStatus(302);
      resp.setContentType("text/plain");
      PrintWriter writer=resp.getWriter();
      writer.write("You are not logged in");
      writer.flush();
      writer.close();
      return;

    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete ReceiptID");
    String ReceiptID =req.getParameter("receiptid");

    Integer result = 0;

    try{
      Receipt receipt = receiptDAO.getReceiptByPrimaryKey(new Receipt(ReceiptID));
      result = receiptDAO.deleteReceipt(receipt.getReceipt_ID());
      result += awsdao.deleteReceipt(receipt.getImage_Link());
    }
    catch(Exception ex){

      resp.setStatus(302);
      resp.setContentType("text/plain");
      PrintWriter writer=resp.getWriter();
      writer.write(ex.getMessage());
      writer.flush();
      writer.close();
      return;
    }



    resp.setStatus(200);
    resp.setContentType("text/plain");
    PrintWriter writer=resp.getWriter();
    writer.write(result.toString());
    writer.flush();
    writer.close();
    return;

  }
}

