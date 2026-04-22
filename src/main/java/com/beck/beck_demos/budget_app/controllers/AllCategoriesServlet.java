package com.beck.beck_demos.budget_app.controllers;

/******************
 Create the Servlet  For Viewing all of the  Category table
 Created By Jonathan Beck 7/31/2024
 ***************/

import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.ParentCategory_VM;
import com.beck.beck_demos.budget_app.models.SubCategory_VM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/all-Categories")
public class AllCategoriesServlet extends HttpServlet {
  private iCategoryDAO categoryDAO;

  @Override
  public void init(){
    categoryDAO = new CategoryDAO();
  }
  public void init (iCategoryDAO categoryDAO){
    this.categoryDAO = categoryDAO;
  }
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (categoryDAO==null){
      categoryDAO = new CategoryDAO();
    }
//To restrict this page based on privilege level
  int PRIVILEGE_NEEDED = 0;
  HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("budget_home");
      return;
    }
    java.time.LocalDate lastMonth = java.time.LocalDate.now().minusMonths(1);
    int month = lastMonth.getMonthValue();
    int year = lastMonth.getYear();
    String _year = req.getParameter("year");
    if (_year != null) {
      try {
        year = Integer.parseInt(_year);
      } catch (Exception e) {
        year = lastMonth.getYear();
      }
    }
    String _month = req.getParameter("month");
    if (_month != null) {
      try {
        month = Integer.parseInt(_month);
      } catch (Exception e) {
        month = lastMonth.getMonthValue();
      }
    }
    boolean refresh = false;
    String _refresh = req.getParameter("refresh");
    if (_refresh != null) {
      try {
        refresh = Boolean.parseBoolean(_refresh);
      } catch (Exception e) {
        refresh = false;
      }
    }

  session.setAttribute("currentPage",req.getRequestURL());
    List<SubCategory_VM> categories = null;
    List<ParentCategory_VM> allparent_categorys = null;
  try {
    categories = categoryDAO.getsubCategoryByUser(user.getUser_ID(), month, year);
    allparent_categorys = categoryDAO.getParentCategoryByUser(user.getUser_ID(), month, year);
  } catch (Exception e) {
     categories = null;
     allparent_categorys = null;
     resp.sendRedirect("budget_home");
    return;
  }


  req.setAttribute("Categories", categories);
    req.setAttribute("ParentCategories", allparent_categorys);
  req.setAttribute("pageTitle", "All Categories");
    if (!refresh) {
      req.getRequestDispatcher("WEB-INF/Budget_App/all_categories.jsp").forward(req, resp);
    }
    if (refresh) {
      req.getRequestDispatcher("WEB-INF/Budget_App/all_categories_Partial.jsp").forward(req, resp);
    }

}
}
