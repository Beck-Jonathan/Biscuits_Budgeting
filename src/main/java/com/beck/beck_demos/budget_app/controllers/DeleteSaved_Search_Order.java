package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.Saved_Search_OrderDAO;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order;
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
@WebServlet("/deletesaved_search_order")
public class DeleteSaved_Search_Order extends HttpServlet {
  private iSaved_Search_OrderDAO saved_search_orderDAO;
  ;
  @Override
  public void init() {
    saved_search_orderDAO = new Saved_Search_OrderDAO();

  }
  public void init(iSaved_Search_OrderDAO saved_search_orderDAO){
    this.saved_search_orderDAO = saved_search_orderDAO;

  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

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
    req.setAttribute("pageTitle", "Delete Saved_Search_Order");
    String _Saved_Search_OrderID = req.getParameter("saved_search_orderid");

    int Saved_Search_OrderID = -1;
    if (_Saved_Search_OrderID!=null && !_Saved_Search_OrderID.equals("")){
      Saved_Search_OrderID = Integer.parseInt(_Saved_Search_OrderID);
    }
    int result = 0;
    if (Saved_Search_OrderID!= -1) {
      try {
        result = saved_search_orderDAO.delete(Saved_Search_OrderID,user.getUser_ID());
      } catch (Exception ex) {
        results.put("dbStatus", ex.getMessage());
      }
    }



    req.setAttribute("result",result);
    req.setAttribute("results",results);

    req.setAttribute("pageTitle", "All Saved_Search_Order");
    req.getRequestDispatcher("WEB-INF/Budget_App/all-Saved_Search_Orders.jsp").forward(req, resp);
  }
}
