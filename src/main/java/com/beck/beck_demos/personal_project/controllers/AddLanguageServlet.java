package com.beck.beck_demos.personal_project.controllers;
import com.beck.beck_demos.personal_project.data.LanguageDAO;
import com.beck.beck_demos.personal_project.models.Language;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  Language table
 Created By Jonathan Beck3/18/2024
 ***************/

@WebServlet("/addLanguage")
public class AddLanguageServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Language");
    req.getRequestDispatcher("WEB-INF/personal-project/AddLanguage.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String _Name = req.getParameter("inputlanguageName");
    String _is_active = req.getParameter("inputlanguageis_active");
    Map<String, String> results = new HashMap<>();
    results.put("Name",_Name);
    results.put("is_active",_is_active);
    Language language = new Language();
    int errors =0;
    try {
      language.setName(_Name);
    } catch(IllegalArgumentException e) {results.put("languageNameerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=LanguageDAO.add(language);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Language Added");
        resp.sendRedirect("all-Languages");
        return;
      } else {
        results.put("dbStatus","Language Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Language ");
    req.getRequestDispatcher("WEB-INF/personal-project/AddLanguage.jsp").forward(req, resp);

  }
}

