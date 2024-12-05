package com.beck.beck_demos.personal_project.controllers;

/******************
 Create the Servlet  For adding to The  User table
 Created By Jonathan Beck3/3/2024

 ***************/
import com.beck.beck_demos.personal_project.data.*;
import com.beck.beck_demos.personal_project.models.*;
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

@WebServlet("/addUser")
public class addUserservlet extends HttpServlet{
  static List<Status> allstatuss = StatusDAO.getAllStatus();
  static List<Language> alllanguages = LanguageDAO.getAllLanguage();
  static List<Privilege> allprivileges = PrivilegeDAO.getAllPrivilege();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendError(HttpServletResponse.SC_FORBIDDEN);
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add User");
    allstatuss = StatusDAO.getAllStatus();
    req.setAttribute("statuss", allstatuss);
    alllanguages = LanguageDAO.getAllLanguage();
    req.setAttribute("languages", alllanguages);
    allprivileges = PrivilegeDAO.getAllPrivilege();

    req.setAttribute("privileges", allprivileges);
    req.getRequestDispatcher("WEB-INF/personal-project/AddUser.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("pageTitle", "Add User");

    req.setAttribute("statuss", allstatuss);

    req.setAttribute("languages", alllanguages);


    req.setAttribute("privileges", allprivileges);
    String _User_Name = req.getParameter("inputuserUser_Name");
    String _User_PW = req.getParameter("inputuserUser_PW");
    char[] _User_PWchar = _User_PW.toCharArray();
    String _Status_ID = req.getParameter("inputuserStatus_ID");
    String _Email = req.getParameter("inputuserEmail");
    String _Language_ID = req.getParameter("inputuserLanguage_ID");
    String _Privilege_ID = req.getParameter("inputuserPrivilege_ID");
    Map<String, String> results = new HashMap<>();
    results.put("User_Name",_User_Name);
    results.put("User_PW",_User_PW);
    results.put("Status_ID",_Status_ID);
    results.put("Email",_Email);
    results.put("Language_ID",_Language_ID);
    results.put("Privilege_ID",_Privilege_ID);
    User user = new User();
    int errors =0;
    try {
      user.setUser_Name(_User_Name);
    } catch(IllegalArgumentException e) {results.put("userUser_Nameerror", e.getMessage());
      errors++;
    }
    try {
      user.setUser_PW(_User_PWchar);
    } catch(IllegalArgumentException e) {results.put("userUser_PWerror", e.getMessage());
      errors++;
    }
    try {
      user.setStatus_ID(Integer.valueOf(_Status_ID));
    } catch(IllegalArgumentException e) {results.put("userStatus_IDerror", e.getMessage());
      errors++;
    }
    try {
      user.setEmail(_Email);
    } catch(IllegalArgumentException e) {results.put("userEmailerror", e.getMessage());
      errors++;
    }
    try {
      user.setLanguage_ID(Integer.valueOf(_Language_ID));
    } catch(IllegalArgumentException e) {results.put("userLanguage_IDerror", e.getMessage());
      errors++;
    }
    try {
      user.setPrivilege_ID(Integer.valueOf(_Privilege_ID));
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
        TwoFA twoFA =  new TwoFA(result);
        TwoFADAO.add(twoFA);
      } else {
        results.put("dbStatus","User Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Create a User ");
    req.getRequestDispatcher("WEB-INF/personal-project/AddUser.jsp").forward(req, resp);

  }
}

