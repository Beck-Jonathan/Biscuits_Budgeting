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
import java.util.*;

/******************
 Create the Servlet  For adding to The  Saved_Search_Order table
 Created By Jonathan Beck 2/4/2025
 ***************/

@WebServlet("/addSaved_Search_Order")
public class AddSaved_Search_OrderServlet extends HttpServlet{
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
      resp.sendRedirect("budget_home");
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Saved_Search_Order");

    req.getRequestDispatcher("WEB-INF/Budget_App/AddSaved_Search_Order.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level

    List<String> ROLES_NEEDED = new ArrayList<>();

//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("budget_home");
      return;
    }




    String _Nickname = req.getParameter("inputsaved_search_orderNickname");
    if (_Nickname!=null) {
      _Nickname=_Nickname.trim();
    }
    String _Description = req.getParameter("inputsaved_search_orderDescription");
    if (_Description!=null) {
      _Description=_Description.trim();
    }

    Map<String, String> results = new HashMap<>();

    results.put("Nickname",_Nickname);
    results.put("Description",_Description);


    Saved_Search_Order saved_search_order = new Saved_Search_Order();
    int errors =0;
    try {
      saved_search_order.setOwned_User(user.getUser_ID());
    } catch(Exception e) {results.put("saved_search_orderOwned_Usererror", e.getMessage());
      errors++;
    }
    try {
      saved_search_order.setNickname(_Nickname);
    } catch(Exception e) {results.put("saved_search_orderNicknameerror", e.getMessage());
      errors++;
    }
    try {
      saved_search_order.setDescription(_Description);
    } catch(Exception e) {results.put("saved_search_orderDescriptionerror", e.getMessage());
      errors++;
    }
    try {
      saved_search_order.setLast_Used(new Date());
    } catch(Exception e) {results.put("saved_search_orderLast_Usederror", e.getMessage());
      errors++;
    }
    try {
      saved_search_order.setLast_Updated(new Date());
    } catch(Exception e) {results.put("saved_search_orderLast_Updatederror", e.getMessage());
      errors++;
    }
    try {
      saved_search_order.setTimes_Ran(0);
    } catch(Exception e) {results.put("saved_search_orderTimes_Ranerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=saved_search_orderDAO.add(saved_search_order);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Saved_Search_Order Added");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Saved_Search_Orders");
        return;
      } else {
        results.put("dbStatus","Saved_Search_Order Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Saved_Search_Order ");
    req.getRequestDispatcher("WEB-INF/Budget_App/AddSaved_Search_Order.jsp").forward(req, resp);

  }
}
