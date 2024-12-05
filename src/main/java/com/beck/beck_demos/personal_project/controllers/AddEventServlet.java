package com.beck.beck_demos.personal_project.controllers;

import com.beck.beck_demos.personal_project.data.EventDAO;
import com.beck.beck_demos.personal_project.data.FacilityDAO;
import com.beck.beck_demos.personal_project.data.TypeDAO;
import com.beck.beck_demos.personal_project.models.Event;
import com.beck.beck_demos.personal_project.models.Facility;
import com.beck.beck_demos.personal_project.models.Type;
import com.beck.beck_demos.personal_project.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/******************
 Create the Servlet  For adding to The  Event table
 Created By Jonathan Beck3/18/2024
 ***************/

@WebServlet("/addEvent")
public class AddEventServlet extends HttpServlet{
  static List<Facility> allFacilitys = FacilityDAO.getActiveFacility();
  static List<Type> allTypes = TypeDAO.getActiveType();

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
    req.setAttribute("pageTitle", "Add Event");
    allFacilitys = FacilityDAO.getAllFacility();
    req.setAttribute("Facilitys", allFacilitys);
    allTypes = TypeDAO.getAllType();
    req.setAttribute("Types", allTypes);
    req.getRequestDispatcher("WEB-INF/personal-project/AddEvent.jsp").forward(req, resp);
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
    allFacilitys = FacilityDAO.getAllFacility();
    req.setAttribute("Facilitys", allFacilitys);
    allTypes = TypeDAO.getAllType();
    req.setAttribute("Types", allTypes);
    String _Facility_ID = req.getParameter("inputeventFacility_ID");
    String _Date = req.getParameter("inputeventDate");
    String _Type_ID = req.getParameter("inputeventType_ID");
    String _is_active = req.getParameter("inputeventis_active");
    Map<String, String> results = new HashMap<>();
    results.put("Facility_ID",_Facility_ID);
    results.put("Date",_Date);
    results.put("Type_ID",_Type_ID);
    results.put("is_active",_is_active);
    Event event = new Event();
    int errors =0;
    try {
      event.setFacility_ID(Integer.valueOf(_Facility_ID));
    } catch(IllegalArgumentException e) {results.put("eventFacility_IDerror", e.getMessage());
      errors++;
    }
    try {
      event.setDate(LocalDate.parse(_Date));
    } catch(IllegalArgumentException e) {results.put("eventDateerror", e.getMessage());
      errors++;
    }
    try {
      event.setType_ID(Integer.valueOf(_Type_ID));
    } catch(IllegalArgumentException e) {results.put("eventType_IDerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=EventDAO.add(event);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Event Added");
        resp.sendRedirect("all-Events");
        return;
      } else {
        results.put("dbStatus","Event Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Event ");
    req.getRequestDispatcher("WEB-INF/personal-project/AddEvent.jsp").forward(req, resp);

  }
}





