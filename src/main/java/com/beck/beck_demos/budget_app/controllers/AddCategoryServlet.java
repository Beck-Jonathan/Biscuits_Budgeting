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
import java.util.HashMap;
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
      resp.sendRedirect("budget_home");
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
      resp.sendRedirect("budget_home");
      return;
    }

    String _Category_Name = req.getParameter("inputcategoryCategory_Name");
    if (_Category_Name!=null) {
      _Category_Name=_Category_Name.trim();
    }
    String _color_id = req.getParameter("inputcategoryColor_id");
    if (_color_id!=null) {
      _color_id=_color_id.trim();
    }
    Map<String, String> results = new HashMap<>();
    results.put("Category_Name",_Category_Name);
    results.put("color_id",_color_id);
    SubCategory category = new SubCategory();
    int errors =0;
    try {
      category.setCategory_Name(_Category_Name);
      category.setUser_ID(user.getUser_ID());
    } catch(Exception e) {results.put("categoryCategory_Nameerror", e.getMessage());
      errors++;
    }
    try {
      category.setcolor_id(_color_id);
    } catch(Exception e) {results.put("categoryColor_IDerror", e.getMessage());
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
        PrintWriter writer = resp.getWriter();
        writer.write("1");
        return;
//        results.put("dbStatus","Category Added");
//        resp.sendRedirect("all-Categories");
//        req.setAttribute("results", results);
//        return;
      } else {
        results.put("dbStatus","Category Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Add Category");
    req.getRequestDispatcher("WEB-INF/Budget_App/add_category.jsp").forward(req, resp);

  }
}

