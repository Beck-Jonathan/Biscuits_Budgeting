package com.beck.beck_demos.budget_app.controllers;


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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet Viuw/Edit from the budget_line_item table
 Created By Jonathan Beck 3/3/2026
 ***************/
/**
 * Servlet implementation class Editbudget_line_itemServlet
 *
 * <p>This servlet handles the editing of {@link Budget_Line_Item} entries in the application.
 * It is mapped to the URL pattern <code>/editbudget_line_item</code>.</p>
 *
 * <p><strong>HTTP GET</strong>: Renders the table for viewing a single budget_line_items. Allows editing of appropriate fields. Access is restricted
 * to users with the "User" role. If unauthorized, the user is redirected to the login page.</p>
 * <p><strong>HTTP POST</strong>: Processes form submissions for editing a budget_line_item. Validates input,
 * attempts update of the the database via {@link Budget_Line_ItemDAO}, and forwards back to the form view with
 * success or error messages as necessary. Successful updates redirect to the list of all budget_line_item.</p
 > * <p>Access to this servlet requires that the user session contain a {@link User} object that is logged in
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

@WebServlet("/editbudget_line_item")
public class EditBudget_Line_ItemServlet extends HttpServlet{
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

    HttpSession session = req.getSession();
    Integer response = 0;
    User user = (User) session.getAttribute("User_B");
    if (user == null || !user.getRoles().contains("User")) {
      session.invalidate();
      response = -1;
      sendResponse(resp, response);
      return;
    }



//to get the old budget_line_item
    String PrimaryKey = req.getParameter("inputbudget_line_itembudget_line_item_id");
    Budget_Line_Item _oldbudget_line_item= new Budget_Line_Item();
    try {
      _oldbudget_line_item.setBudget_Line_Item_id(PrimaryKey);
    } catch (Exception e) {
      response = -2;
      sendResponse(resp, response);
      return;
    }



    String _color_id = req.getParameter("inputbudget_line_itemcolor_id");
    if (_color_id!=null){
      _color_id=_color_id.trim();
    }
    String _name = req.getParameter("inputbudget_line_itemname");
    if (_name!=null){
      _name=_name.trim();
    }
    String _details = req.getParameter("inputbudget_line_itemdetails");
    if (_details!=null){
      _details=_details.trim();
    }
    String _line_item_date = req.getParameter("inputbudget_line_itemline_item_date");
    if (_line_item_date!=null){
      _line_item_date=_line_item_date.trim();
    }
    String _amount = req.getParameter("inputbudget_line_itemamount");
    if (_amount!=null){
      _amount=_amount.trim();
    }
    String _budget_line_type_id = req.getParameter("inputbudget_line_itembudget_line_type_id");
    if (_budget_line_type_id!=null){
      _budget_line_type_id=_budget_line_type_id.trim();
    }
    String _budget_line_status_id = req.getParameter("inputbudget_line_itembudget_line_status_id");
    if (_budget_line_status_id!=null){
      _budget_line_status_id=_budget_line_status_id.trim();
    }
    String _transaction_id = req.getParameter("inputbudget_line_itemtransaction_id");
    if (_transaction_id!=null){
      _transaction_id=_transaction_id.trim();
    }
    String _created_at = req.getParameter("inputbudget_line_itemcreated_at");
    if (_created_at!=null){
      _created_at=_created_at.trim();
    }
    String _updated_at = req.getParameter("inputbudget_line_itemupdated_at");
    if (_updated_at!=null){
      _updated_at=_updated_at.trim();
    }

    Budget_Line_Item _newbudget_line_item = new Budget_Line_Item();
    int errors =0;

    try {
      _newbudget_line_item.setcolor_id(_color_id);
    } catch(Exception e) {
      response = -3;
      sendResponse(resp, response);
      return;
    }
    try {
      _newbudget_line_item.setname(_name);
    } catch(Exception e) {
      response = -4;
      sendResponse(resp, response);
      return;
    }
    try {
      _newbudget_line_item.setdetails(_details);
    } catch(Exception e) {
      response = -5;
      sendResponse(resp, response);
      return;
    }
    try {
      _newbudget_line_item.setline_item_date(LocalDate.parse(_line_item_date));
    } catch(Exception e) {
      response = -6;
      sendResponse(resp, response);
      return;
    }
    try {
      _newbudget_line_item.setamount(Double.valueOf(_amount));
    } catch(Exception e) {
      response = -7;
      sendResponse(resp, response);
      return;
    }
    try {
      _newbudget_line_item.setbudget_line_type_id(_budget_line_type_id);
    } catch(Exception e) {
      response = -8;
      sendResponse(resp, response);
      return;
    }
    try {
      _newbudget_line_item.setbudget_line_status_id(_budget_line_status_id);
    } catch(Exception e) {
      response = -9;
      sendResponse(resp, response);
      return;
    }


//to update the database
    int result=0;

      try{
        result=budget_line_itemDAO.update(_oldbudget_line_item,_newbudget_line_item);
      }catch(Exception ex){
        response = -10;
        sendResponse(resp, response);
        return;
      }
    response = result;
    sendResponse(resp, response);
    return;


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


