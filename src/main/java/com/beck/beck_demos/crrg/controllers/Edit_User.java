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
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/******************
 Create the Servlet Viuw/Edit from the User table
 Created By Jonathan Beck 10/15/2024
 ***************/

@WebServlet("/editCrrgUser")
public class Edit_User extends HttpServlet{

  private iUser_DAO userDAO;
  private List<String> allRoles;

  @Override
  public void init() throws ServletException{
    userDAO = new User_DAO();
    allRoles = userDAO.getDistinctRoleForDropdown();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User _user = (User)session.getAttribute("User");
    if (_user==null||!_user.isInRole(ROLES_NEEDED)){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    String mode = req.getParameter("mode");
    String primaryKey = req.getParameter("userid");
    User user= new User();
    user.setUser_ID(primaryKey);
    try{
      user=userDAO.getUserByUserID(user);
    } catch (SQLException e) {
      req.setAttribute("dbStatus",e.getMessage());
    }

    session.setAttribute("_user",user);
    req.setAttribute("mode",mode);
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "View User");
    allRoles = userDAO.getDistinctRoleForDropdown();
    req.setAttribute("Roles", allRoles);
    req.getRequestDispatcher("WEB-INF/crrg/EditUser.jsp").forward(req, resp);
  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//To restrict this page based on privilege level
    int PRIVILEGE_NEEDED = 0;
    List<String> ROLES_NEEDED = new ArrayList<>();
    ROLES_NEEDED.add("Jonathan");
//add roles here
    HttpSession session = req.getSession();
    User _user = (User)session.getAttribute("User");
    if (_user==null||!_user.isInRole(ROLES_NEEDED)){
      resp.sendError(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    Map<String, String> results = new HashMap<>();
    String mode = req.getParameter("mode");
    req.setAttribute("mode",mode);
//to set the drop downs
    allRoles = userDAO.getDistinctRoleForDropdown();
    req.setAttribute("Roles", allRoles);
//to get the old User

    User _oldUser= (User)session.getAttribute("_user");
//to get the new event's info
    //String _User_ID = req.getParameter("inputuserUser_ID");
    String _User_ID=_oldUser.getUser_ID();
    String _Role_ID = req.getParameter("inputuserRole_ID");
    _Role_ID=_Role_ID.trim();
    String _First_Name = req.getParameter("inputuserFirst_Name");
    _First_Name=_First_Name.trim();
    String _Last_Name = req.getParameter("inputuserLast_Name");
    _Last_Name=_Last_Name.trim();
    String _Email = req.getParameter("inputuserEmail");
    _Email=_Email.trim();

    results.put("User_ID",_User_ID);
    results.put("Role_ID",_Role_ID);
    results.put("First_Name",_First_Name);
    results.put("Last_Name",_Last_Name);
    results.put("Email",_Email);

    User _newUser = new User();
    int errors =0;
    try {
      _newUser.setUser_ID(_User_ID);
    } catch(IllegalArgumentException e) {results.put("userUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newUser.setRole_ID(_Role_ID);
    } catch(IllegalArgumentException e) {results.put("userRole_IDerror", e.getMessage());
      errors++;
    }
    try {
      _newUser.setFirst_Name(_First_Name);
    } catch(IllegalArgumentException e) {results.put("userFirst_Nameerror", e.getMessage());
      errors++;
    }
    try {
      _newUser.setLast_Name(_Last_Name);
    } catch(IllegalArgumentException e) {results.put("userLast_Nameerror", e.getMessage());
      errors++;
    }
    try {
      _newUser.setEmail(_Email);
    } catch(IllegalArgumentException e) {results.put("userEmailerror", e.getMessage());
      errors++;
    }

//to update the database
    int result=0;
    if (errors==0){
      try{
        result=userDAO.update(_oldUser,_newUser);
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","User updated");
        resp.sendRedirect("allCrrgUser");
        return;
      } else {
        results.put("dbStatus","User Not Updated");
      }
    }
//standard
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Edit a User ");
    req.getRequestDispatcher("WEB-INF/crrg/EditUser.jsp").forward(req, resp);
  }
}


