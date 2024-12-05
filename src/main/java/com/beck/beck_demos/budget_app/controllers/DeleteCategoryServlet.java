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
  public static iCategoryDAO categoryDAO;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (categoryDAO==null){
      categoryDAO = new CategoryDAO();
    }
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete Category");
    String CategoryID = req.getParameter("categoryid");

    int result = 0;

    if (!CategoryID.equals("Uncategorized")) {
      try {
        result = categoryDAO.deleteCategory(CategoryID, user.getUser_ID());
      } catch (Exception ex) {
        results.put("dbStatus", ex.getMessage());
      }
    }else {
      results.put("dbStatus", "unable to delete \"uncategorized\"");
    }


    List<Category> categories = null;
    categories = categoryDAO.getCategoryByUser(user.getUser_ID());
    req.setAttribute("results",results);
    req.setAttribute("Categories", categories);
    req.setAttribute("pageTitle", "All Category");
    req.getRequestDispatcher("WEB-INF/Budget_App/all_categories.jsp").forward(req, resp);
  }
}

