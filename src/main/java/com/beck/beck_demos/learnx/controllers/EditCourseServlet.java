package com.beck.beck_demos.learnx.controllers;


import com.beck.beck_demos.learnx.data.CourseDAO;
import com.beck.beck_demos.learnx.Models.Course;
import com.beck.beck_demos.learnx.Models.User;
import com.beck.beck_demos.learnx.Models.CourseCategory;
import com.beck.beck_demos.learnx.data.UserDAO;
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

@WebServlet("/editCourse")
public class EditCourseServlet extends HttpServlet{
  static List<User> allTeachers;
  static List<String> allLevels = CourseDAO.getLevels();

  static {
    try {
      allTeachers = UserDAO.getTeachers();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  static List<CourseCategory> allcategorys = CourseDAO.getAllCourse_Category();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    allLevels = CourseDAO.getLevels();
    req.setAttribute("levels",allLevels);
//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("activeUser");
    if (user==null||!user.getPrivileges().equals("admin")){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }


    int primaryKey = Integer.parseInt(req.getParameter("id"));
    Course course= new Course();
    course.setId(primaryKey);
    try{
      course=CourseDAO.getcourseByPrimaryKey(primaryKey);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }

    session.setAttribute("course",course);

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add course");
    try {
      allTeachers = UserDAO.getTeachers();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("teachers", allTeachers);
    allcategorys = CourseDAO.getAllCourse_Category();
    req.setAttribute("categorys", allcategorys);
    req.getRequestDispatcher("WEB-INF/learnx/edit-course.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    allLevels = CourseDAO.getLevels();
    req.setAttribute("levels",allLevels);
//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("activeUser");
    if (user==null||!user.getPrivileges().equals("admin")){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs
    try {
      allTeachers = UserDAO.getAllUsers();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    req.setAttribute("teachers", allTeachers);
    allcategorys = CourseDAO.getAllCategories();
    req.setAttribute("categorys", allcategorys);
//to get the old course

    Course _oldcourse= (Course)session.getAttribute("course");
//to get the new course's info
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
    results.put("name",_name);
    results.put("description",_description);
    results.put("level",_level);
    results.put("picture",_picture);
    results.put("teacher_id",_teacher_id);
    results.put("category_id",_category_id);
    Course _newcourse = new Course();
    int errors =0;
    try {
      _newcourse.setName(_name);
    } catch(IllegalArgumentException e) {results.put("coursenameerror", e.getMessage());
      errors++;
    }
    try {
      _newcourse.setDescription(_description);
    } catch(IllegalArgumentException e) {results.put("coursedescriptionerror", e.getMessage());
      errors++;
    }
    try {
      _newcourse.setLevel(_level);
    } catch(IllegalArgumentException e) {results.put("courselevelerror", e.getMessage());
      errors++;
    }
    try {
      _newcourse.setPicture(_picture);
    } catch(IllegalArgumentException e) {results.put("coursepictureerror", e.getMessage());
      errors++;
    }
    try {
      _newcourse.setTeacherID(Integer.valueOf(_teacher_id));
    } catch(IllegalArgumentException e) {results.put("courseteacher_iderror", e.getMessage());
      errors++;
    }
    try {
      _newcourse.setCategoryId(Integer.valueOf(_category_id));
    } catch(IllegalArgumentException e) {results.put("coursecategory_iderror", e.getMessage());
      errors++;
    }

//to update the database
    int result=0;
    if (errors==0){
      try{
        result=CourseDAO.update(_oldcourse,_newcourse);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","course updated");
        session.setAttribute("flashMessageSuccess", "Class updated");
        resp.sendRedirect("all-courses");
        return;
      } else {
        results.put("dbStatus","course Not Updated");
        session.setAttribute("flashMessageWarning", "Please check your inputs");
      }
    }





//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a course ");
    req.getRequestDispatcher("WEB-INF/learnx/edit-course.jsp").forward(req, resp);
  }
}

