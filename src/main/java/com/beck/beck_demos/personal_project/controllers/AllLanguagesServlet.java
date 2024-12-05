package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet  For Viewing all of the  Language table
 Created By Jonathan Beck3/18/2024
 ***************/
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
import java.util.List;
import java.util.Map;
@WebServlet("/all-Language")
public class AllLanguagesServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute("currentPage", req.getRequestURL());
    List<Language> languages = null;

    languages = LanguageDAO.getAllLanguage();

    req.setAttribute("Languages", languages);
    req.setAttribute("pageTitle", "All Languages");
    req.getRequestDispatcher("WEB-INF/personal-project/all-Language.jsp").forward(req, resp);

  }
}