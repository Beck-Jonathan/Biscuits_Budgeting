package com.beck.beck_demos.budget_app.controllers;


import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.Category;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  Category table
 Created By Jonathan Beck 7/31/2024
 ***************/

@WebServlet("/editCategory")
public class EditCategoryServlet extends HttpServlet{

  private iCategoryDAO categoryDAO;
  @Override
  public void init(){
    this.categoryDAO = new CategoryDAO();
  }
  public void init(iCategoryDAO categoryDAO){
    this.categoryDAO = categoryDAO;
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
    }

    String mode = req.getParameter("mode");
    String primaryKey = "";
    try{
      primaryKey = req.getParameter("categoryid");
    }catch (Exception e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    Category category= new Category();
    try{
      category.setCategory_ID(primaryKey);
      category.setUser_ID(user.getUser_ID());
    } catch (Exception e){
      req.setAttribute("dbStatus",e.getMessage());
    }

    session.setAttribute("category",category);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit Category");

    req.getRequestDispatcher("WEB-INF/Budget_App/edit_category.jsp").forward(req, resp);

  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
    }

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs

//to get the old Category

    Category _oldCategory= (Category)session.getAttribute("category");
    int errors =0;
//to get the new event's info
    String _Category_ID = req.getParameter("inputcategoryCategory_ID");
    try {
      _Category_ID = _Category_ID.trim();

    } catch (Exception e){
      errors++;
      results.put("categoryCategory_IDerror", e.getMessage());
    }

    results.put("Category_ID",_Category_ID);

    Category _newCategory = new Category();

    try {
      _newCategory.setCategory_ID(_Category_ID);
    } catch(Exception e) {
      results.put("categoryCategory_IDerror", e.getMessage());
      errors++;
    }

//to update the database
    int result=0;
    if (errors==0){
      try{
        result=categoryDAO.update(_oldCategory,_newCategory);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Category updated");
        req.setAttribute("results", results);
        resp.sendRedirect("all-Categories");
        return;
      } else {
        results.put("dbStatus","Category Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Category ");
    req.getRequestDispatcher("WEB-INF/Budget_App/edit_category.jsp").forward(req, resp);
  }
}

