package com.beck.beck_demos.budget_app.controllers;



/******************
 Create the Servlet  For  signing in
 Created By Jonathan Beck3/11/2024

 ***************/
import com.beck.beck_demos.budget_app.data.*;
import com.beck.beck_demos.budget_app.models.*;
import com.beck.beck_demos.shared.EmailService;
import com.mysql.cj.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/budget_home")
public class BudgetHomeServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Budget Home");

    req.getRequestDispatcher("WEB-INF/Budget_App/home.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession();
    session.setAttribute("currentPage",req.getRequestURL());
    req.setAttribute("pageTitle", "Budget Home");
    req.getRequestDispatcher("WEB-INF/Budget_App/home.jsp").forward(req, resp);

  }
}

