package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.StatusDAO;
import com.beck.beck_demos.personal_project.models.Status;
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
 Create the Servlet  For adding to The  Status table
 Created By Jonathan Beck3/18/2024
 ***************/

@WebServlet("/addStatus")
public class AddStatusServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Status");
    req.getRequestDispatcher("WEB-INF/personal-project/AddStatus.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String _Name = req.getParameter("inputstatusName");
    String _is_active = req.getParameter("inputstatusis_active");
    Map<String, String> results = new HashMap<>();
    results.put("Name",_Name);
    results.put("is_active",_is_active);
    Status status = new Status();
    int errors =0;
    try {
      status.setName(_Name);
    } catch(IllegalArgumentException e) {results.put("statusNameerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=StatusDAO.add(status);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Status Added");
        resp.sendRedirect("all-Status");
        return;
      } else {
        results.put("dbStatus","Status Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Status ");
    req.getRequestDispatcher("WEB-INF/personal-project/AddStatus.jsp").forward(req, resp);

  }
}



