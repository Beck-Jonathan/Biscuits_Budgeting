package com.beck.beck_demos.budget_app.controllers;


import com.beck.beck_demos.budget_app.data.CategoryDAO;
import com.beck.beck_demos.budget_app.data.Saved_Search_OrderDAO;
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.Category;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet Viuw/Edit from the Saved_Search_Order table
 Created By Jonathan Beck 2/4/2025
 ***************/

@WebServlet("/editSaved_Search_Order")
public class EditSaved_Search_OrderServlet extends HttpServlet{

  private iSaved_Search_OrderDAO saved_search_orderDAO;
  private iCategoryDAO categoryDAO;
  @Override
  public void init(){
    saved_search_orderDAO = new Saved_Search_OrderDAO();
    categoryDAO = new CategoryDAO();

  }
  public void init(iSaved_Search_OrderDAO saved_search_orderDAO, iCategoryDAO categoryDAO){
    this.saved_search_orderDAO = saved_search_orderDAO;
    this.categoryDAO = categoryDAO;

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

    String mode = req.getParameter("mode");
    int primaryKey = -1;
    try{
      primaryKey = Integer.parseInt(req.getParameter("saved_search_orderid"));
    }catch (Exception e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    Saved_Search_Order saved_search_order= new Saved_Search_Order();
    List<Category> allCategorys = new ArrayList<>();
    try{
      saved_search_order.setSaved_Search_Order_ID(primaryKey);
    } catch (Exception e){
      req.setAttribute("dbStatus",e.getMessage());
    }
    try{
      saved_search_order=saved_search_orderDAO.getSaved_Search_OrderByPrimaryKey(saved_search_order);
      allCategorys = categoryDAO.getCategoryByUser(user.getUser_ID());
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }
    req.setAttribute("Categorys", allCategorys);
    session.setAttribute("saved_search_order",saved_search_order);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit Saved_Search_Order");

    req.getRequestDispatcher("WEB-INF/Budget_App/EditSaved_Search_Order.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs

//to get the old Saved_Search_Order
    Saved_Search_Order _oldSaved_Search_Order= (Saved_Search_Order)session.getAttribute("saved_search_order");
//to get the new event's info

    String _Nickname = req.getParameter("inputsaved_search_orderNickname");
    if (_Nickname!=null){
      _Nickname=_Nickname.trim();
    }
    String _Description = req.getParameter("inputsaved_search_orderDescription");
    if (_Description!=null){
      _Description=_Description.trim();
    }


    results.put("Nickname",_Nickname);
    results.put("Description",_Description);

    Saved_Search_Order _newSaved_Search_Order = new Saved_Search_Order();
    int errors =0;
    _newSaved_Search_Order.setOwned_User(user.getUser_ID());

    try {
      _newSaved_Search_Order.setNickname(_Nickname);
    } catch(Exception e) {results.put("saved_search_orderNicknameerror", e.getMessage());
      errors++;
    }
    try {
      _newSaved_Search_Order.setDescription(_Description);
    } catch(Exception e) {results.put("saved_search_orderDescriptionerror", e.getMessage());
      errors++;
    }


//to update the database
    int result=0;
    if (errors==0){
      try{
        result=saved_search_orderDAO.update(_oldSaved_Search_Order,_newSaved_Search_Order);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Saved_Search_Order updated");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Saved_Search_Orders");
        return;
      } else {
        results.put("dbStatus","Saved_Search_Order Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Saved_Search_Order ");
    req.getRequestDispatcher("WEB-INF/Budget_App/EditSaved_Search_Order.jsp").forward(req, resp);
  }
}

