package com.beck.beck_demos.learnx.controllers;

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

@WebServlet("/enroll")
public class EnrollmentServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String x = "banana";

    User activeUser = Helpers.getUserFromSession(session);
    if(activeUser == null || !Helpers.isStudent(activeUser)) {
      resp.sendRedirect("courses");
      return;
    }
    String courseIdStr = req.getParameter("course");
    int courseId = 0;
    try {
      courseId = Integer.parseInt(courseIdStr);
    } catch(NumberFormatException e) {
      resp.sendRedirect("courses");
      return;
    }
    int result=0;
    try {
      result = CourseDAO.enroll(activeUser.getID(), courseId);

    }
    catch (Exception ex){
      result=0;
    }
    if (result>0) {
      session.setAttribute("flashMessageSuccess", "New course added!");
    }
    else {session.setAttribute("flashMessageWarning", "Course not added!"); }
    resp.sendRedirect("student");
  }
}