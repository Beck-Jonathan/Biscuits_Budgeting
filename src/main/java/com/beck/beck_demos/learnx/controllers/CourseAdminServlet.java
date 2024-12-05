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

@WebServlet("/all-courses")
public class CourseAdminServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();

    List<Course> courses = new ArrayList<>();

    try {
      courses = CourseDAO.get(100,0,"","");
    }
    catch (Exception ex){
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return;
    }
    req.setAttribute("courses", courses);


    req.getRequestDispatcher("WEB-INF/learnx/all-courses-admin.jsp").forward(req, resp);
  }
}