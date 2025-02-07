package com.beck.beck_demos.budget_app.controllers;

/******************
 Create the Servlet  For Viewing all of the  Saved_Search_Order table
 Created By Jonathan Beck 2/4/2025
 ***************/

import com.beck.beck_demos.budget_app.data.Saved_Search_OrderDAO;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order_VM;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iSaved_Search_OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/all-Saved_Search_Orders")
public class AllSaved_Search_OrderServlet  extends HttpServlet {
  private iSaved_Search_OrderDAO saved_search_orderDAO;

  @Override
  public void init() {
    saved_search_orderDAO = new Saved_Search_OrderDAO();

  }
  public void init(iSaved_Search_OrderDAO saved_search_orderDAO){
    this.saved_search_orderDAO = saved_search_orderDAO;

  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();

    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("/budget_in");
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    List<Saved_Search_Order_VM> saved_search_orders = null;
    try {
      saved_search_orders =saved_search_orderDAO.getSaved_Search_OrderbyUser(user.getUser_ID(),20,0);
    } catch (Exception e) {
      saved_search_orders = new ArrayList<>();
    }
    req.setAttribute("Saved_Search_Orders", saved_search_orders);
    req.setAttribute("pageTitle", "All Saved_Search_Orders");
    req.getRequestDispatcher("WEB-INF/Budget_App/all-Saved_Search_Orders.jsp").forward(req,resp);

  }
}
