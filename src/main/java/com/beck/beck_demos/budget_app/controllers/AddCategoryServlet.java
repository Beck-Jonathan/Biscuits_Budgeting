package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.SubCategory;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/******************
 Refactored Servlet For adding to The Subcategory table
 Matches structure of AddBudget_Line_Item
 Updated By Jonathan Beck 3/3/2026
 ***************/

@WebServlet("/addTransactionCategory")
public class AddCategoryServlet extends HttpServlet {
  private iCategoryDAO categoryDAO;

  @Override
  public void init() throws ServletException {
    categoryDAO = new CategoryDAO();
  }

  public void init(iCategoryDAO categoryDAO) {
    this.categoryDAO = categoryDAO;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String response = "0";
    User user = (User) session.getAttribute("User_B");

    // Privilege restriction
    if (user == null || !user.getRoles().contains("User")) {
      session.invalidate();
      response = "-1";
      sendResponse(resp, response);
      return;
    }

    // Extraction
    String _Category_Name = req.getParameter("inputcategoryCategory_Name");
    if (_Category_Name != null) _Category_Name = _Category_Name.trim();

    String _color_id = req.getParameter("inputcategoryColor_id");
    if (_color_id != null) _color_id = _color_id.trim();

    String _parent_id = req.getParameter("inputcategoryParent_id");
    if (_parent_id != null) _parent_id = _parent_id.trim();

    String _strategy_id = req.getParameter("inputsub_categoryprojection_strategy_ID");
    if (_strategy_id != null) _strategy_id = _strategy_id.trim();

    SubCategory category = new SubCategory();

    // Validation and Model Mapping
    try {
      category.setCategory_Name(_Category_Name);
    } catch (Exception e) {
      sendResponse(resp, "-2");
      return;
    }
    try {
      category.setcolor_id(_color_id);
    } catch (Exception e) {
      sendResponse(resp, "-3");
      return;
    }
    try {
      category.setParentCategoryId(_parent_id);
    } catch (Exception e) {
      sendResponse(resp, "-4");
      return;
    }
    try {
      category.setprojection_strategy_ID(_strategy_id);
    } catch (Exception e) {
      sendResponse(resp, "-5");
      return;
    }

    // Set User from Session
    category.setUser_ID(user.getUser_ID());

    String result = "";
    try {
      result = categoryDAO.add(category);
    } catch (Exception ex) {
      sendResponse(resp, "-10");
      return;
    }

    if (result.length() == 36) {
      // Return the new UUID so the JSP can assign it to the new card

      sendResponse(resp, result);
    } else {
      sendResponse(resp, "0");
    }
  }

  private void sendResponse(HttpServletResponse response, String Result) {
    try {
      response.setStatus(200);
      response.setContentType("text/plain");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      out.print(Result);
      out.flush();
    } catch (IOException e) {
      System.err.println("Error writing response: " + e.getMessage());
    }
  }
}