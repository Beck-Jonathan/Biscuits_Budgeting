package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.FacilityDAO;
import com.beck.beck_demos.personal_project.models.Facility;
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
/******************
 Create the Servlet  For adding to The  Facility table
 Created By Jonathan Beck3/18/2024
 ***************/

@WebServlet("/addFacility")
public class AddFacilityServlet extends HttpServlet{

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

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Facility");
    req.getRequestDispatcher("WEB-INF/personal-project/AddFacility.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||user.getPrivilege_ID()<PRIVILEGE_NEEDED){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }
    String _Name = req.getParameter("inputfacilityName");
    String _Addresss = req.getParameter("inputfacilityAddresss");
    String _City = req.getParameter("inputfacilityCity");
    String _State = req.getParameter("inputfacilityState");
    String _Zip = req.getParameter("inputfacilityZip");
    String _is_active = req.getParameter("inputfacilityis_active");
    Map<String, String> results = new HashMap<>();
    results.put("Name",_Name);
    results.put("Addresss",_Addresss);
    results.put("City",_City);
    results.put("State",_State);
    results.put("Zip",_Zip);
    results.put("is_active",_is_active);
    Facility facility = new Facility();
    int errors =0;
    try {
      facility.setName(_Name);
    } catch(IllegalArgumentException e) {results.put("facilityNameerror", e.getMessage());
      errors++;
    }
    try {
      facility.setAddresss(_Addresss);
    } catch(IllegalArgumentException e) {results.put("facilityAddressserror", e.getMessage());
      errors++;
    }
    try {
      facility.setCity(_City);
    } catch(IllegalArgumentException e) {results.put("facilityCityerror", e.getMessage());
      errors++;
    }
    try {
      facility.setState(_State);
    } catch(IllegalArgumentException e) {results.put("facilityStateerror", e.getMessage());
      errors++;
    }
    try {
      facility.setZip(_Zip);
    } catch(IllegalArgumentException e) {results.put("facilityZiperror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=FacilityDAO.add(facility);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Facility Added");
        resp.sendRedirect("all-Facilitys");
        return;
      } else {
        results.put("dbStatus","Facility Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Facility ");
    req.getRequestDispatcher("WEB-INF/personal-project/AddFacility.jsp").forward(req, resp);

  }
}

