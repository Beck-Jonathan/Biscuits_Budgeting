package com.beck.beck_demos.crrg.controllers;

import com.beck.beck_demos.crrg.data.User_DAO;
import com.beck.beck_demos.crrg.models.User;


import com.beck.beck_demos.crrg.data_interfaces.iUser_DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.*;

@WebServlet("/crrgLogin")
public class Login_Servlet extends HttpServlet {
  private iUser_DAO userDAO;
  @Override
  public void init() throws ServletException{
    userDAO = new User_DAO();
  }
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user!=null){
      resp.sendRedirect("crrg_management");
      return;
    }
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Log In!");
    req.getRequestDispatcher("WEB-INF/crrg/Login.jsp").forward(req, resp);
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User");
    if (user!=null){
      resp.sendRedirect("crrg_management");
      return;
    }
    user = new User();
    Map<String, String> results = new HashMap<>();
    String _User_ID = req.getParameter("inputuserUser_ID");


    String _Password = req.getParameter("inputuserPassword");
    results.put("User_ID",_User_ID);
    results.put("Password",_Password);
    int errors =0;
    try {
      _User_ID=_User_ID.trim();
      user.setUser_ID(_User_ID);

    } catch(Exception e) {results.put("userUser_IDerror", e.getMessage());
      errors++;
    }
    try {
      user.setPassword(_Password.toCharArray());
    } catch(Exception e) {results.put("userPassworderror", e.getMessage());
      errors++;
    }

//to update the database
    int result=0;
    if (errors==0){
      try{
        String _password = userDAO.getPassword(_User_ID);
        if (BCrypt.checkpw(_Password,_password)){


         user= userDAO.getUserByUserID(user);
          result=1;
         DateTime now = DateTime.now();
          user.setLast_Logged_In(now);
          userDAO.updateLastLoggedIn(user);
         session.setAttribute("User",user);


        }
        else{
          int x=0;

        }
      }catch(Exception ex){
        results.put("dbStatus","Database Error");
      }
      if (result>0){
        results.put("dbStatus","Logged In!");
        resp.sendRedirect("crrg_management");
        return;
      } else {
        results.put("dbStatus","User Not Logged In");
      }
    }
//standard
    req.setAttribute("results", results);

    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Log In!");
    req.getRequestDispatcher("WEB-INF/crrg/Login.jsp").forward(req, resp);
  }
  }

