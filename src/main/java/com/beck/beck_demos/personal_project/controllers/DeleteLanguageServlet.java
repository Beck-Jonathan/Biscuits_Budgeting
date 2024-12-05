package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet For Deleteing from the Language table
 Created By Jonathan Beck3/19/2024
 ***************/

import com.beck.beck_demos.personal_project.data.LanguageDAO;
import com.beck.beck_demos.personal_project.models.Language;
import com.beck.beck_demos.personal_project.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/deletelanguage")public class DeleteLanguageServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||user.getPrivilege_ID()<PRIVILEGE_NEEDED){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }
    Map<String, String> results = new HashMap<>();

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete Language");
    int LanguageID = Integer.valueOf(req.getParameter("languageid"));
    int mode = Integer.valueOf(req.getParameter("mode"));
    int result = 0;
    if (mode==0){
      try{
        result = LanguageDAO.deleteLanguage(LanguageID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    else {
      try{
        result = LanguageDAO.undeleteLanguage(LanguageID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    List<Language> languages = null;
    languages = LanguageDAO.getAllLanguage();
    req.setAttribute("results",results);
    req.setAttribute("Languages", languages);
    req.setAttribute("pageTitle", "All Language");
    req.getRequestDispatcher("WEB-INF/personal-project/all-Language.jsp").forward(req, resp);
  }
}

