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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/editevent")
public class EditEventServlet extends HttpServlet {
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
    String mode = req.getParameter("mode");
    int primaryKey = Integer.parseInt(req.getParameter("eventid"));
        Event event= new Event();
        event.setEvent_ID(primaryKey);
    try{
      event=EventDAO.getEventByPrimaryKey(event);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }

    session.setAttribute("event",event);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Edit Event");
    allFacilitys = FacilityDAO.getAllFacility();
    req.setAttribute("Facilitys", allFacilitys);
    allTypes = TypeDAO.getAllType();
    req.setAttribute("Types", allTypes);
    req.getRequestDispatcher("WEB-INF/personal-project/EditEvent.jsp").forward(req, resp);
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
  Map<String, String> results = new HashMap<>();
  String mode = req.getParameter("mode");
  req.setAttribute("mode",mode);
//to set the drop downs
  allFacilitys = FacilityDAO.getAllFacility();
  req.setAttribute("Facilitys", allFacilitys);
  allTypes = TypeDAO.getAllType();
  req.setAttribute("Types", allTypes);
//to get the old Event

  Event _oldEvent= (Event)session.getAttribute("event");
//to get the new event's info
  String _Facility_ID = req.getParameter("inputeventFacility_ID");
  _Facility_ID=_Facility_ID.trim();
  String _Date = req.getParameter("inputeventDate");
  _Date=_Date.trim();
  String _Type_ID = req.getParameter("inputeventType_ID");
  _Type_ID=_Type_ID.trim();

  results.put("Facility_ID",_Facility_ID);
  results.put("Date",_Date);
  results.put("Type_ID",_Type_ID);

  Event _newEvent = new Event();
  int errors =0;
  try {
    _newEvent.setFacility_ID(Integer.valueOf(_Facility_ID));
  } catch(IllegalArgumentException e) {results.put("eventFacility_IDerror", e.getMessage());
    errors++;
  }
  try {
    _newEvent.setDate(LocalDate.parse(_Date));
  } catch(IllegalArgumentException e) {results.put("eventDateerror", e.getMessage());
    errors++;
  }
  try {
    _newEvent.setType_ID(Integer.valueOf(_Type_ID));
  } catch(IllegalArgumentException e) {results.put("eventType_IDerror", e.getMessage());
    errors++;
  }

  _newEvent.setis_active(true);
//to update the database
  int result=0;
  if (errors==0){
    try{
      result=EventDAO.update(_oldEvent,_newEvent);
    }catch(Exception ex){
      results.put("dbStatus","Database Error");
    }
    if (result>0){
      results.put("dbStatus","Event updated");
      resp.sendRedirect("all-Events");
      return;
    } else {
      results.put("dbStatus","Event Not Updated");
    }
  }
//standard
  req.setAttribute("results", results);
  req.setAttribute("pageTitle", "Edit a Event ");
  req.getRequestDispatcher("WEB-INF/personal-project/EditEvent.jsp").forward(req, resp);
}
}

