package com.beck.beck_demos.budget_app.controllers;


import com.beck.beck_demos.budget_app.data.SuggestionDAO;
import com.beck.beck_demos.budget_app.models.Suggestion;
import com.beck.beck_demos.budget_app.models.User;
import com.beck.beck_demos.budget_app.iData.iSuggestionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/******************
 Create the Servlet  For adding to The  Suggestion table
 Created By Jonathan Beck 5/1/2025
 ***************/

@WebServlet("/addSuggestion")
public class AddSuggestionServlet extends HttpServlet{
  private iSuggestionDAO suggestionDAO;

  @Override
  public void init() {
    suggestionDAO = new SuggestionDAO();

  }
  public void init(iSuggestionDAO suggestionDAO){
    this.suggestionDAO = suggestionDAO;

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
      session.invalidate();
      resp.sendRedirect("budget_home");
      return;
    }
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Suggestion");


    req.getRequestDispatcher("WEB-INF/Budget_App/AddSuggestion.jsp").forward(req, resp);
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
      session.invalidate();
      resp.sendRedirect("budget_home");
      return;
    }

    String _Application_Name = req.getParameter("inputsuggestionApplication_Name");
    if (_Application_Name!=null) {
      _Application_Name=_Application_Name.trim();
    }
    String _content = req.getParameter("inputsuggestioncontent");
    if (_content!=null) {
      _content=_content.trim();
    }
    Map<String, String> results = new HashMap<>();
    String xxid = "1111-1111-1111-1111-1111-1111-1111-1111-1111";
    String extendedname = user.getUser_Name()+xxid;
    extendedname=extendedname.substring(0,36);



    results.put("Application_Name",_Application_Name);
    results.put("content",_content);
    Suggestion suggestion = new Suggestion();
    int errors =0;
    try {
      suggestion.setUser_ID(extendedname);
    } catch(Exception e) {results.put("suggestionUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      suggestion.setApplication_Name(_Application_Name);
    } catch(Exception e) {results.put("suggestionApplication_Nameerror", e.getMessage());
      errors++;
    }
    try {
      suggestion.setcontent(_content);
    } catch(Exception e) {results.put("suggestioncontenterror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=suggestionDAO.add(suggestion);
      }catch(Exception ex){
        results.put("dbError","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Suggestion Added");
        req.setAttribute("results",results);
        resp.sendRedirect("all-Suggestions");
        return;
      } else {
        results.put("dbStatus","Suggestion Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Add Suggestion");
    req.getRequestDispatcher("WEB-INF/Budget_App/AddSuggestion.jsp").forward(req, resp);

  }
}

