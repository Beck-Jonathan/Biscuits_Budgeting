package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.TwoFADAO;
import com.beck.beck_demos.personal_project.data.UserDAO;
import com.beck.beck_demos.personal_project.models.TwoFA;
import com.beck.beck_demos.personal_project.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/twoFA")
public class TwoFaServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("pageTitle", "Two Factor Auth");
    Map<String, String> results = new HashMap<>();
    String id = req.getParameter("UserID");
    HttpSession session = req.getSession();
    //session.invalidate();



    req.setAttribute("results", results);

    req.getRequestDispatcher("WEB-INF/personal-project/TwoFA.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String twoFACode = req.getParameter("inputuser2faCode");
    HttpSession session = req.getSession();
    Integer userID= (Integer) session.getAttribute("UserID");
    Map<String, String> results = new HashMap<>();
    results.put("inputuser2faCode",twoFACode);

    try {
      String twofa = TwoFADAO.getTwoFAById(userID);
      if (twoFACode.equals(twofa)){
        results.put("dbStatus","Good 2fa code!");
        try{
          int result = UserDAO.activate(userID);
          if (result==0){
            throw new Exception();
          }
          else{
            results.put("dbStatus","user activated!");
            User user = new User();
            user.setUser_ID(userID);
            session.setAttribute("User",UserDAO.getUserByPrimaryKey(user));
            resp.sendRedirect("home");
            return;
          }
        }
        catch(Exception ex){
          results.put("dbStatus","database error!");
        }
      }
      else {
        results.put("dbStatus","Bad 2fa code!");
      }
    } catch(IllegalArgumentException e) {results.put("dbStatus", e.getMessage());

    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Two FA Code ");
    req.getRequestDispatcher("WEB-INF/personal-project/TwoFA.jsp").forward(req, resp);
  }
}
