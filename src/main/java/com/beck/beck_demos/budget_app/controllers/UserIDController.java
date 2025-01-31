/*
package com.beck.beck_demos.budget_app.controllers;

import com.beck.beck_demos.budget_app.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user_Id")
public class UserIDController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    User user = (User)session.getAttribute("User_B");
    resp.setContentType("text/plain");
    String userid=user.getUser_ID().toString();

    PrintWriter pw = resp.getWriter();

    try  {
      pw.write(userid);
    }
    catch (Exception ex){

    }
    finally {
      pw.close();
    }
  }
}*/
