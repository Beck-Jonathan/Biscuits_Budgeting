package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet For Deleteing from the Status table
 Created By Jonathan Beck3/19/2024
 ***************/

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
import java.util.List;
import java.util.Map;
@WebServlet("/deletestatus")public class DeleteStatusServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete Status");
    int StatusID = Integer.valueOf(req.getParameter("statusid"));
    int mode = Integer.valueOf(req.getParameter("mode"));
    int result = 0;
    if (mode==0){
      try{
        result = StatusDAO.deleteStatus(StatusID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    else {
      try{
        result = StatusDAO.undeleteStatus(StatusID);
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
      }
    }
    List<Status> statuss = null;
    statuss = StatusDAO.getAllStatus();
    req.setAttribute("results",results);
    req.setAttribute("Statuss", statuss);
    req.setAttribute("pageTitle", "All Status");
    req.getRequestDispatcher("WEB-INF/personal-project/all-Status.jsp").forward(req, resp);
  }
}



