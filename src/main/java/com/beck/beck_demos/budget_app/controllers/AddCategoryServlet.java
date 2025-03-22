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
 Created By Jonathan Beck 7/30/2024
 ***************/

@WebServlet("/addTransactionCategory")
public class AddCategoryServlet extends HttpServlet{
  private  iCategoryDAO categoryDAO;

  @Override
  public void init() throws ServletException {
    categoryDAO = new CategoryDAO();
  }
  public void init (iCategoryDAO categoryDAO) {
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

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Category");
    req.getRequestDispatcher("WEB-INF/Budget_App/add_category.jsp").forward(req, resp);
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

    String _Category_ID = req.getParameter("inputcategoryCategory_ID");
    if (_Category_ID!=null) {
      _Category_ID=_Category_ID.trim();
    }
    Map<String, String> results = new HashMap<>();
    results.put("Category_ID",_Category_ID);
    Category category = new Category();
    int errors =0;
    try {
      category.setCategory_ID(_Category_ID);
      category.setUser_ID(user.getUser_ID());
    } catch(Exception e) {results.put("categoryCategory_IDerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=categoryDAO.add(category);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Category Added");
        resp.sendRedirect("all-Categories");
        req.setAttribute("results", results);
        return;
      } else {
        results.put("dbStatus","Category Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Category ");
    req.getRequestDispatcher("WEB-INF/Budget_App/add_category.jsp").forward(req, resp);

  }
}

