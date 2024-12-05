package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.PrivilegeDAO;
import com.beck.beck_demos.personal_project.models.Privilege;
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

@WebServlet("/deleteprivilege")
public class DeletePrivilegeServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Privacy Policy");
    int PrivilegeID = Integer.valueOf(req.getParameter("privilegeid"));
    int mode = Integer.valueOf(req.getParameter("mode"));
    int result = 0;
    if (mode==0){
      try{
        result = PrivilegeDAO.deletePrivilege(PrivilegeID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    else {
      try{
        result = PrivilegeDAO.undeletePrivilege(PrivilegeID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }

    }

    List<Privilege> privileges = null;
    privileges = PrivilegeDAO.getAllPrivilege();
    req.setAttribute("results",results);
    req.setAttribute("Privileges", privileges);
    req.setAttribute("pageTitle", "All Privileges");

    req.getRequestDispatcher("WEB-INF/personal-project/all-Privileges.jsp").forward(req, resp);
  }
}