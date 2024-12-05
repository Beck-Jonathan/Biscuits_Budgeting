package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.TypeDAO;
import com.beck.beck_demos.personal_project.models.Type;
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
 Create the Servlet  For adding to The  Type table
 Created By Jonathan Beck3/18/2024
 ***************/

@WebServlet("/addType")
public class AddTypeServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Type");
    req.getRequestDispatcher("WEB-INF/personal-project/AddType.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String _Name = req.getParameter("inputtypeName");
    String _is_active = req.getParameter("inputtypeis_active");
    Map<String, String> results = new HashMap<>();
    results.put("Name",_Name);
    results.put("is_active",_is_active);
    Type type = new Type();
    int errors =0;
    try {
      type.setName(_Name);
    } catch(IllegalArgumentException e) {results.put("typeNameerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=TypeDAO.add(type);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Type Added");
        resp.sendRedirect("all-Types");
        return;
      } else {
        results.put("dbStatus","Type Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Type ");
    req.getRequestDispatcher("WEB-INF/personal-project/AddType.jsp").forward(req, resp);

  }
}

