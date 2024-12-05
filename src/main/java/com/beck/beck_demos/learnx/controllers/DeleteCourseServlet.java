package com.beck.beck_demos.learnx.controllers;

/******************
 Create the Servlet For Deleteing from the course table
 Created By Jonathan Beck 4/25/2024
 ***************/

import com.beck.beck_demos.learnx.data.CourseDAO;
import com.beck.beck_demos.learnx.Models.Course;
import com.beck.beck_demos.learnx.Models.User;
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
@WebServlet("/deletecourse")public class DeleteCourseServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

//To restrict this page based on privilege level
    String PRIVILEGE_NEEDED = "admin";
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("activeUser");
    if (user==null||!user.getPrivileges().equals(PRIVILEGE_NEEDED)){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Delete course");
    int courseID = Integer.valueOf(req.getParameter("id"));

    int result = 0;

      try{
        result = CourseDAO.deletecourse(courseID);
        if (result>0){
          session.setAttribute("flashMessageSuccess", "Course Delete");
        }
        else {session.setAttribute("flashMessageWarning", "Please check your inputs");}
      }
      catch(Exception ex){
        results.put("dbStatus",ex.getMessage());
        session.setAttribute("flashMessageWarning", "Please check your inputs");
      }


    List<Course> courses = null;
    courses = CourseDAO.get(100,0,"","");
    session.setAttribute("flashMessageSuccess", "Course Deleted");
    req.setAttribute("results",results);
    req.setAttribute("courses", courses);
    req.setAttribute("pageTitle", "All course");
    req.getRequestDispatcher("WEB-INF/learnx/all-courses-admin.jsp").forward(req, resp);

  }
}

