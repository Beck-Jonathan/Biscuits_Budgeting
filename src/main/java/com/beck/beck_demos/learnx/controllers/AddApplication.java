package com.beck.beck_demos.learnx.controllers;

import com.beck.beck_demos.learnx.Models.JobApplication;
import com.beck.beck_demos.learnx.data.JobApplicationDAO;
import com.beck.beck_demos.learnx.data.JobListingDAO;
import com.beck.beck_demos.learnx.Models.JobListing;
import com.beck.beck_demos.learnx.Models.Department;
import com.beck.beck_demos.personal_project.models.User;
import com.beck.beck_demos.shared.MyValidators;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/******************
 Create the Servlet  For adding to The  Job_Listing table
 Created By Jonathan Beck 5/2/2024
 ***************/

@WebServlet("/apply-learnx")
public class AddApplication extends HttpServlet{
  //static List<Department> alldepartments = JobListingDAO.getActivedepartment();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level

    HttpSession session = req.getSession();
    Map<String, String> results = new HashMap<>();

    int primaryKey = Integer.parseInt(req.getParameter("job_id"));
    JobListing job_listing= new JobListing();
    job_listing.setJob_id(primaryKey);
    try{
      job_listing=JobListingDAO.getJob_ListingByPrimaryKey(primaryKey);
      if (job_listing==null){
        throw new ServletException("job not found");
      }


    } catch (Exception e) {

      results.put("jobNotFound","Job Not Found");

    }

    session.setAttribute("job_listing",job_listing);

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Apply!");
    //alldepartments = JobListingDAO.getActivedepartment();
    //req.setAttribute("departments", alldepartments);
    req.setAttribute("results", results);
    req.getRequestDispatcher("WEB-INF/learnx/Apply.jsp").forward(req, resp);
  }
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level

    HttpSession session = req.getSession();



    String _jobId = req.getParameter("inputjob_applicationjobId");
    _jobId=_jobId.trim();
    String _firstName = req.getParameter("inputjob_applicationfirstName");
    if(_firstName!=null) {
      _firstName = _firstName.trim();
    }
    String _lastName = req.getParameter("inputjob_applicationlastName");
    if(_lastName!=null) {
      _lastName = _lastName.trim();
    }

    String _email = req.getParameter("inputjob_applicationemail");
    if(_email !=null) {
      _email = _email.trim();
    }

    String _desiredSalary = req.getParameter("inputjob_applicationdesiredSalary");
    if (_desiredSalary!=null) {
      _desiredSalary = _desiredSalary.replace('$',' ');
      _desiredSalary = _desiredSalary.replaceAll(",","");
      _desiredSalary = _desiredSalary.trim();

    }

    String _earliestStartDate = req.getParameter("inputjob_applicationearliestStartDate");
    if(_earliestStartDate!=null){
      _earliestStartDate = _earliestStartDate.trim();
    }

    Map<String, String> results = new HashMap<>();
    results.put("jobId",_jobId);
    results.put("firstName",_firstName);
    results.put("lastName",_lastName);
    results.put("email",_email);
    results.put("desiredSalary",_desiredSalary);
    results.put("earliestStartDate",_earliestStartDate);

    JobApplication job_application = new JobApplication();
    int errors =0;
    try {
      job_application.setjobId(Integer.valueOf(_jobId));
    } catch(IllegalArgumentException e) {results.put("job_applicationjobIderror", e.getMessage());
      errors++;
    }
    try {
      job_application.setfirstName(_firstName);
    } catch(IllegalArgumentException e) {results.put("job_applicationfirstNameerror", e.getMessage());
      errors++;
    }
    try {
      job_application.setlastName(_lastName);
    } catch(IllegalArgumentException e) {results.put("job_applicationlastNameerror", e.getMessage());
      errors++;
    }
    try {
      Matcher matcher = MyValidators.emailPattern.matcher(_email);
      if (!matcher.matches()){
        errors++;
        throw new IllegalArgumentException("Invalid email");

      }

      job_application.setemail(_email);
    } catch(IllegalArgumentException e) {results.put("job_applicationemailerror", e.getMessage());
      errors++;
    }
    try {
      job_application.setdesiredSalary(Double.valueOf(_desiredSalary));
    } catch(IllegalArgumentException e) {results.put("job_applicationdesiredSalaryerror", e.getMessage());
      errors++;
    }
    if(_earliestStartDate!="") {
      try {
        Matcher matcher = MyValidators.DOBPattern.matcher(_earliestStartDate);
        if (matcher.matches()) {

          job_application.setearliestStartDate(new SimpleDateFormat("MM-dd-yyyy").parse(_earliestStartDate));
        }
        else{
          results.put("job_applicationearliestStartDateerror","Double check the format of your date");
          errors++;
        }

      } catch (IllegalArgumentException e) {
        results.put("job_applicationearliestStartDateerror", e.getMessage());
        errors++;
      } catch (ParseException e) {
        results.put("job_applicationearliestStartDateerror", e.getMessage());
      }
    }

      job_application.setdateSubmitted(Instant.now());

    try {
      job_application.setstatus("pending");
    } catch(IllegalArgumentException e) {results.put("job_applicationstatuserror", e.getMessage());
      errors++;
    }
    job_application.setstatus("pending");
    int result=0;
    if (errors==0){
      try{
        result= JobApplicationDAO.add(job_application);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        session.setAttribute("flashMessageSuccess", "You Have Applied!");
        results.put("dbStatus","Job_Application Added");

      } else {
        session.setAttribute("flashMessageSuccess", "You Have NOT Applied!");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Apply");
    req.getRequestDispatcher("WEB-INF/learnx/Apply.jsp").forward(req, resp);

  }
}