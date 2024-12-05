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
import java.util.Map;
/******************
 Create the Servlet  For adding to The  Privilege table
 Created By Jonathan Beck3/18/2024
 ***************/

@WebServlet("/addPrivilege")
public class AddPrivilegeServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Privilege");
    req.getRequestDispatcher("WEB-INF/personal-project/AddPrivilege.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String _Name = req.getParameter("inputprivilegeName");
    String _is_active = req.getParameter("inputprivilegeis_active");
    Map<String, String> results = new HashMap<>();
    results.put("Name",_Name);
    results.put("is_active",_is_active);
    Privilege privilege = new Privilege();
    int errors =0;
    try {
      privilege.setName(_Name);
    } catch(IllegalArgumentException e) {results.put("privilegeNameerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=PrivilegeDAO.add(privilege);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Privilege Added");
        resp.sendRedirect("all-Privileges");
        return;
      } else {
        results.put("dbStatus","Privilege Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Privilege ");
    req.getRequestDispatcher("WEB-INF/personal-project/AddPrivilege.jsp").forward(req, resp);

  }
}



