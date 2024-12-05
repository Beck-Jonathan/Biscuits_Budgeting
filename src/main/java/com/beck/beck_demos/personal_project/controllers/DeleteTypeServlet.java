package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet For Deleteing from the Type table
 Created By Jonathan Beck3/19/2024
 ***************/

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
import java.util.List;
import java.util.Map;
@WebServlet("/deletetype")public class DeleteTypeServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete Type");
    int TypeID = Integer.valueOf(req.getParameter("typeid"));
    int mode = Integer.valueOf(req.getParameter("mode"));
    int result = 0;
    if (mode==0){
      try{
        result = TypeDAO.deleteType(TypeID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    else {
      try{
        result = TypeDAO.undeleteType(TypeID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    List<Type> types = null;
    types = TypeDAO.getAllType();
    req.setAttribute("results",results);
    req.setAttribute("Types", types);
    req.setAttribute("pageTitle", "All Type");
    req.getRequestDispatcher("WEB-INF/personal-project/all-Type.jsp").forward(req, resp);
  }
}

