package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.data.Saved_Search_OrderDAO;
import com.beck.beck_demos.budget_app.iData.iSaved_Search_OrderDAO;
import com.beck.beck_demos.budget_app.models.Saved_Search_Order_Line;
import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

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
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute("User_B");

    // 1. Security Check
    if (user == null || !user.getRoles().contains("User")) {
      sendResponse(resp, "-1");
      return;
    }

    // 2. Parse Inputs
    String old_Search_Phrase = trimParam(req.getParameter("oldPhrase"));
    String old_Category = trimParam(req.getParameter("oldCategory"));

    String _Saved_Search_Order_ID = trimParam(req.getParameter("inputsaved_search_order_lineSaved_Search_Order_ID"));
    String _Line_No = trimParam(req.getParameter("inputsaved_search_order_lineLine_No"));
    String _Category_ID = trimParam(req.getParameter("inputsaved_search_order_lineCategory_ID"));
    String _Search_Phrase = trimParam(req.getParameter("inputsaved_search_order_lineSearch_Phrase"));
    String _Is_Active = trimParam(req.getParameter("inputsaved_search_order_lineIs_Active"));

    // 3. Validation & Object Mapping
    Saved_Search_Order_Line oldLine = new Saved_Search_Order_Line();
    Saved_Search_Order_Line newLine = new Saved_Search_Order_Line();

    try {
      oldLine.setUser_ID(user.getUser_ID());
      oldLine.setSaved_Search_Order_ID(_Saved_Search_Order_ID);
      oldLine.setCategory_ID(old_Category);
      oldLine.setSearch_Phrase(old_Search_Phrase);
      oldLine.setLine_No(Integer.valueOf(_Line_No));

      newLine.setSaved_Search_Order_ID(_Saved_Search_Order_ID);
      newLine.setLine_No(Integer.valueOf(_Line_No));
      newLine.setCategory_ID(_Category_ID);
      newLine.setUser_ID(user.getUser_ID());
      newLine.setSearch_Phrase(_Search_Phrase);
      newLine.setIs_Active(Boolean.parseBoolean(_Is_Active));
    } catch (Exception e) {
      sendResponse(resp, "-2"); // Parse/Validation error
      return;
    }

    // 4. Database Update
    try {
      int result = saved_search_orderDAO.updateLine(oldLine, newLine);
      if (result > 0) {
        sendResponse(resp, "1");
      } else {
        sendResponse(resp, "0"); // No rows updated
      }
    } catch (Exception ex) {
      sendResponse(resp, "-3"); // Database/System error
    }
  }

  private String trimParam(String param) {
    return (param != null) ? param.trim() : "";
  }

  private void sendResponse(HttpServletResponse response, String resultCode) {
    try {
      response.setStatus(200);
      response.setContentType("text/plain");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      out.print(resultCode);
      out.flush();
    } catch (IOException e) {
      System.err.println("Error writing response: " + e.getMessage());
    }
  }
}