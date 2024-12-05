package com.beck.beck_demos.crrg.controllers;

/******************
 Create the Servlet For editing roles for the user table
 Created By Jonathan Beck 10/50/2024
 ***************/


import com.beck.beck_demos.crrg.data.User_DAO;
import com.beck.beck_demos.crrg.models.Album;
import com.beck.beck_demos.crrg.models.Picture;
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
@WebServlet("/editrole")
public class Edit_Role extends HttpServlet {
  private  iUser_DAO user_DAO;

  @Override
  public void init() throws ServletException{
    user_DAO = new User_DAO();

  }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Map<String, String> results = new HashMap<>();

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

    String userID=req.getParameter("objectID");
    String Role = req.getParameter("mode");





    int result = 0;

    try{

        result = user_DAO.changeRole(userID,Role);

    }
    catch(Exception ex){
      results.put("dbStatus",ex.getMessage());
    }



    resp.setContentType("text/plain");
    resp.getWriter().write(result);
  }
}



