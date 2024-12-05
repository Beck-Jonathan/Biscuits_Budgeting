package com.beck.beck_demos.learnx.controllers;


import com.beck.beck_demos.learnx.Models.CourseCategory;
import com.beck.beck_demos.learnx.data.CourseDAO;
import com.beck.beck_demos.learnx.data.UserDAO;
import com.beck.beck_demos.learnx.Models.Course;
import com.beck.beck_demos.learnx.Models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet  For adding to The  course table
 Created By Jonathan Beck 4/25/2024
 ***************/

@WebServlet("/addCourse")
public class AddCourseServlet extends HttpServlet{

  static List<CourseCategory> allcategorys = CourseDAO.getAllCourse_Category();
  static List<String> allLevels = CourseDAO.getLevels();

  static List<User> allTeachers;

  static {
    try {
      allTeachers = UserDAO.getTeachers();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    String PRIVILEGE_NEEDED = "admin";
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("activeUser");
    if (user==null||!user.getPrivileges().equals("admin")){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add course");

    allcategorys = CourseDAO.getAllCourse_Category();
    req.setAttribute("categorys", allcategorys);
    allLevels = CourseDAO.getLevels();
    req.setAttribute("levels",allLevels);
    try {
      allTeachers = UserDAO.getTeachers();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("teachers",allTeachers);
    req.getRequestDispatcher("WEB-INF/learnx/Addcourse.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    String PRIVILEGE_NEEDED = "admin";
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("activeUser");
    if (user==null||!user.getPrivileges().equals("admin")){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }


    allcategorys = CourseDAO.getAllCourse_Category();
    req.setAttribute("categorys", allcategorys);
    allLevels = CourseDAO.getLevels();
    req.setAttribute("levels",allLevels);
    try {
      allTeachers = UserDAO.getTeachers();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("teachers",allTeachers);
    String _name = req.getParameter("inputcoursename");
    _name=_name.trim();
    String _description = req.getParameter("inputcoursedescription");
    _description=_description.trim();
    String _level = req.getParameter("inputcourselevel");
    _level=_level.trim();
    String _picture = req.getParameter("inputcoursepicture");
    _picture=_picture.trim();
    String _teacher_id = req.getParameter("inputcourseteacher_id");
    _teacher_id=_teacher_id.trim();
    String _category_id = req.getParameter("inputcoursecategory_id");
    _category_id=_category_id.trim();
    Map<String, String> results = new HashMap<>();
    results.put("name",_name);
    results.put("description",_description);
    results.put("level",_level);
    results.put("picture",_picture);
    results.put("teacher_id",_teacher_id);
    results.put("category_id",_category_id);
    Course course = new Course();
    int errors =0;
    try {
      course.setName(_name);
    } catch(IllegalArgumentException e) {results.put("coursenameerror", e.getMessage());
      errors++;
    }
    try {
      course.setDescription(_description);
    } catch(IllegalArgumentException e) {results.put("coursedescriptionerror", e.getMessage());
      errors++;
    }
    try {
      course.setLevel(_level);
    } catch(IllegalArgumentException e) {results.put("courselevelerror", e.getMessage());
      errors++;
    }
    try {
      course.setPicture(_picture);
    } catch(IllegalArgumentException e) {results.put("coursepictureerror", e.getMessage());
      errors++;
    }
    try {
      course.setTeacherID(Integer.valueOf(_teacher_id));
    } catch(IllegalArgumentException e) {results.put("courseteacher_iderror", e.getMessage());
      errors++;
    }

    try {
      course.setCategoryId(Integer.valueOf(_category_id));
    } catch(IllegalArgumentException e) {results.put("coursecategory_iderror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=CourseDAO.add(course);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","course Added");
        session.setAttribute("flashMessageSuccess", "User updated");
        resp.sendRedirect("all-courses");
        return;
      } else {
        results.put("dbStatus","course Not Added");
        session.setAttribute("flashMessageWarning", "Please check your inputs");

      }
    }



    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a course ");
    req.getRequestDispatcher("WEB-INF/learnx/Addcourse.jsp").forward(req, resp);

  }
}


