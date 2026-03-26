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
 Create the Servlet  For adding to The  Category table
 Created By Jonathan Beck 7/31/2024
 ***************/

@WebServlet("/editCategory")
public class EditSubCategoryServlet extends HttpServlet{

  private iCategoryDAO categoryDAO;
  @Override
  public void init(){
    this.categoryDAO = new CategoryDAO();
  }
  public void init(iCategoryDAO categoryDAO){
    this.categoryDAO = categoryDAO;
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    Integer response = 0;
    if (user==null||!user.getRoles().contains("User")){
      session.invalidate();
      response = -1;
      sendResponse(resp,response);
      return;
    }


    SubCategory _oldCategory = new SubCategory();
    SubCategory _newCategory = new SubCategory();
    String _Category_ID = req.getParameter("category_ID");
    String _Category_Name = req.getParameter("inputcategoryCategory_Name");
    String _color_id = req.getParameter("inputcategoryColor_id");
    String _parent_category_id = req.getParameter("inputsub_categoryparent_category_id");
    String _projection_strategy_ID = req.getParameter("inputsub_categoryprojection_strategy_ID");

    try {
      _oldCategory.setCategory_ID(_Category_ID);
    }
    catch(Exception e){
      response = -2;
      sendResponse(resp,response);
      return;
    }

    try {
      _Category_Name = _Category_Name.trim();
      _newCategory.setCategory_Name(_Category_Name);
    } catch(Exception e) {
      response = -3;
      sendResponse(resp,response);
      return;
    }
    try {
      _newCategory.setParentCategoryId(_parent_category_id);
    } catch(Exception e) {response = -4;
      sendResponse(resp,response);
      return;
    }
    try {
      _newCategory.setprojection_strategy_ID(_projection_strategy_ID);
    } catch (Exception e) {
      response = -5;
      sendResponse(resp, response);
      return;
    }
    try {
      _newCategory.setcolor_id(_color_id);
    } catch(Exception e) {
      response = -6;
      sendResponse(resp,response);
      return;
    }

//to update the database

      try{
        response=categoryDAO.update(_oldCategory,_newCategory);

          sendResponse(resp,response);
          return;

      }catch(Exception ex){
        response = -7;
        sendResponse(resp,response);
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

