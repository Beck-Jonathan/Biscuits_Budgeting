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

@WebServlet("/PieChart")
public class PieChartController extends HttpServlet {
  private iTransactionDAO transactionDAO;

  @Override
  public void init() throws ServletException {
    transactionDAO = new TransactionDAO();
  }
  public void init(iTransactionDAO transactionDAO) {
    this.transactionDAO = transactionDAO;
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession();

    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
    }
    Map<String,String> results = new HashMap<>();
    List<List<Category_VM>> breakdown = new ArrayList<>();

    try {
      breakdown = transactionDAO.getAnalysis(breakdown, user.getUser_ID());
    } catch (Exception e) {
      results.put("dbError","database Error");
    }
    if (breakdown.isEmpty()) {
      results.put("dbError","No data found");
      req.setAttribute("pageTitle", "Pie Chart");
      req.getRequestDispatcher("WEB-INF/Budget_App/PieChart.jsp").forward(req, resp);
      return;
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
    List<Integer> allYears = new ArrayList<>();
    for (List<Category_VM> categories: breakdown) {
      allYears.add(categories.get(0).getYear());
    }

    List<String> allCategories = new ArrayList<>();
    for (Category_VM category : breakdown.get(0)) {
      allCategories.add(category.getCategory_ID());
    }

    session.setAttribute("Breakdown", breakdown);
    session.setAttribute("Categories",allCategories);
    session.setAttribute("yearRange",allYears);

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Pie Chart");

    req.getRequestDispatcher("WEB-INF/Budget_App/PieChart.jsp").forward(req, resp);
  }


}
