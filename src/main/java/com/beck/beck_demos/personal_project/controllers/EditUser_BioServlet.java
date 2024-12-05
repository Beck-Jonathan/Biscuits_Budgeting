package com.beck.beck_demos.personal_project.controllers;


import com.beck.beck_demos.personal_project.data.UserBioDAO;
import com.beck.beck_demos.personal_project.models.UserBio;
import com.beck.beck_demos.personal_project.models.User;
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
import java.time.LocalDate;
import java.util.*;

/******************
 Create the Servlet  For adding to The  User_Bio table
 Created By Jonathan Beck 5/5/2024
 ***************/

@WebServlet("/editUser_Bio")
public class EditUser_BioServlet extends HttpServlet{


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level

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

    UserBio bio = new UserBio();
    try {
      bio = UserBioDAO.getUser_BioByPrimaryKey(user.getUser_ID());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }


    req.setAttribute("user_bio", bio);

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);

//to get the old User_Bio, if exists
    boolean exists = false;
    try {
      exists = UserBioDAO.checkForBio(user.getUser_ID());
    } catch (SQLException e) {

    }
    UserBio _oldUser_Bio = null;
    if (exists) {

      try {
        _oldUser_Bio = UserBioDAO.getUser_BioByPrimaryKey(user.getUser_ID());
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
//to get the new event's info
    Integer _User_ID = user.getUser_ID();
    int errors =0;

    String _Postion = req.getParameter("inputuser_bioposition");
    if (_Postion !=null) {
      _Postion = _Postion.trim();
    }
    else{
      _Postion="";
      errors++;
    }
    String _Bio = req.getParameter("inputuser_bioBio");
    if  (_Bio!=null) {

      _Bio = _Bio.trim();
    }
    else{
      _Bio="";
      errors++;
    }

    String _Began_Skating = req.getParameter("inputuser_bioBegan_Skating");
    if (_Began_Skating !=null) {

      _Began_Skating = _Began_Skating.trim();
    }
    else{
      _Began_Skating = "";
      errors++;
    }

    results.put("Postion",_Postion);
    results.put("Bio",_Bio);
    results.put("Began_Skating",_Began_Skating);

    UserBio _newUser_Bio = new UserBio();

    try {
      _newUser_Bio.setUser_ID(_User_ID);
    } catch(IllegalArgumentException e) {results.put("user_bioUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newUser_Bio.setPostion(_Postion);
    } catch(IllegalArgumentException e) {results.put("user_bioPostionerror", e.getMessage());
      errors++;
    }
    try {
      _newUser_Bio.setBio(_Bio);
    } catch(IllegalArgumentException e) {results.put("user_bioBioerror", e.getMessage());
      errors++;
    }
    try {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-mm", Locale.ENGLISH);


      Date date = formatter.parse(_Began_Skating);
      _newUser_Bio.setBegan_Skating(date);
    } catch(Exception e) {results.put("user_bioBegan_Skatingerror", e.getMessage());
      errors++;
    }

//to update the database
    int result=0;
    if (errors==0){
      try{
        if (exists) {
          result = UserBioDAO.update(_oldUser_Bio, _newUser_Bio);
        }
        else {
          result = UserBioDAO.add(_newUser_Bio);
        }
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","User_Bio updated");
        if (user.getPrivilege_ID()==1) {
          resp.sendRedirect("user-dash");
          return;
        }
        if (user.getPrivilege_ID()==2){
          resp.sendRedirect("team-admin-dash");
          return;
        }
        return;
      } else {
        results.put("dbStatus","User_Bio Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);

    req.getRequestDispatcher("WEB-INF/personal-project/UserDash.jsp").forward(req, resp);
  }
}

