package com.beck.beck_demos.learnx.controllers;

import com.beck.beck_demos.learnx.Models.Department;
import com.beck.beck_demos.learnx.data.JobListingDAO;
import com.beck.beck_demos.learnx.Models.JobListing;
import com.beck.beck_demos.personal_project.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  Job_Listing table
 Created By Jonathan Beck 4/29/2024
 ***************/

@WebServlet("/addJob_Listing")
public class AddJobServlet extends HttpServlet{
  static List<Department> alldepartments = JobListingDAO.getActivedepartment();

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
    req.setAttribute("pageTitle", "Add Job_Listing");
    alldepartments = JobListingDAO.getActivedepartment();
    req.setAttribute("departments", alldepartments);
    req.getRequestDispatcher("WEB-INF/personal-project/AddJob_Listing.jsp").forward(req, resp);
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

    alldepartments = JobListingDAO.getActivedepartment();
    req.setAttribute("departments", alldepartments);
    String _department_id = req.getParameter("inputjob_listingdepartment_id");
    _department_id=_department_id.trim();
    String _featured = req.getParameter("inputjob_listingfeatured");
    _featured=_featured.trim();
    String _position = req.getParameter("inputjob_listingposition");
    _position=_position.trim();
    String _posted_at = req.getParameter("inputjob_listingposted_at");
    _posted_at=_posted_at.trim();
    String _contract = req.getParameter("inputjob_listingcontract");
    _contract=_contract.trim();
    String _location = req.getParameter("inputjob_listinglocation");
    _location=_location.trim();
    String _job_description = req.getParameter("inputjob_listingjob_description");
    _job_description=_job_description.trim();
    Map<String, String> results = new HashMap<>();
    results.put("department_id",_department_id);
    results.put("featured",_featured);
    results.put("position",_position);
    results.put("posted_at",_posted_at);
    results.put("contract",_contract);
    results.put("location",_location);
    results.put("job_description",_job_description);
    JobListing job_listing = new JobListing();
    job_listing.setDepartment(new Department());
    int errors =0;
    try {
      job_listing.getDepartment().setDepartment_id(Integer.valueOf(_department_id));
    } catch(IllegalArgumentException e) {results.put("job_listingdepartment_iderror", e.getMessage());
      errors++;
    }
    try {
      job_listing.setFeatured(Boolean.parseBoolean(_featured));
    } catch(IllegalArgumentException e) {results.put("job_listingfeaturederror", e.getMessage());
      errors++;
    }
    try {
      job_listing.setPosition(_position);
    } catch(IllegalArgumentException e) {results.put("job_listingpositionerror", e.getMessage());
      errors++;
    }
    try {
      job_listing.setPosted_at(Instant.from(LocalDate.parse(_posted_at)));
    } catch(IllegalArgumentException e) {results.put("job_listingposted_aterror", e.getMessage());
      errors++;
    }
    try {
      job_listing.setContract(_contract);
    } catch(IllegalArgumentException e) {results.put("job_listingcontracterror", e.getMessage());
      errors++;
    }
    try {
      job_listing.setLocation(_location);
    } catch(IllegalArgumentException e) {results.put("job_listinglocationerror", e.getMessage());
      errors++;
    }
    try {
      job_listing.setDescription(_job_description);
    } catch(IllegalArgumentException e) {results.put("job_listingjob_descriptionerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=JobListingDAO.add(job_listing);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Job_Listing Added");
        resp.sendRedirect("all-Job_Listings");
        return;
      } else {
        results.put("dbStatus","Job_Listing Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a Job_Listing ");
    req.getRequestDispatcher("WEB-INF/personal-project/AddJob_Listing.jsp").forward(req, resp);

  }
}

