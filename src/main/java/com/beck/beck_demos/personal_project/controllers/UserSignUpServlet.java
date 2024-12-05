package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet  For adding to The  User table
 Created By Jonathan Beck3/3/2024
 ***************/
import com.beck.beck_demos.personal_project.data.*;
import com.beck.beck_demos.personal_project.models.*;
import com.beck.beck_demos.shared.EmailService;
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

@WebServlet("/joinus")
public class UserSignUpServlet extends HttpServlet{

  static List<Language> alllanguages = LanguageDAO.getAllLanguage();



  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("pageTitle", "Join Us!");


    alllanguages = LanguageDAO.getActiveLanguage();
    req.setAttribute("languages", alllanguages);

    req.getRequestDispatcher("WEB-INF/personal-project/JoinUs.jsp").forward(req, resp);  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int id;
    req.setAttribute("pageTitle", "Join Us!");



    req.setAttribute("languages", alllanguages);



    String _User_Name = req.getParameter("inputuserUser_Name");
    String _User_PW = req.getParameter("inputuserUser_PW");
    String _User_PW2 = req.getParameter("inputuserUser_PW2");
    char[] _User_PWChar = _User_PW.toCharArray();

    String _Email = req.getParameter("inputuserEmail");
    String _Language_ID = req.getParameter("inputuserLanguage_ID");





    Map<String, String> results = new HashMap<>();
    results.put("User_Name3",_User_Name);
    results.put("User_PW3",_User_PW);
    results.put("User_PW2",_User_PW2);

    results.put("Email",_Email);
    results.put("Language_ID",_Language_ID);

    User user = new User();

    int errors =0;

    try {
      user.setUser_Name(_User_Name);
    } catch(IllegalArgumentException e) {results.put("userUser_Nameerror3", e.getMessage());
      errors++;
    }
    boolean usernameFree = false;
    try {
      usernameFree = UserDAO.usernameFree(_User_Name);
    } catch (Exception e) {
      results.put("dbStatus",e.getMessage());
      errors++;
    }
    if (!usernameFree){
      results.put("userUser_Nameerror3", "This username is already taken.");
      errors++;
    }
    try {
      user.setUser_PW(_User_PWChar);
    } catch(IllegalArgumentException e) {results.put("userUser_PWerror3", e.getMessage());
      errors++;
    }
    if (!_User_PW.equals(_User_PW2)){
      results.put("userUser_PW2error", "Passwords do not match");
      errors++;
    }
    try {
      user.setStatus_ID(Integer.valueOf(1));
    } catch(IllegalArgumentException e) {results.put("userStatus_IDerror", e.getMessage());
      errors++;
    }
    try {
      user.setEmail(_Email);
    } catch(IllegalArgumentException e) {results.put("userEmailerror", e.getMessage());
      errors++;
    }
    boolean emailFree = true;
    try {
       emailFree = UserDAO.emailFree(_Email);
    } catch (Exception e) {
      results.put("dbStatus",e.getMessage());
      errors++;
    }
    if (!emailFree){
      results.put("userEmailerror", "This email  is already taken.");
    errors++;
    }
    try {
      user.setLanguage_ID(Integer.valueOf(_Language_ID));
    } catch(IllegalArgumentException e) {results.put("userLanguage_IDerror", e.getMessage());
      errors++;
    }
    try {
      user.setPrivilege_ID(Integer.valueOf(1));
    } catch(IllegalArgumentException e) {results.put("userPrivilege_IDerror", e.getMessage());
      errors++;
    }
    int result=0;
    if (errors==0){
      try{
        result=UserDAO.add(user);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","User Added");
        try {
           id  = UserDAO.getUserID(_Email);
           results.put("UserID", String.valueOf(id));
          TwoFA twofa = new TwoFA(id);
          TwoFADAO.add(twofa);
          String code = TwoFADAO.getTwoFAById(id);
          EmailService.send2faCode_Roller(code,_Email);

          HttpSession session = req.getSession();
          session.setAttribute("UserID",id);
          resp.sendRedirect("twoFA");
          return;
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      } else {
        results.put("dbStatus","User Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a User ");
    req.getRequestDispatcher("WEB-INF/personal-project/JoinUs.jsp").forward(req, resp);

  }
}

