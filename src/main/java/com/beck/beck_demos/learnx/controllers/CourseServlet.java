package com.beck.beck_demos.learnx.controllers;

import com.beck.beck_demos.learnx.Models.Course;
import com.beck.beck_demos.learnx.Models.CourseCategory;
import com.beck.beck_demos.learnx.Models.User;
import com.beck.beck_demos.learnx.data.CourseDAO;
import com.beck.beck_demos.shared.Helpers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = Helpers.getUserFromSession(session);
    if (user!=null) {
      TreeMap<Course, Instant> userCourses = CourseDAO.getCoursesEnrolled(5, 0, user.getID());
      req.setAttribute("userCourses", userCourses);
    }
    String [] categoryTry = req.getParameterValues("category");
    String skillTry = req.getParameter("skill-level");
    int limitTry = 0;
    int offsetTry= 0;
    try{
      limitTry = Integer.parseInt(req.getParameter("limit"));
    }
    catch (Exception e){
      limitTry = 25;
    }
    try{
      offsetTry = Integer.parseInt(req.getParameter("offset"));
    }
    catch (Exception e){
      offsetTry = 0;
    }


    String categoryFilter = "";
    if (categoryTry!=null && categoryTry.length>0){
      categoryFilter=String.join(",",categoryTry);
    }
    String skillFilter="";
    if (skillTry!=null) {
      skillFilter = skillTry;
    }
    // To do: For pagination
    int limit = limitTry;
    int offset = offsetTry;
    List<Course> courses = new ArrayList<>();

    try {
      courses = CourseDAO.get(limit, offset, categoryFilter, skillFilter);
    }
    catch (Exception ex){
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return;
    }

    List<CourseCategory> categories = CourseDAO.getAllCategories();
    req.setAttribute("courses", courses);
    req.setAttribute("categories", categories);
    req.setAttribute("pageTitle", "Courses");
    req.setAttribute("categoryFilter",categoryFilter);
    req.setAttribute("skillFilter",skillFilter);
    req.getRequestDispatcher("WEB-INF/learnx/all-courses.jsp").forward(req, resp);
  }
}