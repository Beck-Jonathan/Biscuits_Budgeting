package com.beck.beck_demos.learnx.controllers;

import com.beck.beck_demos.learnx.Models.User;
import  com.beck.beck_demos.learnx.data.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
  static final int SECONDSINTHIRTYDAYS = 30*24*60*60;
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("pageTitle", "Log in");
    String redirect = req.getParameter("redirect");


    if(redirect != null) {
      req.setAttribute("redirect", redirect);
    }
    req.getRequestDispatcher("WEB-INF/learnx/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String redirect = req.getParameter("redirect");
    if (redirect==null){
      redirect="learnx";
    }
    String email = req.getParameter("inputEmail1");
    String password = req.getParameter("inputPassword1");
    String[] rememberMe = req.getParameterValues("checkbox-1");

    Map<String,String> results = new HashMap<>();
    results.put("email",email);
    results.put("password1",password);
    if(rememberMe != null && rememberMe[0].equals("remember")) {
      results.put("remember", "remember");
    }

    User userFromDatabase = null;
    try {
      userFromDatabase = UserDAO.getUserByPrimaryKey(email);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    if(userFromDatabase == null) {
      // No user found with that email
      results.put("loginFail", "Invalid combination");
    }
    else{
      if(!BCrypt.checkpw(password,String.valueOf(userFromDatabase.getPassword()))){
        results.put("loginFail", "Invalid passsword");
      }
      else{
        if (!userFromDatabase.getStatus().equals("active")){
          results.put("loginFail", "User Account Status: "+userFromDatabase.getStatus());
        }
        else{
          userFromDatabase.setLast_logged_in(Instant.now());
          UserDAO.update(userFromDatabase);
          userFromDatabase.setPassword(null);

          HttpSession session = req.getSession();
          if(rememberMe!=null){
            session.setMaxInactiveInterval(SECONDSINTHIRTYDAYS);

          }
          session.setAttribute("activeUser",userFromDatabase);
          session.setAttribute("flashMessageSuccess","Logged in!");
          session.setAttribute("language",userFromDatabase.getLanguage());
          resp.sendRedirect(redirect);

          return;

        }
      }
    }
    req.setAttribute("results", results);
    req.setAttribute("pageTitle", "Log in to your account");
    req.getRequestDispatcher("WEB-INF/learnx/login.jsp").forward(req, resp);

  }
}
