package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.Saved_Search_OrderDAO;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order_Line;
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
/******************
 Create the Servlet Viuw/Edit from the Saved_Search_Order_Line table
 Created By Jonathan Beck 2/7/2025
 ***************/

@WebServlet("/editSaved_Search_Order_Line")
public class EditSaved_Search_Order_LineServlet extends HttpServlet {
  private iSaved_Search_OrderDAO saved_search_orderDAO;
  @Override
  public void init() {
    saved_search_orderDAO = new Saved_Search_OrderDAO();
  }
  public void init(iSaved_Search_OrderDAO saved_search_orderDAO) {
    this.saved_search_orderDAO = saved_search_orderDAO;
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

//to get the old Saved_Search_Order_Line
    Saved_Search_Order_Line _oldSaved_Search_Order_Line= new Saved_Search_Order_Line();
    _oldSaved_Search_Order_Line.setUser_ID(user.getUser_ID());
    String old_Search_Phrase = req.getParameter("oldPhrase");
    if (old_Search_Phrase!=null){
      old_Search_Phrase=old_Search_Phrase.trim();
    }
    String old_Category = req.getParameter("oldCategory");
    if (old_Category!=null){
      old_Category=old_Category.trim();
    }

//to get the new event's info
    String _Saved_Search_Order_ID = req.getParameter("inputsaved_search_order_lineSaved_Search_Order_ID");
    if (_Saved_Search_Order_ID!=null){
      _Saved_Search_Order_ID=_Saved_Search_Order_ID.trim();
    }
    String _Line_No = req.getParameter("inputsaved_search_order_lineLine_No");
    if (_Line_No!=null){
      _Line_No=_Line_No.trim();
    }
    String _Category_ID = req.getParameter("inputsaved_search_order_lineCategory_ID");
    if (_Category_ID!=null){
      _Category_ID=_Category_ID.trim();
    }

    String _Search_Phrase = req.getParameter("inputsaved_search_order_lineSearch_Phrase");
    if (_Search_Phrase!=null){
      _Search_Phrase=_Search_Phrase.trim();
    }
    String _Is_Active = req.getParameter("inputsaved_search_order_lineIs_Active");
    if (_Is_Active!=null){
      _Is_Active=_Is_Active.trim();
    }
    results.put("Saved_Search_Order_ID",_Saved_Search_Order_ID);
    results.put("Line_No",_Line_No);
    results.put("Category_ID",_Category_ID);
   ;
    results.put("Search_Phrase",_Search_Phrase);
    results.put("Is_Active",_Is_Active);
    Saved_Search_Order_Line _newSaved_Search_Order_Line = new Saved_Search_Order_Line();
    int errors =0;
    try {
      _newSaved_Search_Order_Line.setSaved_Search_Order_ID(Integer.valueOf(_Saved_Search_Order_ID));

      _oldSaved_Search_Order_Line.setSaved_Search_Order_ID(Integer.valueOf(_Saved_Search_Order_ID));
    } catch(Exception e) {results.put("saved_search_order_lineSaved_Search_Order_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newSaved_Search_Order_Line.setLine_No(Integer.valueOf(_Line_No));
      _oldSaved_Search_Order_Line.setLine_No(Integer.valueOf(_Line_No));
    } catch(Exception e) {results.put("saved_search_order_lineLine_Noerror", e.getMessage());
      errors++;
    }
    try {
      _oldSaved_Search_Order_Line.setCategory_ID(old_Category);
      _newSaved_Search_Order_Line.setCategory_ID(_Category_ID);
    } catch(Exception e) {results.put("saved_search_order_lineCategory_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newSaved_Search_Order_Line.setUser_ID(user.getUser_ID());
    } catch(Exception e) {results.put("saved_search_order_lineUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newSaved_Search_Order_Line.setSearch_Phrase(_Search_Phrase);
      _oldSaved_Search_Order_Line.setSearch_Phrase(old_Search_Phrase);
    } catch(Exception e) {results.put("saved_search_order_lineSearch_Phraseerror", e.getMessage());
      errors++;
    }
    try {
      _newSaved_Search_Order_Line.setIs_Active(Boolean.parseBoolean(_Is_Active));
    } catch(Exception e) {
      _newSaved_Search_Order_Line.setIs_Active(true);
      results.put("saved_search_order_lineIs_Activeerror", e.getMessage());
      //errors++;
    }
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=saved_search_orderDAO.updateLine(_oldSaved_Search_Order_Line,_newSaved_Search_Order_Line);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Saved_Search_Order_Line updated");
        req.setAttribute("results",results);
        resp.sendRedirect("editSaved_Search_Order?saved_search_orderid="+_Saved_Search_Order_ID+"&mode=view");

        return;
      } else {
        results.put("dbStatus","Saved_Search_Order_Line Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Saved_Search_Order_Line ");
    req.getRequestDispatcher("WEB-INF/Budget_App/EditSaved_Search_Order.jsp").forward(req, resp);
  }
}



