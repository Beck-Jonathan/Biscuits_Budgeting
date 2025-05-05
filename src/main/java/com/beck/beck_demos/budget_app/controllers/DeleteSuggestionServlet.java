package com.beck.beck_demos.budget_app.controllers;


/******************
 Create the Servlet For Deleteing from the Suggestion table
 Created By Jonathan Beck 5/1/2025
 ***************/

import com.beck.beck_demos.budget_app.data.SuggestionDAO;
import com.beck.beck_demos.budget_app.models.Suggestion;
import com.beck.beck_demos.budget_app.models.Suggestion_VM;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iSuggestionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/deletesuggestion")
public class DeleteSuggestionServlet extends HttpServlet {
  private iSuggestionDAO suggestionDAO;

  @Override
  public void init(){
    suggestionDAO = new SuggestionDAO();

  }
  public void init(iSuggestionDAO suggestionDAO){
    this.suggestionDAO = suggestionDAO;

  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
//add roles here
    HttpSession session = req.getSession();

    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("schedule_in");
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete Suggestion");
    String SuggestionID = req.getParameter("suggestionid");

    boolean _ajax=false;
    String AJAX = req.getParameter("AJAX");
    try {
      _ajax = Boolean.parseBoolean(AJAX);
    } catch (Exception e){
      _ajax = false;
    }
    Integer result = 0;

    try{
      result = suggestionDAO.deleteSuggestion(SuggestionID);
    }
    catch(Exception ex){
      results.put("dbStatus",ex.getMessage());
    }

    if (_ajax){
      resp.setStatus(200);
      resp.setContentType("text/plain");
      PrintWriter writer=resp.getWriter();
      writer.write(result.toString());
      writer.flush();
      writer.close();
      return;
    }
    List<Suggestion_VM> suggestions = null;
    try {
      suggestions = suggestionDAO.getAllSuggestion(0,20,"","");
    }
    catch (Exception ex){
      results.put("dbStatus",ex.getMessage());
    }
    req.setAttribute("result",result);
    req.setAttribute("results",results);
    req.setAttribute("Suggestions", suggestions);
    req.setAttribute("pageTitle", "All Suggestion");
    req.getRequestDispatcher("WEB-INF/Budget_App/all-Suggestions.jsp").forward(req, resp);
  }
}

