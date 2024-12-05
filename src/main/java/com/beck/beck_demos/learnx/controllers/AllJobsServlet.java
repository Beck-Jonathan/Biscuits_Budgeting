package com.beck.beck_demos.learnx.controllers;

/******************
 Create the Servlet  For Viewing all of the  Job_Listing table
 Created By Jonathan Beck 4/29/2024
 ***************/

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@WebServlet("/careers")
public class AllJobsServlet extends HttpServlet {
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level

  HttpSession session = req.getSession();
  User user = (User)session.getAttribute("User");


  session.setAttribute("currentPage",req.getRequestURL());
  List<JobListing> job_listings = null;

  job_listings =JobListingDAO.getAll();

  req.setAttribute("Job_Listings", job_listings);
  req.setAttribute("pageTitle", "All Job_Listings");
  req.getRequestDispatcher("WEB-INF/learnx/AllJobs.jsp").forward(req,resp);

}
}
