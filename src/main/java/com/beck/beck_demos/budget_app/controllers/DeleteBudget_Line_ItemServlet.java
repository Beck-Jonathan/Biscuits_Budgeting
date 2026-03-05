package com.beck.beck_demos.budget_app.controllers; /******************
 Create the Servlet For Deleteing from the budget_line_item table
 Created By Jonathan Beck 3/3/2026
 ***************/

import com.beck.beck_demos.budget_app.data.Budget_Line_ItemDAO;
import com.beck.beck_demos.budget_app.models.Budget_Line_Item;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iBudget_Line_ItemDAO;
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
 * Servlet implementation class Addbudget_line_itemServlet
 *
 * <p>This servlet handles the deletion of  {@link Budget_Line_Item} entries in the application.
 * It is mapped to the URL pattern <code>/deletebudget_line_item</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: handles the request for deleting a budget_line_item via {@link Budget_Line_ItemDAO}. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p> * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
 * appropriate roles set (specifically the "User" role).</p>
 *
 * <p>Created by Jonathan Beck on 3/3/2026</p>
 *
 * @author Jonathan Beck
 * @version 1.0
 * @see com.beck.beck_demos.budget_app.models.Budget_Line_Item
 * @see com.beck.beck_demos.budget_app.data.Budget_Line_ItemDAO
 * @see jakarta.servlet.http.HttpServlet
 */
@WebServlet("/deleteBudget_line_item")
public class DeleteBudget_Line_ItemServlet extends HttpServlet {
  private iBudget_Line_ItemDAO budget_line_itemDAO;

  @Override
  public void init() {
    budget_line_itemDAO = new Budget_Line_ItemDAO();

  }
  public void init(iBudget_Line_ItemDAO budget_line_itemDAO){
    this.budget_line_itemDAO = budget_line_itemDAO;

  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Integer response = 0;

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      session.invalidate();
      response = -1;
      sendResponse(resp, response);
      return;
    }

    String budget_line_itemID = req.getParameter("budget_line_itemid");
    Budget_Line_Item lineItem = new Budget_Line_Item();
    try {
      lineItem.setBudget_Line_Item_id(budget_line_itemID);
    }catch (Exception e){
      response=-2;
      resp.setStatus(200);
      sendResponse(resp, response);
      return;
    }


      try{
        response = budget_line_itemDAO.deleteBudget_Line_Item(budget_line_itemID);
        resp.setStatus(200);
        sendResponse(resp, response);
        return;

      }
      catch(Exception ex){
        response=-3;
        resp.setStatus(200);
        sendResponse(resp, response);
        return;
      }

  }

  private void sendResponse(HttpServletResponse response, Integer Result) {
    try {
      // 1. Set the status and headers before getting the writer
      response.setStatus(200);
      response.setContentType("text/plain");
      response.setCharacterEncoding("UTF-8");

      // 2. Use try-with-resources if you want to be safe,
      // though Tomcat generally manages the response writer for you.
      PrintWriter out = response.getWriter();
      out.print(Result.toString());
      out.flush();

    } catch (IOException e) {
      // Log the error if the connection was closed before we could write
      System.err.println("Error writing response: " + e.getMessage());
    }
  }
}

