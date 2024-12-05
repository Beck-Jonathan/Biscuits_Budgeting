package com.beck.beck_demos.learnx.controllers;

/******************
 Create the Servlet  For Viewing all of the  Job_Application table
 Created By Jonathan Beck 5/2/2024
 ***************/

import com.beck.beck_demos.learnx.Models.User;
import com.beck.beck_demos.learnx.data.JobApplicationDAO;
import com.beck.beck_demos.learnx.Models.JobApplication;
import com.beck.beck_demos.shared.Helpers;
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
@WebServlet("/applications")
public class AllApplications extends HttpServlet {@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
  int PRIVILEGE_NEEDED = 0;
  HttpSession session = req.getSession();

  User userFromSession = Helpers.getUserFromSession(session);
  if(userFromSession == null || !Helpers.isActive(userFromSession) || !Helpers.isAdmin(userFromSession)) {
    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    return;
  }


  session.setAttribute("currentPage",req.getRequestURL());
  List<JobApplication> job_applications = null;

  job_applications =JobApplicationDAO.getAllJob_Application();

  req.setAttribute("Job_Applications", job_applications);
  req.setAttribute("pageTitle", "All Job_Applications");
  req.getRequestDispatcher("WEB-INF/learnx/all-Job_Applications.jsp").forward(req,resp);

}
}
