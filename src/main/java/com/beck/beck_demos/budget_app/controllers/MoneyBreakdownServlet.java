package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.data.TransactionDAO;
import com.beck.beck_demos.budget_app.data.UserDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.iData.iTransactionDAO;
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

@WebServlet("/MoneyBreakdown")
public class MoneyBreakdownServlet extends HttpServlet {
  private  iCategoryDAO categoryDAO;
  private iTransactionDAO transactionDAO;
  private iUserDAO userDAO;



  @Override
  public void init() throws ServletException {
    categoryDAO = new CategoryDAO();
    transactionDAO = new TransactionDAO();
    userDAO = new UserDAO();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
    int loop=0;
    List<List<Category_VM>> years = new ArrayList<>(year_range+1);
    for(int i=0;i<year_range+1;i++){
      years.add(i, new ArrayList<>(allCategories.size()+2));
    }

    //add each year and category combo
    for (int i=0;i<=year_range;i++) {
      for (int k = 0; k < allCategories.size(); k++) {
        Category_VM c = new Category_VM(allCategories.get(k).getCategory_ID(), 0);
        years.get(i).add(k, c);
      }
    }

    //add each year total, pos and neg
    for (int i=0;i<=year_range;i++) {

        Category_VM c = new Category_VM("Total In", 0,"pos");
        years.get(i).add( c);
      Category_VM d = new Category_VM("Total Out", 0,"neg");
      years.get(i).add( d);

    }

    //add each category total, ignoring year
    years.add( new ArrayList<>(allCategories.size()+2));
      for (int k = 0; k < allCategories.size(); k++) {
        Category_VM c = new Category_VM(allCategories.get(k).getCategory_ID(), 0);
        years.get(years.size()-1).add(k, c);
      }
      //get each grand total
    Category_VM total_in = new Category_VM("", 0,"pos");
    years.get(years.size()-1).add(total_in);
    Category_VM total_out = new Category_VM("", 0,"neg");
    years.get(years.size()-1).add(total_out);

int x=6;
    //to add grand totals, pos and neg
    //not implemented



    try {
      int size = transactionDAO.getAnalysis(years, user.getUser_ID());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }



    session.setAttribute("categories",years);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Money Breakdown");

    req.getRequestDispatcher("WEB-INF/Budget_App/moneyBreakdown.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Budget Home");
    req.getRequestDispatcher("WEB-INF/Budget_App/home.jsp").forward(req, resp);

  }
}

