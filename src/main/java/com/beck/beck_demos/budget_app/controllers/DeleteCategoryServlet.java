package com.beck.beck_demos.budget_app.controllers;

/******************
 Create the Servlet For Deleteing from the Category table
 Created By Jonathan Beck 7/31/2024
 ***************/

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
@WebServlet("/deleteBudgetCategory")
public class DeleteCategoryServlet extends HttpServlet {
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

    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
    }
    int errors=0;

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete Category");
    String CategoryID = req.getParameter("categoryid");
    if (CategoryID==null){
      errors ++;
    }
    int result = -1;

    if (errors==0&&!CategoryID.equals("Uncategorized")) {
      try {
        result = categoryDAO.deleteCategory(CategoryID, user.getUser_ID());
      } catch (Exception ex) {
        results.put("dbStatus", ex.getMessage());
        result=0;
      }
    }else {
      results.put("dbStatus", "unable to delete \"uncategorized\"");
    }
    if (result ==0){
      results.put("dbStatus", "Unable to delete "+CategoryID+".");
    }


    req.setAttribute("result", result);
    List<Category> categories = null;
    categories = categoryDAO.getCategoryByUser(user.getUser_ID());
    req.setAttribute("results",results);
    req.setAttribute("Categories", categories);
    req.setAttribute("pageTitle", "All Category");
    req.getRequestDispatcher("WEB-INF/Budget_App/all_categories.jsp").forward(req, resp);
  }
}

