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
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/PieChartMonthAJAX")
public class PieChartMonthlyAJAX extends HttpServlet {
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
      resp.sendRedirect("budget_home");
      return;
    }
    int year = 2025;
    try {
      year = Integer.parseInt(req.getParameter("year"));
    }
    catch (Exception e) {
      year = 2025;
    }
    String mode = req.getParameter("mode");
    int errors =0;
    Map<String,String> results = new HashMap<>();
    List<List<Category_VM>> breakdown = new ArrayList<>();
    List<String> allCategories = new ArrayList<>();
    if (mode==null){
      mode="monthly";
    }

    if (mode.equals("monthly")){
      try {
        breakdown = transactionDAO.getMonthlyAnalysis(breakdown, user.getUser_ID(),year);
      } catch (Exception e) {
        results.put("dbError","database Error");
      }

    }
    else if (mode.equals("annual")){
      try {
        breakdown = transactionDAO.getAnalysis(breakdown, user.getUser_ID());
      } catch (Exception e) {
        results.put("dbError","database Error");
      }

    }
    else {
      errors=1;
      PrintWriter out = resp.getWriter();
      //resp.setHeader("Content-Type", "application/json;charset=UTF-8");
      resp.setCharacterEncoding("UTF-8");
      out.print("error");
      out.flush();
    }

   try {
      for (List<Category_VM> category_vms : breakdown) {
        for (int i = 0; i < category_vms.size(); i++) {
          if (category_vms.get(i).getCategory_ID().equals("total in")) {
            while (i < category_vms.size() - 1) {
              Category_VM temp = category_vms.get(i + 1);
              category_vms.set(i + 1, category_vms.get(i));
              category_vms.set(i, temp);
              i++;
            }
          }
        }
      }
      for (List<Category_VM> category_vms : breakdown) {
        for (int i = 0; i < category_vms.size(); i++) {
          if (category_vms.get(i).getCategory_ID().equals("total out")) {
            while (i < category_vms.size() - 1) {
              Category_VM temp = category_vms.get(i + 1);
              category_vms.set(i + 1, category_vms.get(i));
              category_vms.set(i, temp);
              i++;
            }
          }
        }
      }


      for (Category_VM category : breakdown.get(0)) {
        allCategories.add(category.getCategory_ID());
      }
    }
    catch (Exception e){
      resp.sendRedirect("budget_home");
      return;
    }


    ObjectMapper mapper = new ObjectMapper();
    String output="";
    // convert user object to json string and return it

      output = mapper.writeValueAsString(breakdown);

    PrintWriter out = resp.getWriter();
    resp.setHeader("Content-Type", "application/json;charset=UTF-8");
    resp.setCharacterEncoding("UTF-8");
    out.print(output);
    out.flush();

  }

}
