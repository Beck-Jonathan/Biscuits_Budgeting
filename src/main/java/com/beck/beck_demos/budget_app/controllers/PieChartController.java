package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.data.UserDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.iData.iUserDAO;
import com.beck.beck_demos.budget_app.models.Category;
import com.beck.beck_demos.budget_app.models.Category_VM;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PieChart")
public class PieChartController extends HttpServlet {
  public static iCategoryDAO categoryDAO;
  private iUserDAO userDAO;

  @Override
  public void init() throws ServletException {
    userDAO = new UserDAO();
    categoryDAO = new CategoryDAO();
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (categoryDAO==null){
      categoryDAO = new CategoryDAO();
    }
    HttpSession session = req.getSession();

    User user = (User)session.getAttribute("User_B");

    List<Category> allCategories = categoryDAO.getCategoryByUser(user.getUser_ID());

    int year_range=0;
    // new approach
    try {
      year_range = userDAO.yearRange(user.getUser_ID());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }



    session.setAttribute("Categories",allCategories);
    session.setAttribute("yearRange",year_range);

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Pie Chart");

    req.getRequestDispatcher("WEB-INF/Budget_App/PieChart.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    if (categoryDAO==null){
      categoryDAO = new CategoryDAO();
    }
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Pie Chart");
    req.getRequestDispatcher("WEB-INF/Budget_App/PieChart.jsp").forward(req, resp);

  }
}
