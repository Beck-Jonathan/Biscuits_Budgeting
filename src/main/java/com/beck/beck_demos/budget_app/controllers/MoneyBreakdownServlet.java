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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    Map<String,String> results = new HashMap<>();


    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
    }

    List<List<Category_VM>> breakdown = new ArrayList<>();


    try {
      breakdown = transactionDAO.getAnalysis(breakdown, user.getUser_ID());
    } catch (Exception e) {
      results.put("dbError","database Error");
    }

    for (List<Category_VM> category_vms : breakdown) {
      for (int i =0;i<category_vms.size();i++) {
        if (category_vms.get(i).getCategory_ID().equals("total in")){
          while (i<category_vms.size()-1) {
            Category_VM temp = category_vms.get(i + 1);
            category_vms.set(i + 1, category_vms.get(i));
            category_vms.set(i, temp);
            i++;
          }
        }
      }
    }
    for (List<Category_VM> category_vms : breakdown) {
      for (int i =0;i<category_vms.size();i++) {
        if (category_vms.get(i).getCategory_ID().equals("total out")){
          while (i<category_vms.size()-1) {
            Category_VM temp = category_vms.get(i + 1);
            category_vms.set(i + 1, category_vms.get(i));
            category_vms.set(i, temp);
            i++;
          }
        }
      }
    }


    int year_span = breakdown.size();
    int category_count = 0;
    for (List<Category_VM> year : breakdown) {
      if (year.size()>category_count){
        category_count = year.size();
      }
    }


    session.setAttribute("breakdown",breakdown);
    session.setAttribute("years",year_span);
    session.setAttribute("category_count",category_count);
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

