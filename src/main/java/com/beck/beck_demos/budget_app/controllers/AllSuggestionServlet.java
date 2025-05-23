package com.beck.beck_demos.budget_app.controllers;

/******************
 Create the Servlet  For Viewing all of the  Suggestion table
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/all-Suggestions")
public class AllSuggestionServlet extends HttpServlet {private iSuggestionDAO suggestionDAO;

  @Override
  public void init(){
    suggestionDAO = new SuggestionDAO();

  }
  public void init(iSuggestionDAO suggestionDAO){
    this.suggestionDAO = suggestionDAO;

  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    Map<String,String> results = new HashMap<>();
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    if (user==null||!user.getRoles().contains("User")){
      resp.sendRedirect("budget_in");
      return;
    }

    int errors = 0;
    String search_term = req.getParameter("search");
    if (search_term==null){
      search_term ="";
    }
    if (!search_term.equals("") && (search_term.length()<2||search_term.length()>100)){
      errors++;
      results.put("searchError","Invalid search term");
    }
    String Appplication_Name = req.getParameter("App");
    if (Appplication_Name==null){
      Appplication_Name="";
      session.setAttribute("App",Appplication_Name);
    }
    int suggestioncount=0;
    int page_number=1;
    int page_size = 20;
    try {
      page_number = Integer.parseInt(req.getParameter("suggestion_page"));
    } catch (Exception e){
      page_number=1;
    }
    session.setAttribute("suggestion_page_number",page_number);
    int offset=(page_number-1)*(page_size);
    session.setAttribute("currentPage",req.getRequestURL());
    List<Suggestion_VM> suggestions = null;
    int suggestion_count = 0;
    List<String> Applications = new ArrayList<>();
    Applications.add("Budgeting");
    Applications.add("Homepage");
    Applications.add("Scheduling");
    Applications.add("Derby");
    Applications.add("Warriors");
    Applications.add("Generator");
    Applications.add("Other");
    try {
      //List <String> allApplications = suggestionDAO.getDistinctApplicationForDropdown();
      //req.setAttribute("Applications", allApplications);
      suggestion_count = suggestionDAO.getSuggestionCount(search_term, "", Appplication_Name);
      suggestions =suggestionDAO.getAllSuggestion(offset,page_size,search_term,"",Appplication_Name);
    } catch (Exception e) {
      suggestions = new ArrayList<>();
    }
    int total_pages = (suggestion_count/page_size)+1;
    req.setAttribute("Applications",Applications);
    req.setAttribute("noOfPages", total_pages);
    req.setAttribute("Suggestions", suggestions);
    req.setAttribute("pageTitle", "All Suggestions");
    req.getRequestDispatcher("WEB-INF/Budget_App/all-Suggestions.jsp").forward(req,resp);

  }

  }

