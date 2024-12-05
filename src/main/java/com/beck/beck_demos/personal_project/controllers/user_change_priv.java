package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.PrivilegeDAO;
import com.beck.beck_demos.personal_project.data.UserDAO;
import com.beck.beck_demos.personal_project.models.Privilege;
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

@WebServlet("/user_change_priv")
public class user_change_priv extends HttpServlet {
  public List<Privilege> all_priv;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    all_priv = PrivilegeDAO.getAllPrivilege();
    req.setAttribute("privileges",all_priv);
        req.setAttribute("pageTitle", "Edit your Privileges");
    req.getRequestDispatcher("WEB-INF/personal-project/user_change_priv.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    all_priv = PrivilegeDAO.getAllPrivilege();
    req.setAttribute("privileges",all_priv);
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    int errors=0;
    Map<String,String> results = new HashMap<>();

    String new_priv = req.getParameter("inputPivLevel");
    int user_id = user.getUser_ID();
    int priv_for_db=0;
    try {
       priv_for_db = Integer.parseInt(new_priv);
       if (priv_for_db==0){throw new IllegalArgumentException("invalid priv id");}
    }
    catch(Exception ex){
      errors++;
      results.put("PrivLevelerror",ex.getMessage());
    }
    if (errors==0){
      try {
        int result = UserDAO.updatePriv(user_id, priv_for_db);
        if (result==1){
          user.setPrivilege_ID(priv_for_db);
          resp.sendRedirect("home");
          return;
        }
      }
      catch (Exception ex){
        errors++;
        results.put("dbStatus",ex.getMessage());
      }
    }


    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit your Privilege ");
    req.getRequestDispatcher("WEB-INF/personal-project/user_change_priv.jsp").forward(req, resp);

  }


}
