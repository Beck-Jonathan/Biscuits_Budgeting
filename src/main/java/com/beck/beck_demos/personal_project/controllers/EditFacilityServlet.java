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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  Facility table
 Created By Jonathan Beck 5/4/2024
 ***************/

@WebServlet("/editfacility")
public class EditFacilityServlet extends HttpServlet{

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

    String mode = req.getParameter("mode");
    int primaryKey = Integer.parseInt(req.getParameter("facilityid"));
    Facility facility= new Facility();
    facility.setFacility_ID(primaryKey);
    try{
      facility=FacilityDAO.getFacilityByPrimaryKey(facility);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }

    session.setAttribute("facility",facility);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add Facility");
    req.getRequestDispatcher("WEB-INF/personal-project/EditFacility.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 1;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||user.getPrivilege_ID()<PRIVILEGE_NEEDED){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs
//to get the old Facility

    Facility _oldFacility= (Facility)session.getAttribute("facility");
//to get the new event's info
    String _Name = req.getParameter("inputfacilityName");
    _Name=_Name.trim();
    String _Addresss = req.getParameter("inputfacilityAddresss");
    _Addresss=_Addresss.trim();
    String _City = req.getParameter("inputfacilityCity");
    _City=_City.trim();
    String _State = req.getParameter("inputfacilityState");
    _State=_State.trim();
    String _Zip = req.getParameter("inputfacilityZip");
    _Zip=_Zip.trim();

    results.put("Name",_Name);
    results.put("Addresss",_Addresss);
    results.put("City",_City);
    results.put("State",_State);
    results.put("Zip",_Zip);

    Facility _newFacility = new Facility();
    int errors =0;
    try {
      _newFacility.setName(_Name);
    } catch(IllegalArgumentException e) {results.put("facilityNameerror", e.getMessage());
      errors++;
    }
    try {
      _newFacility.setAddresss(_Addresss);
    } catch(IllegalArgumentException e) {results.put("facilityAddressserror", e.getMessage());
      errors++;
    }
    try {
      _newFacility.setCity(_City);
    } catch(IllegalArgumentException e) {results.put("facilityCityerror", e.getMessage());
      errors++;
    }
    try {
      _newFacility.setState(_State);
    } catch(IllegalArgumentException e) {results.put("facilityStateerror", e.getMessage());
      errors++;
    }
    try {
      _newFacility.setZip(_Zip);
    } catch(IllegalArgumentException e) {results.put("facilityZiperror", e.getMessage());
      errors++;
    }

    _newFacility.setis_active(true);
//to update the database
    int result=0;
    if (errors==0){
      try{
        result=FacilityDAO.update(_oldFacility,_newFacility);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Facility updated");
        resp.sendRedirect("all-Facilitys");
        return;
      } else {
        results.put("dbStatus","Facility Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a Facility ");
    req.getRequestDispatcher("WEB-INF/personal-project/EditFacility.jsp").forward(req, resp);
  }
}

