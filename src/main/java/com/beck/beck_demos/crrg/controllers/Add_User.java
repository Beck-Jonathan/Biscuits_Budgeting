package com.beck.beck_demos.crrg.controllers;


import com.beck.beck_demos.crrg.data.User_DAO;
import com.beck.beck_demos.crrg.models.User;
import com.beck.beck_demos.crrg.models.User;
import com.beck.beck_demos.crrg.data_interfaces.iUser_DAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.*;

/******************
 Create the Servlet  For adding to The  User table
 Created By Jonathan Beck 10/15/2024
 ***************/

@WebServlet("/addCrrgUser")
public class Add_User extends HttpServlet{

  private iUser_DAO userDAO;
  @Override
  public void init() throws ServletException{
    userDAO = new User_DAO();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Add User");
    List<String> allRoles = userDAO.getDistinctRoleForDropdown();
    req.setAttribute("Roles", allRoles);
    req.getRequestDispatcher("WEB-INF/crrg/AddUser.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user==null||!user.isInRole(ROLES_NEEDED)){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    List<String> allRoles = userDAO.getDistinctRoleForDropdown();
    req.setAttribute("Roles", allRoles);
    String _User_ID = req.getParameter("inputuserUser_ID");
    _User_ID=_User_ID.trim();
    String _Role_ID = req.getParameter("inputuserRole_ID");
    _Role_ID=_Role_ID.trim();
    String _First_Name = req.getParameter("inputuserFirst_Name");
    _First_Name=_First_Name.trim();
    String _Last_Name = req.getParameter("inputuserLast_Name");
    _Last_Name=_Last_Name.trim();
    String _Email = req.getParameter("inputuserEmail");
    _Email=_Email.trim();
    DateTime _Last_Logged_In = null;

    Map<String, String> results = new HashMap<>();
    results.put("User_ID",_User_ID);
    results.put("Role_ID",_Role_ID);
    results.put("First_Name",_First_Name);
    results.put("Last_Name",_Last_Name);
    results.put("Email",_Email);


    User _user = new User();
    int errors =0;
    try {
      _user.setUser_ID(_User_ID);
    } catch(IllegalArgumentException e) {
      results.put("userUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      _user.setRole_ID(_Role_ID);
    } catch(IllegalArgumentException e) {results.put("userRole_IDerror", e.getMessage());
      errors++;
    }
    try {
      _user.setFirst_Name(_First_Name);
    } catch(IllegalArgumentException e) {results.put("userFirst_Nameerror", e.getMessage());
      errors++;
    }
    try {
      _user.setLast_Name(_Last_Name);
    } catch(IllegalArgumentException e) {results.put("userLast_Nameerror", e.getMessage());
      errors++;
    }
    try {
      _user.setEmail(_Email);
    } catch(IllegalArgumentException e) {results.put("userEmailerror", e.getMessage());
      errors++;
    }
    _user.setLast_Logged_In(null);
    List<String> firstPart = new ArrayList<>(Arrays.asList("Tall","Short","Agile","Speedy"));
    List<String> secondPart = new ArrayList<>(Arrays.asList("Jammer", "Blocker", "Pivot", "Coach"));
    Random rand = new Random();
    String _Password = firstPart.get(rand.nextInt(firstPart.size()-1));
    _Password+=secondPart.get(rand.nextInt(secondPart.size()-1));
    _Password+=rand.nextInt(99);

    _user.setPassword(_Password.toCharArray());

    int result=0;
    if (errors==0){
      try{
        boolean usernameFree=userDAO.usernameFree(_User_ID);
        if (!usernameFree){
          results.put("userUser_IDerror", "Derby name already in use.");

        }
        boolean emailFree= userDAO.emailFree(_Email);
        if (!emailFree){
          results.put("userEmailerror", "Email already in use");
        }
        if (usernameFree&&emailFree) {
          result = userDAO.add(_user);
        }
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        Integer newUser=1;
        session.setAttribute("newUser",newUser);
        results.put("dbStatus","User Added");
        results.put("Password",_Password);
        session.setAttribute("results", results);
        resp.sendRedirect("allCrrgUser");
        return;
      } else {
        results.put("dbStatus","User Not Added");

      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Add a User");
    req.getRequestDispatcher("WEB-INF/crrg/AddUser.jsp").forward(req, resp);

  }
}


